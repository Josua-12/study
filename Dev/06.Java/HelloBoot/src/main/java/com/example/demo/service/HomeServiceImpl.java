package com.example.demo.service;

import com.example.demo.entity.Member;
import com.example.demo.repository.HomeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service : Spring의 Service 계층 컴포넌트로 등록
// Spring Container가 Bean으로 관리
// 비즈니스 로직을 처리하는 계층임, 트랜잭션 처리는 주로 이 계층에서 일어남
@Service
public class HomeServiceImpl implements HomeService {
    // HomeRepository 의존성 선언
    // 생성자 주입을 통해 초기화
    private final HomeRepository homeRepository;

    public HomeServiceImpl(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    @Override
    public List<Member> memberList() {
        /*
            JpaRepository의 findAll() 메소드 호출
                - JAP가 "SELECT * FROM MEMBER" 쿼리 자동 생성 및 실행
                - 결과를 MEMBER 엔티티 리스트로 자동 매핑
                - 트랜잭션은 기본적으로 읽기 전용 (readonly)
         */
        return homeRepository.findAll();
    }
}
