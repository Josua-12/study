package com.library.dto;

import com.library.entity.Member;
import com.library.entity.MemberStatus;
import com.library.entity.MemberType;
import com.library.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
    회원 정보 응답 DTO
        - 사용목적
            - 회원 정보 조회 결과 반환
            - 민감한 정보 (비밀번호, 계좌정보) 제외
            - API 응답 표준화
        - 변환
            - Member Entity => MemberResponseDTO (from)
            - 단방향 변환만 지원
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDTO {
    //기본 식별 정보
    private Long id;
    private String email;
    private String name;
    private String phone;
    private String address;

    //회원 상태 정보
    private MemberType memberType;
    private MemberStatus status;
    private Role role;
    private LocalDateTime joinDate;
    private LocalDateTime updatedAt;

    public static MemberResponseDTO from(Member member) {
        if (member == null) return null;

        return MemberResponseDTO.builder()
                .id(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .phone(member.getPhone())
                .address(member.getAddress())
                .memberType(member.getMemberType())
                .status(member.getStatus())
                .role(member.getRole())
                .joinDate(member.getJoinDate())
                .updatedAt(member.getUpdatedAt())
                .build();
    }
}


















