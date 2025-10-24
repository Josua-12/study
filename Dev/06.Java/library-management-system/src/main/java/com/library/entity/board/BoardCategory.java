package com.library.entity.board;

import lombok.Getter;

/*
    게시글 카테고리 Enum
        - 게시글의 카테고리를 구분함
        - 각 카테고리는 화면에 표시될 한글 이름(displayName)을 가짐
 */
@Getter
public enum BoardCategory {
    NOTICE("공지사항"),         /* 공지사항 게시판 */
    FREE("자유게시판"),          /* 자유게시판 */
    QNA("질문답변"),            /* 질문답변 게시판*/
    REVIEW("리뷰");            /* 리뷰 게시판 */

    private final String displayName;       /*  화면에 표시될 한글 이름 */


    BoardCategory(String displayName) {
        this.displayName = displayName;
    }
}
