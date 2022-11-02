package com.njmarket.webshopnj.service;

import com.njmarket.webshopnj.domain.*;
import com.njmarket.webshopnj.repository.MemberRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static com.njmarket.webshopnj.domain.Post.createPostTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Commit
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @PersistenceContext
    EntityManager em;

    @BeforeEach
    public void before(){
        Member member1 = new Member("member1","12","mem1");
        Member member2 = new Member("member2","123","mem2");
        Member member3 = new Member("member3","1234","mem3");

        Post post1 = new Post();
        Post post2 = new Post();
        Post post3 = new Post();
        Post post4 = new Post();
        Post post5 = new Post();
        Post post6 = new Post();
        Post post7 = new Post();
        Post post8 = new Post();
        Post post9 = new Post();

        createPostTest(post1,member1, "title1", "content1", Category.SHARE, Detail.BEAUTY);
        createPostTest(post2,member2, "title2", "content2", Category.SHARE, Detail.CLOTH);
        createPostTest(post3,member3, "title3", "content3", Category.SHARE, Detail.EDUCATION);
        createPostTest(post4,member1, "title4", "content4", Category.COMMUNITY, Detail.BEAUTY);
        createPostTest(post5,member2, "title5", "content5", Category.COMMUNITY, Detail.CLOTH);
        createPostTest(post6,member3, "title6", "content6", Category.COMMUNITY, Detail.ELECTRONICS);
        createPostTest(post7,member1, "title7", "content7", Category.SELL, Detail.HEALTH);
        createPostTest(post8,member2, "title8", "content8", Category.SELL, Detail.HEALTH);
        createPostTest(post9,member3, "title9", "content9", Category.SELL, Detail.CLOTH);
        
        em.persist(member2);
        em.persist(member1);
        em.persist(member3);
        
        em.persist(post1);
        em.persist(post2);
        em.persist(post3);
        em.persist(post4);
        em.persist(post5);
        em.persist(post6);
        em.persist(post7);
        em.persist(post8);
        em.persist(post9);
        
        em.flush();
        em.clear();

        List<Member> members = em.createQuery("select  m from Member m", Member.class)
                .getResultList();
        for (Member member : members) {
            System.out.println("member.getUserid() = " + member.getUserid());
            System.out.println("member.getName() = " + member.getName());
            System.out.println("member.getPassword() = " + member.getPassword());

        }

        List<Post> posts = em.createQuery("select p from Post p", Post.class)
                .getResultList();
        for (Post post : posts) {
            System.out.println("post.getMember() = " + post.getMember().getName());
            System.out.println("post.getTitle() = " + post.getTitle());
            System.out.println("post.getContent() = " + post.getContent());
            System.out.println("post.getCategory() = " + post.getCategory());
            System.out.println("post.getDetail() = " + post.getDetail());
        }

    }

    @Test
    public void test() throws Exception{
        //이름으로 멤버 검색
//        String qlString = "select m from Member m " + "where m.name = :name";
//
//        Member findMember = em.createQuery(qlString, Member.class)
//                .setParameter("name", "mem1")
//                .getSingleResult();
//
//        System.out.println("findMember name = " + findMember.getName());
//
//        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
//        QMember m = new QMember("m");
//
//        Member findMember2 = queryFactory
//                .select(m)
//                .from(m)
//                .where(m.name.eq("mem1"))
//                .fetchOne();
//
//        System.out.println("findMember2.getName() = " + findMember2.getName());

        // 게시물 시간, post id순

        // category, detail 필터 게시물

        //내가 작성한 게시물

        // 게시물 검색 (작성자, 제목, 내용)

        // 게시물 페이징

    }

}