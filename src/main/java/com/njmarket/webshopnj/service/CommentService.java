package com.njmarket.webshopnj.service;

import com.njmarket.webshopnj.repository.CommentRepository;
import com.njmarket.webshopnj.repository.MemberRepository;
import com.njmarket.webshopnj.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

}
