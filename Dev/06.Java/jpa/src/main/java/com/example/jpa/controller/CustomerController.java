package com.example.jpa.controller;

import com.example.jpa.entity.Customer;
import com.example.jpa.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller     // Spring MVC 컨트롤러, View를 반환 (@RestController와 구분됨)
@RequestMapping("/customer")    // 기본 URL 경로 설정, 모든 메서드는 /customer로 시작
@AllArgsConstructor     // 생성자 주입을 위한 Lombok 어노테이션
public class CustomerController {

    private final CustomerService customerService;  // 서비스 계층 의존성 주입 (DI)

    @GetMapping("/register-test")
    public String register() {
        // 폼에서 받아야 하지만, 테스트용으로 하드코딩
        Customer customer = new Customer();
        customer.setUsername("springAI");
        customer.setPassword("12345");
        customer.setAge(30);
        customer.setCustomerName("이순신");
        customer.setRating("GOLD");
        customer.setOccupation("회사원");

        Customer cus = customerService.register(customer);  // DB 저장
        return "redirect:/";        //메인 페이지로 리다이렉트 (URL 변경)
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        // 빈 customer 객체를 Model에 추가
        model.addAttribute("customer", new Customer());
        return "customer/registerForm";
    }

    /*
        회원 등록 처리 (POST 요청)
         - @ModelAttribute가 폼 데이터를 Customer 객체로 자동 바인딩
         - RedirectAttributes : 리다이렉트시 데이타 전달
            - FlashAttribute : 리다이렉트 후 한 번만 사용되고 사라지는 데이터
                - PRG 패턴 (Post-Redirect-Get) 구현시 메시지 전달용
     */
    @PostMapping("/register")  // 폼 제출 처리
    public String processRegister(@ModelAttribute Customer customer,
                                  RedirectAttributes redirectAttributes) {
        try {
            customerService.register(customer);
            redirectAttributes.addFlashAttribute("successMessage",
                    "회원 등록이 완료되었습니다.");
            return "redirect:/customer/lists";

        } catch (DataIntegrityViolationException e) {  // DB 제약조건 위반 예외 처리(UNIQUE)
            if (e.getMessage().contains("username") ||
                e.getMessage().contains("Duplicate entry")) {
                    redirectAttributes.addFlashAttribute("errorMessage",
                            "이미 사용중인 아이디입니다. 다른 아이디를 입력해주세요.");
            } else {
                // 기타 DB 제약조건 위반
                    redirectAttributes.addFlashAttribute("errorMessage",
                            "회원 등록 중 오류가 발생했습니다.");
            }
            // 입력했던 데이터 유지
            redirectAttributes.addFlashAttribute("customer", customer);

            return "redirect:/customer/register";
        } catch (Exception e) {
            // 예상하지 못한 예외 처리
            redirectAttributes.addFlashAttribute("errorMessage",
                    "예상하지 못한 오류가 발생했습니다: " + e.getMessage());
            return "redirect:/customer/register";
        }

    }

    /*
     회원 상세 조회
        url: GET /customer/detail/1
        @PathVariable - URL 경로의 {id} 값을 파라미터로 받음
     */
    @GetMapping("/detail/{id}")
    public String getDetail(@PathVariable Long id, Model model) {
        //1) Service를 통해 회원 조회
        Optional<Customer> optional = customerService.getById(id);

        Customer customer;
        //2) Optional처리
        if (optional.isPresent()) {
            customer =  optional.get();

            // 3)Model에 데이타 담기
            model.addAttribute("customer", customer);

        } else {
            //회원 없음
            throw new IllegalArgumentException("회원을 찾을 수 없습니다. ID : " + id);
        }

        // 상세 페이지로 이동
        return "customer/detail";        // templates/detail.html 랜더링
    }

    @GetMapping("/lists")
    public String lists(Model model) {
        List<Customer> customers = customerService.getAllCustomer();
        model.addAttribute("customers", customers);
        model.addAttribute("totalCounts", customers.size());
        return "customer/list";
    }

    /*
        회원 수정 폼 화면
        URL : GET/customer/edit/{id}
     */
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Optional<Customer> optional = customerService.getById(id);

        if (optional.isPresent()) {
            model.addAttribute("customer", optional.get());
        } else {
            throw new IllegalArgumentException("회원을 찾을 수 없습니다. ID : " + id);
        }
        return "customer/editForm";
    }
}











