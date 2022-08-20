package com.njmarket.webshopnj.dto;

import com.njmarket.webshopnj.domain.Category;
import com.njmarket.webshopnj.domain.Detail;
import lombok.Data;

@Data
public class PostSearchCondition {

    private String username;
    private String title;
    private Category category;
    private Detail detail;


}
