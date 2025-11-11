package com.example.boardservice.dto;

/*
    사용자 정보 Dto
        - BoardResponseDto에 포함되어 게시글 작성자 정보를 표현하는 간소화된 객체
        - User Service로 부터 받은 UserResponseDto에서 필요한 필드만 추출하여 사용
        - 사용 목적
            - 게시글 조회 시 작성자의 기본 정보만 표시
            - 클라이언트에게 간결한 응답 제공

    데이터 흐름
        - User Service (UserResponseDto) --> UserClient 수신
            --> BoardService에서 UserDto로 변환 --> BoardResponseDto에 포함
            --> Client 전달
 */
public class UserDto {
    private Long userId;
    private String name;

    public UserDto(Long userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
