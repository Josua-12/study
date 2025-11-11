package com.example.boardservice.dto;

/*
    사용자 정보 응답 DTO
        - board-service에서 user-service로부터 사용자 정보를 받아올 때 사용하는 객체
        - UserClient를 통해 user-service의 API를 호출하여 받은 응답을 이 DTO로 매핑

        - 서비스 간 통신(Inter-Service Communication)을 위한 DTO
 */
public class UserResponseDto {
    private Long userId;
    private String email;
    private String name;

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
