package com.example.jpa.controller;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Controller     // HTML View 반환
@RequestMapping("/")    // 기본 경로 매핑, 애플리케이션 루트 URL
@AllArgsConstructor
public class MainController {

    private final MessageSource messageSource;        // 으존성 주입

    // URL : GET http://localhost:8081/
    @GetMapping
    public String index(Model model, Locale locale){
        // 현재 언어에 맞는 메시지 가져오기
        String welcomeMsg = messageSource.getMessage("welcome", null, locale);
        String appTitle = messageSource.getMessage("app.title", null, locale);

        model.addAttribute("str", welcomeMsg);
        model.addAttribute("title", appTitle);


        return "index";
    }
}

