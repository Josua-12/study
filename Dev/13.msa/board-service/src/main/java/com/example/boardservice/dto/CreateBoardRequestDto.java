package com.example.boardservice.dto;

/*
    게시글 생성 요청 DTO
        - 클라이언트로부터 새 게시글 작성 요청 시 전달되는 데이터를 담는 객체
        - HTTP POST /boards 요청의 Request Body를 이 DTO로 변환하여 처리
            - Controller에서 @RequestBody로 JSON 데이터를 이 DTO로 자동 변환
            - Service 계층으로 전달되어 Board 엔티티 생성에 사용됨
            - 처리 흐름
                - Client -> Controller -> Service -> Board Entity 생성 -> DB 저장
        - 포함정보
            - title, content,
            - userId : User 테이블의 Primary Key
 */
public class CreateBoardRequestDto {
    private String title;
    private String content;
    private Long userId;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Long getUserId() {
        return userId;
    }
}
