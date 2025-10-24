package com.library.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
public class FileStorageServiceTest {

    @Autowired
    private FileStorageService fileStorageService;

    private static final long MAX_FILE_SIZE = 1024 * 1024 * 10;     // 10MB

    @Test
    @DisplayName("정상 파일 업로드 - JPG 파일")
    void testValidateFile_ValidJpg() {
        // given - JPG 이미지 파일 생성

        byte[] content = "test image content".getBytes(); // 테스트용 더미 이미지 내용 생성

        MultipartFile file = new MockMultipartFile(     // Spring의 Mock 파일 객체 생성
                "file",
                "test.jpg",
                "image/jpeg",
                content
        );

        // when & then -- 예외가 발생하지 않아야 함
        fileStorageService.validateFile(file);
    }


    @Test
    @DisplayName("정상 파일 업로드 - PDF 파일")
    void testValidateFile_ValidPdf() {
        // given - PDF  파일 생성

        byte[] content = "test PDF content".getBytes(); // 테스트용 더미 이미지 내용 생성

        MultipartFile file = new MockMultipartFile(     // Spring의 Mock 파일 객체 생성
                "file",
                "document.pdf",
                "application/pdf",
                content
        );

        // when & then -- 예외가 발생하지 않아야 함
        fileStorageService.validateFile(file);
    }

}






















