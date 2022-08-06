package com.njmarket.webshopnj.service;


import com.njmarket.webshopnj.domain.Post;
import com.njmarket.webshopnj.repository.CommentRepository;
import com.njmarket.webshopnj.repository.MemberRepository;
import com.njmarket.webshopnj.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;


    @Transactional
    public void savePost(Post post) {
        postRepository.save(post);
    }

    // TODO: 2022/08/06 findPosts()

    public Post findOne(Long postId) {
        return postRepository.findOne(postId);
    }
}
