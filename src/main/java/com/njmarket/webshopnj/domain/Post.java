package com.njmarket.webshopnj.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter@Setter
public class Post {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<UploadFile> uploadFiles = new ArrayList<>();

    private LocalDateTime postDate;

    private String title;
    private String content;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Detail detail;



    public static Post createPost(Member member) {
        Post post = new Post();
        post.setMember(member);
        post.setPostDate(LocalDateTime.now());
        return post;
    }

    public static Post createPostTest(Post post,Member member,String title, String content,Category category, Detail detail) {
        post.setMember(member);
        post.setPostDate(LocalDateTime.now());
        post.setTitle(title);
        post.setContent(content);
        post.setCategory(category);
        post.setDetail(detail);
        return post;
    }
}
