package com.example.jpa.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    private final MessageSource messageSource;      // 의존성 주입

    @GetMapping
    public String index(Model model, Locale locale) {
        model.addAttribute("title", "비동기 전송 처리");
        model.addAttribute("message", "Spring Boot와 JavaScript 비동기 통신하기");
        return "index.html";
    }

}
