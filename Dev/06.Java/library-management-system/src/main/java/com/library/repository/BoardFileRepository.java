package com.library.repository;

import com.library.entity.board.BoardFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
    Spring Data JPA Query Method
        - 메서드 이름을 분석해서 자동으로 SQL 쿼리를 생성하는 기능
        - findByBoardIdOrderByCreatedAtDesc
            - find  :  SELECT 조회
            - By    :  WHERE 조건 시작
            - BoardId : board_id 필드로 검색
            - OrderBy : ORDER BY 절 추가
            - CreatedAt : created_at 필드로 정렬
            - Desc : 내림차순 (최신순)

            - 생성되는 SQL
                - SELECT * FROM board_file WHERE board_id = ? ORDER BY created_at DESC;
 */

public interface BoardFileRepository extends JpaRepository<BoardFile, Long> {
    // 특정 게시글의 모든 첨부파일 조회 - 생성일 최신순으로 정렬 
    List<BoardFile> findByBoardIdOrderByCreatedAtDesc(Long boardId);
}
