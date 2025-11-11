package com.example.boardservice.controller;

import com.example.boardservice.dto.BoardResponseDto;
import com.example.boardservice.dto.CreateBoardRequestDto;
import com.example.boardservice.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping    // POST /boards
    public ResponseEntity<Void> create(     // 응답 본문(Body)이 없음, 상태코드만 반환
            @RequestBody CreateBoardRequestDto createBoardRequestDto  // 요청 본문을 DTO로 바인딩
    ){
        boardService.create(createBoardRequestDto);  // 게시글 생성 로직 호출
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @GetMapping("/{boardId}")       // GET /boards/{boardId} 요청 처리 (특정 게시글 조회)
    public ResponseEntity<BoardResponseDto> getBoard(
            @PathVariable Long boardId      // URL 경로의 {boardId} 값을 메서드 파라미터로 바인딩
    ) {
        BoardResponseDto boardResponseDto = boardService.getBoard(boardId);
        return ResponseEntity.ok(boardResponseDto);     // 200 OK; 조회 성공, 게시글 데이터를 담아 본문에 담아 반환
    }

}
















