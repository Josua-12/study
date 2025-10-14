package com.library.service;

import com.library.entity.member.Member;
import com.library.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    /*
        Spring Security가 로그인 처리시 자동 호출
        @param username 로그인 폼에서 입력한 이메일
        @return UserDetails (Member 객체)
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("로그인 시도 - 이메일: {}", username);

        //1. DB에서 이메일로 회원 조회
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> {
                   log.error("❌ 존재하지 않는 사용자: {}", username);
                   return new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
                });

        //2. 회원 정보 로깅
        log.info("✅ 사용자 조회 성공 - ID: {}, 권한: {}",
                                    member.getId(), member.getRole());
        if (member.getPassword() == null || member.getPassword().isEmpty()) {
            log.error("🚨 치명적 오류: password 필드가 비어있음! ");
            log.error("   - Member ID: {}", member.getId());
            log.error("   - Member Email: {}", member.getEmail());
        } else {
            log.info("✅ 비밀번호 확인: {}...(앞 10자)",
                    member.getPassword().substring(0, Math.min(10, member.getPassword().length())));
        }

        //3. 계정 상태 확인
        if (!member.isEnabled()) {
            log.warn("⚠️ 비활성화된 계정: {}", username);
        }

        //4. Member 객체 반환 (UserDetails 구현)
        //Spring Security가 비밀번호 검증 진행
        return member;
    }
}
