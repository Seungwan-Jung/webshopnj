package com.njmarket.webshopnj.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter@Setter
public class Comment {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private LocalDateTime commentDate;

    private String text;

    public static Comment createOrder(Member member, Post post) {
        Comment comment = new Comment();
        comment.setMember(member);
        comment.setPost(post);
        comment.setCommentDate(LocalDateTime.now());
        return comment;
    }
}
