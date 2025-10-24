package com.library.service;

import com.library.dto.board.CommentCreateDTO;
import com.library.dto.board.CommentDTO;
import com.library.dto.board.CommentUpdateDTO;
import com.library.entity.board.Board;
import com.library.entity.board.Comment;
import com.library.entity.board.CommentStatus;
import com.library.entity.member.Member;
import com.library.repository.BoardRepository;
import com.library.repository.CommentRepository;
import com.library.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/*
    댓글 Service - 댓글 관련 비즈니스 로직을 처리함
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    //특정 게시글의 댓글 목록 조회
    public List<CommentDTO> getCommentsByBoardId(Long boardId) {
        log.info("게시글 {}의 댓글 목록 조회", boardId);
        List<Comment> comments = commentRepository.findByBoardIdAndStatus(
                boardId, CommentStatus.ACTIVE);

        return comments.stream()    // Comment 엔티티 리스트를 Stream으로 변환
                .map(CommentDTO::from)  // 각 Comment를 CommentDTO로 변환
                .collect(Collectors.toList());  //변환된 CommentDTO들을 새로운 List로 수집하여 반환
    }

    //댓글 작성
    @Transactional
    public CommentDTO createComment(Long boardId, CommentCreateDTO dto, String loginId) {
        log.info("게시글 {}의 댓글 작성 - 작성자: {}", boardId, loginId);

        //게시글 조회
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        //작성자 조회
        Member author = memberRepository.findByEmail(loginId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

        //댓글 생성
        Comment comment = Comment.builder()     // Builder 패턴으로 Comment 객체 생성 시작
                .content(dto.getContent())      //댓글 내용 설정
                .board(board)                   //댓글이 작성될 게시글 설정
                .author(author)                 //댓글 작성자 설정
                .status(CommentStatus.ACTIVE)   //댓글 상태를 활성으로 설정
                .build();                       //설정된 값들로 Comment 객체 생성 완료

        Comment savedComment = commentRepository.save(comment);
        log.info("댓글 작성 완료 - 댓글 ID: {}", savedComment.getId());

        return CommentDTO.from(savedComment);   // 저장된 Comment 엔티티를 CommentDTO로 변환하여 반환
    }

    // 댓글 수정
    @Transactional
    public CommentDTO updateComment(Long commentId, CommentUpdateDTO dto, String loginId) {
        log.info("댓글 {} 수정 - 수성자: {}", commentId, loginId);

        //댓글 조회
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        //작성자 확인
        if (!comment.getAuthor().getEmail().equals(loginId)) {
            throw new IllegalArgumentException("댓글 작성자만 수정할 수 있습니다.");
        }

        //삭제된 댓글인지 확인
        if (comment.getStatus() == CommentStatus.DELETED) {
            throw new IllegalArgumentException("삭제된 댓글은 수정할 수 없습니다.");
        }

        // 댓글 수정
        comment.update(dto.getContent());
        log.info("댓글 수정 완료 - 댓글 ID: {}", commentId);

        return CommentDTO.from(comment);   //수정된 Comment 엔티티를 CommentDTO로 변환하여 반환
    }

    //댓글 삭제 (소프트 삭제)
    @Transactional
    public void deleteComment(Long commentId, String loginId) {
        log.info("댓글 {} 삭제 - 삭제자: {}", commentId, loginId);

        //댓글 조회
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        //작성자 확인
        if (!comment.getAuthor().getEmail().equals(loginId)) {
            throw new IllegalArgumentException("댓글 작성자만 삭제할 수 있습니다.");
        }

        //댓글 삭제
        comment.delete();
        log.info("댓글 삭제 완료 - 댓글 ID: {}", commentId);

    }

    // 특정 게시글의 댓글 개수 조회
    public Long getCommentCount(Long boardId) {
        return commentRepository.countByBoardIdAndStatus(boardId, CommentStatus.ACTIVE);
    }

}
