package com.example.jpa.controller;

import com.example.jpa.entity.Customer;
import com.example.jpa.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;

import java.util.Optional;

@Controller     // Spring MVC 컨트롤러, View를 반환 (@RestController와 구분됨)
@RequestMapping("/customer")    // 기본 URL 경로 설정, 모든 메서드는 /customer로 시작
@AllArgsConstructor     // 생성자 주입
public class CustomerController {

    private final CustomerService customerService;      // 서비스 계층 의존성 주입 (DI)

    @GetMapping("/register-test")
    public String register() {
        // 폼에서 받아야 하지만, 테스트용으로 하드코딩
        Customer customer = new Customer();
        customer.setUsername("SpringAI");
        customer.setPassword("12345");
        customer.setAge(30);
        customer.setCustomerName("이순신");
        customer.setRating("GOLD");
        customer.setOccupation("회사원");

        Customer cus =  customerService.register(customer);     // DB 저장
        return "redirect:/";    // 메인 페이지로 리다이렉트 (URL 변경)
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        // 빈 customer 객체를 Model에 추가
        model.addAttribute("customer", new Customer());
        return "/customer/registerForm";
    }

    @PostMapping("/register")   // 폼 제출 처리
    public String processRegister(@ModelAttribute  Customer customer) {
        customerService.register(customer);
        return "redirect:/";
    }

    /*
        회원 상세 조회
            url: GET /customer/detail/1
            @PathVariable - URL 경로의 {id}
     */
    @GetMapping("/detail/{id}")
    public String getDetail(@PathVariable  Long id, Model model) {
        // 1) Service를 통해 회원 조회
        Optional<Customer> optional = customerService.getById(id);

        Customer customer;
        // 2) Optional 처리
        if (optional.isPresent()) {
            customer = optional.get();
        } else {
            // 회원
            throw new IllegalArgumentException("error");
        }

        // Model에 데이타 담기
        model.addAttribute("customer", customer);
        return "detail";        // templates/detail.html 랜더링
    }

}
