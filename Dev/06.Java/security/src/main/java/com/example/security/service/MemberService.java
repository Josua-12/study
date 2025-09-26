package com.example.security.service;

import com.example.security.entity.Member;
import com.example.security.entity.Role;
import com.example.security.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public Member register(Member member) {
        //1. 사용자가 입력한 패스워드를 암호화
        String pwd = passwordEncoder.encode(member.getPassword());
        member.setPassword(pwd);

        //2. 회원의 기본 권한(USER, MANAGER, ADMIN)을 저장함
        Role userRole = roleService.findByName("USER");
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        member.setRoles(roles);
        return memberRepository.save(member);
    }

    /*
        username으로 회원 조회 메서드
            - 주로 로그인 시 UserDetailsService에서 호출
            - 리턴 : 조회된 Member 엔티티
     */
    public Member findByUsername(String username) {
        Optional<Member> optional = memberRepository.findByUsername(username);
        if (!optional.isPresent()) {
            throw new UsernameNotFoundException("User not found with username: "
                    + username);
        }
        return optional.get();      // Optional에서 실제 Member 객체 추출하여 반환
    }
}
