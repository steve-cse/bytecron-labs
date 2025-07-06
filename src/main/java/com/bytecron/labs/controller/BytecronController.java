package com.bytecron.labs.controller;

import com.bytecron.labs.modal.Post;
import com.bytecron.labs.service.BytecronService;
import jakarta.servlet.http.HttpServletRequest;
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
public class BytecronController {
    private final BytecronService bytecronService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/posts")
    public String getPosts(Model model) {
        List<Post> posts = null;
        try {
            posts = bytecronService.getPosts();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("posts", posts);
        return "fragments/posts :: postList";
    }

    @GetMapping("/post/{slug}")
    public String getFullPost(@PathVariable String slug, HttpServletRequest request, Model model) {
        String postContent = null;
        Post postDetails = null;
        try {
            postContent = bytecronService.getPost(slug);
            postDetails = bytecronService.getPostDetails(slug);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("details", postDetails);
        model.addAttribute("post", postContent);

        return "post";
    }

    //TODO: Remove once layout and navigation are setup
    @GetMapping("/partial/post/{slug}")
    public String getPartialPost(@PathVariable String slug, HttpServletRequest request, Model model) {
        String postContent = null;
        Post postDetails = null;
        try {
            postContent = bytecronService.getPost(slug);
            postDetails = bytecronService.getPostDetails(slug);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("details", postDetails);
        model.addAttribute("post", postContent);
        return "fragments/post :: postContent";
    }
}


