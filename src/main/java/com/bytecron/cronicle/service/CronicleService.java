package com.bytecron.cronicle.service;


import com.bytecron.cronicle.modal.Post;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class CronicleService {
    private final ResourcePatternResolver resourcePatternResolver;
    private final MarkdownService markDownService;
    private final ResourceLoader resourceLoader;

    public List<Post> getPosts() throws IOException {
        List<Post> postMetaDataList = new ArrayList<>();
        Resource[] resources = resourcePatternResolver.getResources("classpath:posts/*");
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
        return postMetaDataList;
    }

    private static String getValue(Map<String, List<String>> data, String key) {
        List<String> values = data.get(key);
        return (values != null && !values.isEmpty()) ? values.get(0) : null;
    }

    public  String getPost(String slug) throws IOException {
        String path = "classpath:posts/" + slug + ".md";
        Resource resource = resourceLoader.getResource(path);
        InputStream inputStream = resource.getInputStream();
        String content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        return markDownService.renderMarkdownToHtml(content);
    }

}
