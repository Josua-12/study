package com.example.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomerUserDetailsService implements UserDetailsService {
    /*
        Spring Security가 사용자 인증 시 자동으로 호출하는 메서드
            - username : 로그인 폼에서 입력한 사용자명
            - UserDetails : Spring Security가 인증에 사용할 사용자 정보 객체
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("====로그인 시도====");
        System.out.println("Username : " + username);



        return null;
    }
}
