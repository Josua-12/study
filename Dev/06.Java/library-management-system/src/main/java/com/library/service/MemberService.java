package com.library.service;

import com.library.dto.member.MemberRegistrationDTO;
import com.library.dto.member.MemberResponseDTO;
import com.library.entity.member.Member;
import com.library.entity.member.MemberStatus;
import com.library.entity.member.MemberType;
import com.library.entity.member.Role;
import com.library.repository.MemberRepository;
import com.library.util.MaskingUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/*
    * 서비스 레이어 주요 책임:
    - 회원 가입/조회/수정/탈퇴 비즈니스 로직
    - 비밀번호 암호화 보안 처리
        - BCrypt 기반 비밀번호 암호화
    - 회원 상태 및 등급 관리
    - 이메일 중복 체크 등 유효성 검증
    - 보안 기능
        - 중복 가입 방지
        * - 개인정보 처리 시 로깅 마스킹
        * - 트랜잭션 기반 데이터 무결성 보장
        *

    * 회원 가입 처리 단계:
        - 1) 이메일 중복 체크 (유니크 제약 검증)
        - 2) 비밀번호 BCrypt 암호화
        - 3) 기본값 자동 설정 (등급, 상태, 권한)
        - 4) 가입일시 자동 기록
        - 5) 데이터베이스 저장
        - 6) 가입 완료 로깅 밍 통계 업데이트
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)     // 읽기 전용 트랜잭션 (성능 최적화)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional // 쓰기 트랜잭션 (readonly = false)
    public MemberResponseDTO register(MemberRegistrationDTO registrationDTO) {
        String maskedEmail = MaskingUtils.maskEmail(registrationDTO.getEmail());
        log.info("=== 👤 회원가입 비즈니스 로직 시작: {} ===", maskedEmail);

        try {
            // 1. DTO 유효성 검사
            log.info("DTO 유효성 검사 진행 중...");

            // 비밀번호 확인 검증
            if (!registrationDTO.isPasswordMatching()) {
                log.error("❌ 비밀번호 불일치 감지: {}", maskedEmail);
                log.error("   |__ 비밀번호와 확인 비밀번호가 다름");

                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }

            // 약관 동의 확인
            if (!registrationDTO.isAllTermsAgreed()) {
                log.error("❌ 약관 동의 누락: {}", maskedEmail);
                log.error("   |__ 필수 약관에 동의하지 않음");

                throw new IllegalArgumentException("필수 약관에 동의해야 합니다.");
            }

            log.info("DTO 유효성 검사 통과");

            // === 2. 이메일 중복 체크 (필수 검증) ===
            log.info("이메일 중복 체크 진행 중...");

            if (memberRepository.existsByEmail(registrationDTO.getEmail())) {
                log.error("❌ 이메일 중복 감지: {}", maskedEmail);
                log.error("   |__ 기존 회원과 동일한 이메일로 가입 시도");

                throw new IllegalArgumentException("이미 사용중인 이메일입니다."
                                            + registrationDTO.getEmail());
            }
            log.info("이메일 중복 체크 통과");
            log.info("  |___ 새로운 이메일 확인: {} ", maskedEmail);

            // === 3. DTO -> Entity 변환 ===
            log.info("DTO에서 Entity로 변환 중...");

            Member member = Member.builder()
                    .email(registrationDTO.getEmail())
                    .password(registrationDTO.getPassword())    // 아직 평문, 밑에서 암호화
                    .name(registrationDTO.getName())
                    .phone(registrationDTO.getPhone())
                    .address(registrationDTO.getAddress())
                    .build();
            log.info("Entity 변환 완료");

            // === 4. 비밀번호 암호화 (보안 핵심) ===
            log.info("비밀번호 암호화 처리 중...");

            String originalPassword = registrationDTO.getPassword();
            if (originalPassword == null || originalPassword.trim().isEmpty()) {
                log.error("❌ 비밀번호가 비어있음");
                throw new IllegalArgumentException("비밀번호는 필수 입력값입니다.");
            }

            // BCrypt 해시 생성
            String encodedPassword = passwordEncoder.encode(originalPassword);
            member.setPassword(encodedPassword);

            log.info("✅ 비밀번호 암호화 완료");
            log.info("   |- 알고리즘 : BCrypt");
            log.info("   |- 원본 길이 : {}자 ", originalPassword.length());
            log.info("   |- 암호화 결과 길이 : {}자 ", encodedPassword.length());

            // === 5. 기본값 자동 설정 (비즈니스 규칙) ===

            // 가입일시 설정 (시스템 시간 기준)
            if (member.getJoinDate() == null) {
                LocalDateTime joinTime = LocalDateTime.now();
                member.setJoinDate(joinTime);
                log.info("   |- 가입일시 : {} ", joinTime);
            }

            // 회원 등급 설정 (신규 회원은 일반등급)
            if (member.getMemberType() == null) {
                member.setMemberType(MemberType.REGULAR);
                log.info("   |- 회원등급 : {} ({}권 대출 가능) ",
                                MemberType.REGULAR.getDescription(),
                                MemberType.REGULAR.getMaxRentalBooks());
            }

            // 계정 상태 설정 (신규 회원은 활성 상태)
            if (member.getStatus() == null) {
                member.setStatus(MemberStatus.ACTIVE);
                log.info("   |- 계정상태 : {} (로그인 가능) ",
                        MemberStatus.ACTIVE.getDescription());
            }

            // 시스템 권한 설정 (신규 회원은 일반 사용자)
            if (member.getRole() == null) {
                member.setRole(Role.USER);
                log.info("   |- 시스템 권한 : {} ({}) ",
                        Role.USER.getDescription(),
                        Role.USER.getKey());
            }

            log.info("✅ 기본값 설정 완료");

            // === 6. 데이터베이스 저장 (영속화) ===
            log.info("데이터베이스 저장 중...");

            Member savedMember = memberRepository.save(member);

            // === 7. 저장 결과 검증 ===
            if (savedMember.getId() == null) {
                log.error("❌ 회원 저장 실패 -  ID가 할당되지 않음");
                throw new RuntimeException("회원 정보 저장에 실패했습니다.");
            }

            // (추가적인 로그 추가 나중에..)
            log.info("=== ✅ 회원가입 비즈니스 로직 완료 ===");

            log.info(" Entity에서 ResponseDTO로 변환 중....");

            MemberResponseDTO responseDTO = MemberResponseDTO.from(savedMember);

            return responseDTO;

        } catch (IllegalArgumentException e) {
            // 비즈니스 규칙 위반 (이메일 중복 등)
            log.error("회원가입 비즈니스 규칙 위반 : {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            // 시스템 오류 (DB 연결 실패, 암호화 오류 등)
            log.error("회원가입 시스템 오류 발생!");
            throw new RuntimeException("회원 가입 중 시스템 오류가 발생했습니다.", e);
        }
    }

    // 이메일 존재 여부 확인 (중복 체크용)
    public boolean existsByEmail(String email) {
        String maskedEmail = MaskingUtils.maskEmail(email);
        log.info("이메일 존재 여부 확인: {} ", maskedEmail);

        try {
            boolean exists = memberRepository.existsByEmail(email);

            log.info("이메일 중복 체크 결과 : {} -> {}",
                        maskedEmail, exists ? "이미 사용중" : "사용 가능");

            if (exists) {
                log.info("    |__ 기존 회원의 이메일과 일치");
            } else {
                log.info("    |__ 새로운 이메일 확인함");
            }

            return exists;

        } catch (Exception e) {
            log.error(" 이메일 존재 여부 확인 중 오류 : {} ", maskedEmail);
            log.error("   |__ 안전을 위해 중복으로 판정");
            return true;
        }
    }
}





















