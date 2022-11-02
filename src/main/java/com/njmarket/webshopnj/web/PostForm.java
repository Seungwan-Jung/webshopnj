package com.njmarket.webshopnj.web;

import com.njmarket.webshopnj.domain.Category;
import com.njmarket.webshopnj.domain.Detail;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter @Setter
public class PostForm {

    private String title;
    private String content;
    private Category category;
    private Detail detail;
    private List<MultipartFile> uploadFiles;
}
