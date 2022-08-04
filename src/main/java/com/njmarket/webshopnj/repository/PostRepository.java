package com.njmarket.webshopnj.repository;

import com.njmarket.webshopnj.domain.Member;
import com.njmarket.webshopnj.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;

    public void save(Post post) {
        em.persist(post);
    }

    public Post findOne(Long id) {
        return em.find(Post.class, id);
    }

    // TODO: 2022/07/23 findby title, content , category,detail

    // TODO: 2022/07/23 findAll 동적쿼리 querydsl

}
