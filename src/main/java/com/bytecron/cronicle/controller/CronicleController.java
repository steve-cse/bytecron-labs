package com.bytecron.cronicle.controller;

import com.bytecron.cronicle.modal.Post;
import com.bytecron.cronicle.service.CronicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CronicleController {
    private final CronicleService cronicleService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/posts")
    public String getPosts(Model model) {
        List<Post> posts = null;
        try {
            posts = cronicleService.getPosts();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("posts", posts);
        return "fragments/posts :: postList";
    }

    @GetMapping("/post/{slug}")
    public String getPost(@PathVariable String slug,  Model model) {
        String postContent = null;
        try {
            postContent = cronicleService.getPost(slug);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        model.addAttribute("post",postContent);
        return "fragments/post :: postContent";
    }
}

