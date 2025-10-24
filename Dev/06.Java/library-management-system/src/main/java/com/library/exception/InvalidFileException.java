package com.library.exception;

/*
    * 파일 업로드 검증 실패 시 발생하는 커스텀 예외

    * RuntimeException 상속 이유
        - unchecked exception으로 동작 (try~catch 강제 안함)
        - Spring의 @ControlerAdvice로 전역 처리 가능
        - 비즈니스 로직에서 예외 처리 코드 간소화

    * 발생 상황
        - 허용되지 않은 파일 확장자 업로드 시
        - 파일 크기 제한 초과 시
        - 파일명이 비어있거나 null인 경우
        - MIME 타입 검증 실패

 */
public class InvalidFileException extends  RuntimeException {
    //기본생성자 - 에러 메시지만 전달할 때 사용
    public InvalidFileException(String message) {
        super(message);
    }
    // 원인 예외를 포함하는 생성자
    public InvalidFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
