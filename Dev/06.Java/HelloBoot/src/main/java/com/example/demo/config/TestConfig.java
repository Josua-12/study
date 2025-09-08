package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration : 이 클래스가 스프링 설정 클래스임
@Configuration
public class TestConfig {
    // 스프링 컨테이너 시작될 때 가장 먼저 실행됨
    public TestConfig() {
        System.out.println("TestConfig()");
    }
    
    // @Bean : 메서드가 반환하는 객체를 스프링 빈으로 등록함
    @Bean
    public MyBean myBean() {
        System.out.println("myBean() 메서드 호출");
        return new MyBean();    // MyBean 객체를 생성하여 스프링 컨테이너에 등록
    }
}