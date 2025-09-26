package com.example.security.service;

import com.example.security.entity.CustomerMember;
import com.example.security.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerUserDetailService implements UserDetailsService {
    private final MemberService memberService;
    /*
        Spring security가 사용자 인증 시 자동으로 호출하는 메서드
            - username : 로그인 폼에서 입력한 사용자명
            - UserDetails : Spring Security가 인증에 사용할 사용자 정보 객체
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("=== 로그인 시도 ===");
        System.out.println("Username : " + username);

        try {
            Member member = memberService.findByUsername(username);
            System.out.println("사용자 찾음 : " + member.getName());
            System.out.println("CustomerUserDetailsService : " + member.getUsername());

            // CustomerMember는 UserDetails를 구현하면서 Member 정보도 포함
            return new CustomerMember(member);      // Authentication => View
        } catch (Exception e) {
            System.out.println("사용자 못 찾음 : " + e.getMessage());
            throw e;
        }
    }
}
