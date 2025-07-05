package com.bytecron.cronicle.service;


import com.bytecron.cronicle.modal.Post;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CronicleService {
    private final ResourcePatternResolver resourcePatternResolver;
    private final MarkdownService markDownService;
    private final ResourceLoader resourceLoader;

    @Cacheable("posts")
    public List<Post> getPosts() throws IOException {
        return loadAllPosts();
    }
    @Cacheable(value="renderedPosts", key = "#slug")
    public  String getPost(String slug) throws IOException {
        String path = "classpath:posts/" + slug + ".md";
        Resource resource = resourceLoader.getResource(path);
        InputStream inputStream = resource.getInputStream();
        String content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        return markDownService.renderMarkdownToHtml(content);
    }

    @Cacheable("postDetails")
    public Post getPostDetails(String slug) throws IOException {
        List<Post> posts = getPosts();
        return posts.stream()
                .filter(post -> post.getSlug().equals(slug))
                .findFirst()
                .orElse(null);
    }

    public List<Post> loadAllPosts() throws IOException {
        List<Post> postMetaDataList = new ArrayList<>();

        Resource[] resources = resourcePatternResolver.getResources("classpath:posts/*");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d yyyy", Locale.ENGLISH);

        for (Resource resource : resources) {
            InputStream inputStream = resource.getInputStream();
            String content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            Map<String, List<String>> parsedMetaData = markDownService.parseMetaData(content);
            postMetaDataList.add(Post.builder()
                    .title(getValue(parsedMetaData, "title"))
                    .date(getValue(parsedMetaData, "date"))
                    .excerpt(getValue(parsedMetaData, "excerpt"))
                    .author(getValue(parsedMetaData, "author"))
                    .slug(resource.getFilename().replaceFirst("\\.md$", ""))
                    .build());
        }

        return postMetaDataList.stream()
                .sorted(Comparator.comparing((Post post) ->
                        LocalDate.parse(post.getDate(), formatter)
                ).reversed())
                .collect(Collectors.toList());
    }

    private static String getValue(Map<String, List<String>> data, String key) {
        List<String> values = data.get(key);
        return (values != null && !values.isEmpty()) ? values.get(0) : null;
    }
}
