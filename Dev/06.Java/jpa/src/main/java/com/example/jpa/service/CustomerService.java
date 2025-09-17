package com.example.jpa.service;

import com.example.jpa.entity.Customer;
import com.example.jpa.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service    // Service Bean으로 등록, 비즈니스 로직 처리 계층
@AllArgsConstructor // 모든 필드를 파라미터로 받는 생성자 자동 생성 (DI용)
public class CustomerService {
    private final CustomerRepository customerRepository;    // 생성자 주입

    // 회원 등록
    @Transactional  // insert, update, delete 메서드 실행 중 예외 발생 시 자동 롤백
    public Customer register(Customer customer) {
        return customerRepository.save(customer);   // save() -> insert SQL
    }

    // 특정 회원 한 명의 정보 가져오기
    @Transactional(readOnly = true)     // 읽기 전용, 성능 최적화
    public Optional<Customer> getById(Long id) {    // PK(id)
        return customerRepository.findById(id);     // SELECT * FROM customer WHERE id = ?
    }
}
