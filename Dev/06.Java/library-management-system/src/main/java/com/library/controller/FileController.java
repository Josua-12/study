package com.library.controller;

import com.library.entity.board.BoardFile;
import com.library.repository.BoardFileRepository;
import com.library.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/*
        파일 다운로드 Controller
            - 첨부파일 다운로드 처리
            - 다운로드 횟수 증가
 */

@Controller
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final FileStorageService fileStorageService;
    private final BoardFileRepository boardFileRepository;

    /*
        파일 다운로드
            - 파일 ID로 파일 정보 조회
            - 물리적 파일 로드
            - 다운로드 횟수 증가 (더티체킹)
            - 파일 다운로드 응답 반환
            - URL: GET /files/download/{fileId}
     */
    @GetMapping("/download/{fileId}")
    @Transactional  // 다운로드 횟수 증가를 더티체킹으로 저장
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) {
        // 1. 파일 정보 조회
        BoardFile boardFile = boardFileRepository.findById(fileId)  //DB에서 파일 ID로 BoardFile 엔티티 조회
                .orElseThrow(() -> new RuntimeException("파일을 찾을 수 없습니다."));

        // 2. 물리적 파일 다운로드
        Resource resource = fileStorageService.loadFileAsResouce(   //서버 저장소에서 실제 파일을 Resouce 객체로 로드
                boardFile.getFilePath(),        // 파일이 저장된 디렉토리 경로
                boardFile.getStoredFilename()   // 서버에 저장된 고유 파일명 (UUID + 확장자)
        );

        //3. 다운로드 횟수 증가
        boardFile.increaseDownloadCount();  // 다운로드 횟수 1 증가 (메모리상에서만)
        //@Transactional로 인해 메서드 종료 시 더티체킹으로 자동 UPDATE 쿼리 실행됨

        //4. 파일명 인코딩 (한글 파일명 처리)
        String encodedFilename;     // 인코딩된 파일명을 저장할 변수

        try {
            encodedFilename =       // 원본 파일명을 UTF_8로 URL 인코딩 (한글 깨짐 방지)
            URLEncoder.encode(boardFile.getOriginalFilename(), StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            encodedFilename = boardFile.getOriginalFilename(); // 원본 파일명 그대로 사용
        }

        // 5. 파일 다운로드 응답 반환
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,    // 브라우저가 파일을 다운로드하도록 지시 (attachment) + 다운로드될 파일명 지정
                        "attachment; filename=\"" +encodedFilename+ "\"")
                .body(resource);    // 응답 본문에 파일 데이터(resource) 포함

    }
}
