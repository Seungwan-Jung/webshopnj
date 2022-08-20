package com.njmarket.webshopnj.repository;

import com.njmarket.webshopnj.domain.Category;
import com.njmarket.webshopnj.domain.Detail;
import com.njmarket.webshopnj.domain.Post;
import com.njmarket.webshopnj.dto.PostDto;
import com.njmarket.webshopnj.dto.PostSearchCondition;
import com.njmarket.webshopnj.dto.QPostDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.njmarket.webshopnj.domain.QMember.member;
import static com.njmarket.webshopnj.domain.QPost.post;
import static org.springframework.util.StringUtils.hasText;


@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;


    public void save(Post post) {
        em.persist(post);
    }

    public Post findOne(Long id) {
        return em.find(Post.class, id);
    }

    public List<PostDto> findAll(PostSearchCondition postSearchCondition) {
        return queryFactory
                .select(new QPostDto(
                        member.id,
                        member.name,
                        post.id,
                        post.title,
                        post.category,
                        post.detail))
                .from(post)
                .leftJoin(post.member,member)
                .where(usernameEq(postSearchCondition.getUsername()),
                        titleEq(postSearchCondition.getTitle()),
                        categoryEq(postSearchCondition.getCategory()),
                        detailEq(postSearchCondition.getDetail()))
                .fetch();

    }

    private BooleanExpression usernameEq(String username){
        return !hasText(username) ? null : member.name.eq(username);
    }

    private BooleanExpression titleEq(String title) {
        return !hasText(title) ? null : post.title.eq(title);
    }

    private BooleanExpression categoryEq(Category category) {
        return !hasText(category.toString()) ? null : post.category.eq(category);
    }

    private BooleanExpression detailEq(Detail detail) {
        return !hasText(detail.toString()) ? null : post.detail.eq(detail);
    }

    // TODO: 2022/07/23 findby title, content , category,detail,author


    // TODO: 2022/07/23 findAll 동적쿼리 querydsl



}
