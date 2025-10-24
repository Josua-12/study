package com.library.controller;

import com.library.dto.board.CommentCreateDTO;
import com.library.dto.board.CommentDTO;
import com.library.dto.board.CommentUpdateDTO;
import com.library.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    댓글 Controller - 댓글 관련 REST API 제공, JSON 형식으로 요청/응답 처리

    @RestController : @Controller + @ResponseBody (JSON 자동 변환)
    @RequestMapping("/api/comments") : 기본 URL 경로 설정
    @RequiredArgsConstructor : final 필드 생성자 자동 생성
    @Slf4j : 로그 사용
 */
@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;

    /*
        게시글별 댓글 목록 조회 API
            - GET /api/comments/boards/{boardId}
            - 특정 개시글의 모든 활성 댓글 조회
     */
    @GetMapping("/boards/{boardId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByBoardId(
            @PathVariable Long boardId
    ) {
       log.info("댓글 목록 조회 요청 - 게시글 ID: {}", boardId);
       List<CommentDTO> comments = commentService.getCommentsByBoardId(boardId);

       log.info("댓글 목록 조회 완료 - 댓글 수: {}", comments.size());
       return ResponseEntity.ok(comments);  //HTTP 200 OK 상태코드와 댓글 목록을 응답
    }


    /*
        댓글 작성 API
            - POST /api/comments/boards/{boardId}
            - 특정 게시글에 새 댓글 작성
     */
    @PostMapping("/boards/{boardId}")
    public ResponseEntity<CommentDTO> createComment(
          @PathVariable  Long boardId,      // url 경로에서 게시글 ID 추출
          @Valid @RequestBody CommentCreateDTO dto,    // 요청 본문(JSON)을 DTO 변환 후 유효성 검증
          @AuthenticationPrincipal UserDetails userDetails  // Spring Security에서 현재 로그인한 사용자 정보 주입
    ){
        log.info("댓글 작성 요청 - 게시글 ID: {}, 작성자: {}", boardId, userDetails.getUsername());

        String userEmail = userDetails.getUsername();  // Member의 email을 반환

        CommentDTO comment = commentService.createComment(boardId, dto, userEmail);

        log.info("댓글 작성 완료 - 댓글 ID: {}", comment.getId());

        return  ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    /*
        댓글 수정 API - PUT /api/comments/{commentId}
                     - 기존 댓글 내용 수정
     */
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(
        @PathVariable Long commentId,
        @Valid @RequestBody CommentUpdateDTO dto,
        @AuthenticationPrincipal UserDetails userDetails
    ) {
        log.info("댓글 수정 요청 - 댓글 ID: {}, 수정자: {}", commentId, userDetails.getUsername());

        String userEmail = userDetails.getUsername();

        CommentDTO comment = commentService.updateComment(commentId, dto, userEmail);

        log.info("댓글 수정 완료 - 댓글 ID: {}", commentId);
        return ResponseEntity.ok(comment);
    }

    /*
        댓글 삭제 API
            - DELETE /api/comments/{commentId}, 댓글 소프트 삭제
     */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        log.info("댓글 삭제 요청 - 댓글 ID: {}, 삭제자: {}", commentId, userDetails.getUsername());

        String userEmail = userDetails.getUsername();

        commentService.deleteComment(commentId, userEmail);

        log.info("댓글 삭제 완료 - 댓글 ID: {}", commentId);
        return ResponseEntity.noContent().build();  // HTTP 204 No Content 상태코드로 응답-본문없이 삭제 성공만 전달
    }

}














