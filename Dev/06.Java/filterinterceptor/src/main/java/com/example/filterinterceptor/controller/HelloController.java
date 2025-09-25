package com.example.filterinterceptor.controller;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(Model model) {
        System.out.println("스프링 부트 요청 테스트");
        model.addAttribute("data","Spring Boot");
        return "helloView";
    }
}
