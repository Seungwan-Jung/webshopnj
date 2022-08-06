package com.njmarket.webshopnj.web;


import com.njmarket.webshopnj.domain.Post;
import com.njmarket.webshopnj.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping(value = "/posts/new")
    public String createForm(Model model) {

        model.addAttribute("form", new PostForm());
        return "posts/createPostForm";
    }

    @PostMapping(value = "/posts/new")
    public String create(PostForm form) {

        Post post = new Post();
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());
        post.setCategory(form.getCategory());
        post.setDetail(form.getDetail());

        postService.savePost(post);
        return "redirect:/posts";
    }

    // TODO: 2022/08/06 /posts
}
