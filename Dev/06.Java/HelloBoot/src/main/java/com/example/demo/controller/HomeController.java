package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// @Controller : 이 클래스가 MVC 패턴의 컨트롤러임을 나타냄
// 사용자의 요청을 받아 처리하고, 응답을 반환하는 역할
// 스프링이 자동으로 new HomeController()를 실행하여 빈으로 등록함
@Controller
public class HomeController {

    public HomeController() {
        System.out.println("HomeController 객체가 생성됨");
    }

    // "/hello" URL로 GET 요청이 오면 이 메서드가 실행됨
    @GetMapping("/hello")
    public String home(Model model) {
        model.addAttribute("message", "스프링부트에 오신 것을 환영합니다!");
        model.addAttribute("timestamp", new java.util.Date());

        // "home" 반환하면 templates/home.html 파일을 찾아 랜더링
        // Thymeleaf 템플릿 엔진이 자동으로 .html 확장자를 붙임
        return "home";
    }
}
