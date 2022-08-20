package com.njmarket.webshopnj.dto;

import com.njmarket.webshopnj.domain.Category;
import com.njmarket.webshopnj.domain.Detail;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class  PostDto {

    private Long memberId;
    private String username;
    private Long postId;
    private String title;
    private Category category;
    private Detail detail;

    public PostDto() {
    }

    @QueryProjection
    public PostDto(Long memberId, String username, Long postId, String title, Category category, Detail detail) {
        this.memberId = memberId;
        this.username = username;
        this.postId = postId;
        this.title = title;
        this.category = category;
        this.detail = detail;
    }
}
