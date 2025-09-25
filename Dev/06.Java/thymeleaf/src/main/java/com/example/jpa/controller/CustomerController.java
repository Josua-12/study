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
import java.util.Locale;
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
            customer = optional.get();

            // 3)Model에 데이타 담기
            model.addAttribute("customer", customer);
            // templates/detail.html 랜더링

        } else {
            //회원 없음
            throw new IllegalArgumentException("회원을 찾을 수 없습니다. ID: " + id);
        }

        //상세 페이지로 이동
        return "customer/detail";
    }

    @GetMapping("/lists")
    public String lists(Model model) {
        List<Customer> customers = customerService.getAllCustomer();
        model.addAttribute("customers", customers);
        model.addAttribute("totalCount", customers.size());
        return "customer/list";
    }

    /*
        회원 수정 폼 화면
        URL: GET /customer/edit/{id}
     */
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Optional<Customer> optional = customerService.getById(id);

        if (optional.isPresent()) {
            model.addAttribute("customer", optional.get());
        } else {
            throw new IllegalArgumentException("회원을 찾을 수 없습니다. ID: " + id);
        }

        return "customer/editForm";
    }

    /*
    회원 정보 수정 처리 (POST 요청)
    URL: POST /customer/edit/{id}
    @PathVariable - URL 경로의 {id} 값을 받음
    @ModelAttribute - 폼 데이터를 Customer 객체로 자동 바인딩
     */
    @PostMapping("/edit/{id}")
    public String processEdit(@PathVariable Long id,
                              @ModelAttribute Customer customer,
                              RedirectAttributes redirectAttributes) {
        try {
            //수정할 회원 정보 설정
            customer.setId(id);

            //Service를 통해 회원 정보 수정
            Customer updatedCustomer = customerService.update(customer);

            //성공 메시지 설정
            redirectAttributes.addFlashAttribute("successMessage",
                    "회원 정보가 성공적으로 수정되었습니다.");
            //상세 페이지로 리다이렉트
            return "redirect:/customer/detail/" + id;

        } catch (Exception e) {
            // 오류 발생시 처리
            redirectAttributes.addFlashAttribute("errorMessage",
                    "회원 정보 수정 중 오류가 발생했습니다. " + e.getMessage());
            // 수정 폼으로 다시 리다이렉트
            return "redirect:/customer/edit/" + id;
        }
    }

    /*
        회원 삭제 처리 (Soft Delete 방식)
        URL : GET /customer/delete/{id}
         - 실제로 DB에서 삭제하지 않고 deleted 플래그를 true로 설정
         - 삭제 시간도 함께 기록
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         RedirectAttributes redirectAttributes) {
        try {
            // 회원 소프트 삭제
            customerService.softDelete(id);

            // 성공 메시지 성공
            redirectAttributes.addFlashAttribute("successMessage",
                    "회원이 성공적으로 삭제되었습니다.");

        } catch (Exception e) {
            // 오류 메시지 설정
            redirectAttributes.addFlashAttribute("errorMessage",
                    "회원 삭제 중 오류가 발생했습니다." + e.getMessage());
        }
        // 회원 목록으로 리다이렉트
        return "redirect:/customer/lists";
    }

    @GetMapping("/querymethod/query-method")
    @ResponseBody
    public String testQueryMethod() {
        customerService.testQueryMethods();
        return "쿼리 메서드 테스트 완료! 콘솔을 확인하세요.";
    }

    // 간단한 메뉴 페이지
    @GetMapping("/querymethod")
    @ResponseBody
    public String queryMethodMenu() {
        return """
                <html>
                <head>
                    <meta charset="UTF-8">
                    <title>퀴리 메서드 테스트</title>
                    <style>
                        body { font-family: Arial; padding: 20px;}
                        h1 { color: #333; }
                        .test-list { margin: 20px 0; }
                        .test-item { margin: 10px 0; }
                        a { color: #667eea; text-decoration: none; margin-right: 10px;}
                        a:hover { text-decoration: underline; }
                    </style>
                </head>
                <body>
                    <h1>🔭 쿼리 메서드 실행</h1>
                    <hr>
                    <div class="test-list">
                        <div class="test-item">
                            <a href="/customer/query-method/2">2-2. findByUsernameAndPassword</a> - 로그인 체크 
                        </div>
                        <div class="test-item">
                            <a href="/customer/query-method/3">2-3. findByCustomerNameOrOccupation</a> - OR 조건 
                        </div>        
                        <div class="test-item">
                            <a href="/customer/query-method/4">2-4. findByAgeGreaterThan</a> - 나이 초과 
                        </div>       
                        <div class="test-item">
                            <a href="/customer/query-method/5">2-5. findByAgeLessThanEqual</a> - 나이 이하 
                        </div>          
                        <div class="test-item">
                            <a href="/customer/query-method/6">2-6. findByAgeBetween</a> - 범위 검색
                        </div>    
                        <div class="test-item">
                            <a href="/customer/query-method/7">2-7. findByCustomerNameContaining</a> - 포함 검색
                        </div>     
                        <div class="test-item">
                            <a href="/customer/query-method/8">2-8. findByUsernameStartingWith</a> - 시작 문자 검색
                        </div>   
                        <div class="test-item">
                            <a href="/customer/query-method/9">2-9. findByRatingOrderByAgeDesc</a> - 정렬
                        </div>   
                        <div class="test-item">
                            <a href="/customer/query-method/10">2-10. findByAgeBetweenAndRatingOrderByReservesDesc</a> - 복합 조건 + 정렬
                        </div>         
                        <div class="test-item">
                            <a href="/customer/query-method/11">2-11. findByDeletedFalse</a> - Boolean 체크 
                        </div>          
                        <div class="test-item">
                            <a href="/customer/query-method/12">2-12. countByRating</a> - 카운트 
                        </div>     
                        <div class="test-item">
                            <a href="/customer/query-method/13">2-13. existsByUsername</a> - 존재 확인 
                        </div>      
                        <div class="test-item">
                            <a href="/customer/query-method/14">2-13. findTop5ByOrderByReservesDesc</a> - TOP 5
                        </div>                                                                                                                                                                                                                                               
                    </div>
                </body>
                </html>
                """;
    }

    @GetMapping("/query-method/{testNumber}")
    @ResponseBody
    public String runQueryMethods(@PathVariable int testNumber) {
        switch (testNumber) {
            case 2 -> customerService.test2FindByUsernameAndPassword();
            case 3 -> customerService.test3FindByCustomerNameOrOccupation();
            case 4 -> customerService.test4FindByAgeGreaterThan();
            case 5 -> customerService.test5FindByAgeLessThanEqual();
            case 6 -> customerService.test6FindByAgeBetween();
            case 7 -> customerService.test7FindByCustomerNameContaining();
            case 8 -> customerService.test8FindByUsernameStartingWith();
            case 9 -> customerService.test9FindByRatingOrderByAgeDesc();
            case 10 -> customerService.test10FindByAgeBetweenAndRatingOrderByReservesDesc();
            case 11 -> customerService.test11FindByDeletedFalse();
            case 12 -> customerService.test12CountByRating();
            case 13 -> customerService.test13ExistsByUsername();
            case 14 -> customerService.test14FindTop5ByOrderByReservesDesc();
            default -> {
                return "❌ 잘못된 테스트 번호입니다.(2-10 사이의 숫자를 입력하세요.)";
            }
        }
        return "✅ 테스트" +testNumber+ " 실행 완료! 콘솔을 확인하세요.";
    }

    //================== JPQL 메서드 =======================
    @GetMapping("/jpql/{testNumber}")
    @ResponseBody
    public String runJPQLTest(@PathVariable int testNumber) {
        switch (testNumber) {
            case 1 -> customerService.jpqlTest1FindActiveCustomersByRating();
            case 2 -> customerService.jpqlTest2FindPlatinumCustomersInAgeRange();
            case 3 -> customerService.jpqlTest3GetCustomerStaticsByRating();
            case 4 -> customerService.jpqlTest4FindCustomersAboveAverageReserves();
            default -> {
                return "❌ 잘못된 테스트 반환입니다. (1-4 사이의 숫자를 입력하세요)";
            }
        }

        return "✅ JPQL 테스트 " + testNumber+ " 실행 완료! 콘솔을 확인하세요.";
    }

    @GetMapping("/jpql")
    @ResponseBody
    public String jpqlMenu() {
        return """
                <html>
                <head>
                    <meta charset="UTF-8">
                    <title>퀴리 메서드 테스트</title>
                    <style>
                        body { font-family: Arial; padding: 20px;}
                        h1 { color: #333; }
                        .test-list { margin: 20px 0; }
                        .test-item { margin: 10px 0; }
                        a { color: #667eea; text-decoration: none; margin-right: 10px;}
                        a:hover { text-decoration: underline; }
                    </style>
                </head>
                <body>
                    <h1>🔭 JPQL 테스트 실행</h1>
                    <hr>
                    <div class="test-list">
                        <div class="test-item">
                            <a href="/customer/jpql/1">3-1. findActiveCustomersByRating</a> - 기본 JPQL 
                        </div>         
                        
                        <div class="test-item">
                            <a href="/customer/jpql/2">3-2. findPlatinumCustomersInAgeRange</a> - 복잡한 조건 JPQL 
                        </div>          
                        
                        <div class="test-item">
                            <a href="/customer/jpql/3">3-3. getCustomerStaticsByRating</a> - 집계 함수 JPQL 
                        </div>               
                        
                        <div class="test-item">
                            <a href="/customer/jpql/4">3-4. findCustomersAboveAverageReserves</a> - 서브 쿼리 JPQL 
                        </div>                                                                                                                                                                                                                                                                                             
                    </div>
                </body>
                </html>                
                """;
    }

}











