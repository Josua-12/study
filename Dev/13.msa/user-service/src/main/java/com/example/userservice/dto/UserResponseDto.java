package com.example.userservice.dto;

/*
    사용자 정보 응답 DTO
        - 서버에서 클라이언트로 사용자 정보를 반환할 때 사용하는 객체
        - User 엔티티의 민감한 정보 (비밀번호 등)를 제외하고 필요한 정보만 전달
        - 사용 상황
            - 회원가입 성공 후 생성된 사용자 정보 반환
            - 사용자 조회 API 응답
            - 다른 마이크로서비스(board-service 등)에서 사용자 정보 요청 시 응답
 */
public class UserResponseDto {
    private Long userId;
    private String email;
    private String name;

    public UserResponseDto(Long userId, String email, String name) {
        this.userId = userId;
        this.email = email;
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
