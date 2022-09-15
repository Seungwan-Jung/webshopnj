package com.njmarket.webshopnj.controller;


import com.njmarket.webshopnj.domain.Post;
import com.njmarket.webshopnj.dto.PostDto;
import com.njmarket.webshopnj.dto.PostSearchCondition;
import com.njmarket.webshopnj.service.PostService;
import com.njmarket.webshopnj.web.PostForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping(value = "/posts/new")
    public String createForm(Model model) {

        model.addAttribute("form", new PostForm());
        return "posts/createPostForm";
    }

    @PostMapping(value = "/posts/new")
    public String create(PostForm form, @RequestParam MultipartFile file, HttpServletRequest request) throws IOException {

        Post post = new Post();
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());
        post.setCategory(form.getCategory());
        post.setDetail(form.getDetail());

        postService.savePost(post);

        log.info("request={}",request);
        log.info("multipartFile={}", file);

        if (!file.isEmpty()) {
            String fullPath = fileDir + file.getOriginalFilename();
            log.info("file save fullPath={}", fullPath);
            file.transferTo(new File(fullPath));
        }
        return "redirect:/posts";
    }

    // TODO: 2022/08/06 /posts

    @GetMapping(value = "/posts")
    public String list(@ModelAttribute("postSearch") PostSearchCondition postSearchCondition ,Model model) {
        List<PostDto> posts = postService.findPosts(postSearchCondition);
        model.addAttribute("posts", posts);
        return "posts/postList";
    }
}
