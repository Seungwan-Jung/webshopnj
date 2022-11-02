package com.njmarket.webshopnj.controller;


import com.njmarket.webshopnj.domain.Member;
import com.njmarket.webshopnj.domain.Post;
import com.njmarket.webshopnj.domain.UploadFile;
import com.njmarket.webshopnj.dto.PostDto;
import com.njmarket.webshopnj.dto.PostSearchCondition;
import com.njmarket.webshopnj.resolver.Login;
import com.njmarket.webshopnj.service.PostService;
import com.njmarket.webshopnj.upload.FileStore;
import com.njmarket.webshopnj.web.PostForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;
    private final FileStore fileStore;

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping(value = "/posts/new")
    public String createForm(@Login Member LoginMember, Model model) {

        model.addAttribute("member", LoginMember);
        model.addAttribute("form", new PostForm());
        return "posts/createPostForm";
    }

    @PostMapping(value = "/posts/new")
    public String create(@Login Member LoginMember,Model model,@ModelAttribute PostForm form, @RequestParam MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
        model.addAttribute("member", LoginMember);

        List<UploadFile> storeFiles = fileStore.storeFiles(form.getUploadFiles());

        Post post = new Post();
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());
        post.setCategory(form.getCategory());
        post.setDetail(form.getDetail());
        post.setUploadFiles(storeFiles);
        postService.savePost(post);

        redirectAttributes.addAttribute("postId", post.getId());

        if (!file.isEmpty()) {
            String fullPath = fileDir + file.getOriginalFilename();
            log.info("file save fullPath={}", fullPath);
            file.transferTo(new File(fullPath));
        }
        return "redirect:/posts/{postId}";
    }

    @GetMapping("/posts/{id}")
    public String postDetail(@Login Member LoginMember,@PathVariable Long id, Model model) {
        model.addAttribute("member", LoginMember);

        Post post = postService.findOne(id);
        model.addAttribute("post", post);
        return "posts/postDetail";
    }

    @ResponseBody
    @GetMapping("/files/{filename}")
    public Resource downloadFile(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }

    // TODO: 2022/11/01 파일 다운로드 필요하다면 추가 .23 

    // TODO: 2022/08/06 /posts

    @GetMapping(value = "/posts")
    public String list(@Login Member LoginMember,@ModelAttribute("postSearch") PostSearchCondition postSearchCondition ,Model model) {
        model.addAttribute("member", LoginMember);

        List<PostDto> posts = postService.findPosts(postSearchCondition);
        model.addAttribute("posts", posts);
        return "posts/postList";
    }
}
