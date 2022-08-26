package com.njmarket.webshopnj.service;

import com.njmarket.webshopnj.domain.Member;
import com.njmarket.webshopnj.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String userid, String password) {
        return memberRepository.findByUserid(userid)
                .filter(member -> member.getPassword().equals(password))
                .orElse(null);
    }
}
