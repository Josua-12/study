package com.example.security.controller;

import com.example.security.entity.Member;
import com.example.security.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(Member member) {
        // 패스워드 암호화
        memberService.register(member);
        return "redirect:/ui/list";     // Book 목록 페이지로 이동
    }

    /*
        메인 페이지 - 책 목록 포함
     */
    @GetMapping("/ui/list")
    public String main(Model model) {
        return "list";
    }
}
