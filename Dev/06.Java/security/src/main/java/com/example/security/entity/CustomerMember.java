package com.example.security.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class CustomerMember extends User {
    private Member member;

    // 부모 생성자 생성자 호출
    public CustomerMember(Member member) {
        //로그인 ID, 암호화된 비밀번호, 권한정보변환
        super(member.getUsername(), member.getPassword(),
                                    getAuthorities(member.getRoles()));
        this.member = member;
    }

    // JPA Role 엔티티를 Spring Security의 GrantedAuthority로 변환
    /*
        변환 예시:
         - Role(name="USER") -> ("ROLE_USER")
         - Role(name="ADMIN") -> ("ROLE_ADMIN")
         - Spring Security가 인식하는 권한 객체 켈력션
     */
    private static Collection<? extends GrantedAuthority>
                                    getAuthorities(Set<Role> roles) {
        // "ROLE_" 접두사는 Spring Security의 규칙
        return roles.stream().map(role ->
                        new SimpleGrantedAuthority("ROLE_" + role.getName()))
                        .collect(Collectors.toList());
    }
}
