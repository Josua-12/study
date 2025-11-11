package com.example.boardservice.dto;

/*
    게시물 조회 응답 DTO
        - 게시물 정보와 작성자 정보를 함께 담아 클라이언트에게 반환하는 객체
        - MSA의 API Composition Pattern 구현한 핵심 DTO

    API Composition Pattern
        - Board Service의 게시물 데이터 (Board 테이블)
        - User Service의 사용자 데이터 (UserClient를 통해 HTTP 요청)
        => 두 서비스의 데이터를 조합하여 하나의 응답으로 반환

        - 클라이언트가 게시글 상세 조회 시 작성자 정보도 함께 필요로 할 때
        - 여러 마이크로서비스의 데이터를 하나의 API로 제공

    데이터 조합 과정
        1) BoardRepository에서 Board 엔티티 조회 (게시물 정보 + userId)
        2) UserClient로 User Service에 HTTP 요청 (userId 전달)
        3) User Service로부터 UserDto 응답 수신
        4) Board 정보 + UserDto를 겹합하여 BoardResponseDto 생성
        5) 클라이언트에게 통합된 응답 반환

    MSA 장점 : 클라이언트는 하나의 API 호출로 여러 서비스의 데이터를 받을 수 있음
 */


public class BoardResponseDto {
    private Long boardId;
    private String title;
    private String content;
    private UserDto user;

    public BoardResponseDto(Long boardId, String title, String content, UserDto user) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public Long getBoardId() {
        return boardId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public UserDto getUser() {
        return user;
    }
}


















