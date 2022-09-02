package com.njmarket.webshopnj.controller;

import com.njmarket.webshopnj.domain.Member;
import com.njmarket.webshopnj.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String home(@SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) Member loginMember,Model model){
        if (loginMember == null) {
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "logged/homeLog";
    }
}
