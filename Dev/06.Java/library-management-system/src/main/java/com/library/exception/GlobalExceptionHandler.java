package com.library.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

/*
       전역 예외 처리 핸들러 - 애플리케이션 전체에서 발생하는 예외를 중앙에서 처리
       @ControllerAdvice
            - Spring의 AOP를 활용한 전역 예외 처리 메커니즘
                - 모든 @Controller에서 발생하는 예외를 한 곳에서 처리
                - 코드 중복 제거 및 일관된 에러 응답 제공
                - Controller에서 try~catch 불필요

      예외 처리 우선순위 (구체적인 것 => 일반적인 것)
        1) InvalidFileException (파일 검증 실패)
        2) MaxUploadSizeExceededException (Spring 파일 크기 제한)
        3) RuntimeException (일반 런타임 에러)
        4) Exception (모든 예외의 최종 방어선)
 */
@ControllerAdvice   // 모든 Controller에 적용되는 전역 예외 처리
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidFileException.class)
    public ModelAndView handleInvalidFileException(InvalidFileException e) {
        log.error("파일 검증 실패: {}", e.getMessage());

        ModelAndView mav = new ModelAndView("error/file-error");
        mav.addObject("errorTitle", "파일 업로드 실패");
        mav.addObject("errorMessage", e.getMessage());
        mav.addObject("errorDetail", "다시 시도해주세요. 문제가 계속되면 관리자에게 문의하세요.");
        mav.setStatus(HttpStatus.BAD_REQUEST);

        return mav;
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.error("파일 크기 초과: {}", e.getMessage());

        ModelAndView mav = new ModelAndView("error/file-error");
        mav.addObject("errorTitle", "파일 크기 초과");
        mav.addObject("errorMessage", "업로드 가능한 최대 파일 크기는 10MB입니다.");
        mav.addObject("errorDetail", "더 작은 파일을 선택하거나 파일을 압축해주세요.");
        mav.setStatus(HttpStatus.BAD_REQUEST);

        return mav;
    }

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleRuntimeException(RuntimeException e) {
        log.error("런타입 예외 발생: {}", e.getMessage());

        ModelAndView mav = new ModelAndView("error/file-error");
        mav.addObject("errorTitle", "오류 발생");
        mav.addObject("errorMessage", e.getMessage());
        mav.addObject("errorDetail", "요청을 처리하는 중 문제가 발생했습니다.");
        mav.setStatus(HttpStatus.BAD_REQUEST);

        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handlerException(Exception e) {
        log.error("예외 발생: {}", e.getMessage(), e);

        ModelAndView mav = new ModelAndView("error/file-error");
        mav.addObject("errorTitle", "시스템 발생");
        mav.addObject("errorMessage", "일시적인 오류가 발생했습니다.");
        mav.addObject("errorDetail", "잠시 후 다시 시도해주세요.");
        mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        return mav;

    }
}
