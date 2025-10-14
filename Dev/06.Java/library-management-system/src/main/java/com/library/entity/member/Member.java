package com.library.entity.member;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*  * 회원 정보를 관리하는 JPA 엔터티
    * Spring Security 연동하여 인증/인가 처리
    *   - UserDetails 인터페이스 구현
    *   - 권한 기반 접근 제어 (ROLE_USER, ROLE_LIBRARIAN, ROLE_ADMIN)
    *   - 계정 상태별 로그인 제어 (활성/정지/탈퇴)
 */
@Entity
@Table(name = "members")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @Column(nullable = false, unique = true, length = 100)
    private String email;       // 이메일 (로그인 ID로 사용)
    @Column(nullable = false)
    private String password;    // 패스워드 (암호화되어 저장됨)
    @Column(nullable = false, length = 50)
    private String name;
    @Column(length = 20)
    private String phone;
    @Column(length = 200)
    private String address;
    @Column(name = "join_date")
    private LocalDateTime joinDate;

    @Enumerated(EnumType.STRING)    // Enum 타입을 DB에 저장하는 방법 지정하는 어노테이션. => "문자열로 저장"
    @Column(name = "member_type", length = 20)
    @Builder.Default
    private MemberType memberType = MemberType.REGULAR;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    @Builder.Default
    private MemberStatus status = MemberStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 20)
    @Builder.Default
    private Role role = Role.USER;


    // 감사 로그 (시스템 추적 정보)
    @Column(name = "created_at")
    private LocalDateTime createdAt;    // 회원가입 완료 시점 기록 (immutable, 데이터 감사 추적용)
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;    // 회원정보 변경 시마다 자동 업데이트 (변경이력추적용)


    /*
        엔티티 최조 저장 전 실행되는 콜백
            *자동 설정 항목:
            - joinDate: 가입일 = 현재 시간
            - createdAt: 생성일 = 현재 시간
            - updatedAt: 수정일 = 현재 시간

            *memberRepository.save() 최초 실행 시

     */
    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        joinDate = now;
        createdAt = now;
        updatedAt = now;
    }

    /*
        엔티티 수정 전 실행되는 콜백
            * 엔티티 필드 변경 후 트랜잭션 커밋 시
     */
    @PreUpdate
    protected  void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    /*
        Spring Security가 권한 목록 반환
                        권한 확인 시 사용
            - 권한
                - Role enum (USER, ADMIN, LIBRARIAN)
                - Spring Security Authority (ROLE_USER, ROLE_ADMIN, ROLE_LIBRARIAN)
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        // Role.USER => ROLE_USER 형태로 변환
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        return authorities;
    }

    /*
        Spring Security 인증용 비밀번호 반환
            -로그인 시 : PasswordEncoder가 입력 비밀번호와 DB의 암호화된 비밀번호를 비교
     */
    @Override
    public String getPassword() {
        return password;
    }

    /*
        Spring Security가 로그인 시 사용하는 username
        @return 이메일을 username으로 사용
     */
    @Override
    public String getUsername() {
        return email;
    }
}
