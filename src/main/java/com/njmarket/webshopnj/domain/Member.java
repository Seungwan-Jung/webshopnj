package com.njmarket.webshopnj.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter@Setter
public class Member {

    public Member(String userid, String password, String name) {
        this.userid = userid;
        this.password = password;
        this.name = name;
    }

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotEmpty
    private String userid;

    @NotEmpty
    private String password;

    @NotEmpty
    private String name;

    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    public Member() {

    }
}
