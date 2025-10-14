package com.library.entity.board;

import com.library.entity.base.BaseEntity;
import com.library.entity.member.Member;
import jakarta.persistence.*;
import lombok.*;

/*
    게시글 Entity
        - 게시판의 게시글 정보를 관리함
        - BaseEntity를 상속받아 생성일시/수정일시가 자동 관리됨
        - 작성자(Member)와 다대일(N:1) 연관관계를 가짐

    연관관계 로딩 전략
        - author 필드는 지연 로딩(LAZY) 사용
        - N + 1 문제 해결을 위해 Fetch Join 권장
        - 조회 시 BoardRepository의 Fetch Join 메서드 사용 필요
 */
@Entity
@Table(name = "board")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //JPA 스펙상 기본 생성자 필요. 외부에서 직접 생성방지
@AllArgsConstructor
@Builder
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // AUTO_INCREMENT
    private Long id;        /* 게시글 ID (PK)*/

    @Column(nullable = false, length = 200)
    private String title;

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    /*
        게시글 작성자 (Member와 N:1 관계)
            - 연관관계 설정
                - 관계: 다대일(May-to-One) - 여러 게시글이 한 명의 작성자를 가짐
                - 외래키: author_id (members.member_id 참조)
                - 필수: nullable = false (게시글은 반드시 작성자가 있어야 함)

            - 지연 로딩 (Lazy Loading)
                - FetchType.LAZY를 사용하여 성능을 최적화함
                - 장점: Board 조회 시 Member를 즉시 조회하지 않음
                - 효과: author 필드를 사용하지 않는 경우 불필요한 JOIN 쿼리 방지
                - 동작: board.getAuthor().getName() 호출 시점에 Member 조회

            - N+1 문제와 해결책
                - N+1 문제란?
                    - 게시글 목록 조회: 1번의 쿼리
                    - 각 게시글의 작성자 조회: N번의 추가 쿼리
                    - 결과: 게시글이 100개면 총 101번의 쿼리 실행 (1 + 100)

                - 해결책
                    - Fetch Join 사용
                        - BoardRepository에서 Fetch Join으로 한 번에 조회
                        - 1번의 쿼리로 Board + Member 함께 조회

                - 프록시 객체
                    - 실제 Member 객체가 아닌 Hibernate 프록시 객체
                    - author의 메서드 호출 시점에 실제 DB조회
                    - 트랜잭션 범위 밖에서 접근 시 LazyInitializationException 발생 가능
                        - 해결: @Transactional 내에서 사용하거나 Fetch Join 활용
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Member author;

    @Column(nullable = false)
    @Builder.Default
    private Long viewCount = 0L;

    @Column(nullable = false)
    @Builder.Default
    private Long likeCount = 0L;

    /*
        게시글 상태
            - EnumType.STRING을 사용하여 문자열로 저장
            - 값 기반 저장
            - 가독성 : DB에서 직접 확인 가능
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private BoardStatus status = BoardStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private BoardCategory category = BoardCategory.FREE;

    /*
        비즈니스 메서드
            - 조회수 증가
                - 게시글 상세보기 시 호출됨
                - 트랜잭션 내에서 호출되어야 변경사항이 DB에 반영됨
     */
    public void increaseViewCount() {
        this.viewCount++;
    }

    /*
        게시글 수정
            - 게시글의 제목, 본문, 카테고리를 수정함
     */
    public void update(String title, String content, BoardCategory category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    /*
        게시글 삭제
            - 소프트 삭제: 실제 데이터를 삭제하지 않고 상태만 DELETED로 변경함
                - 장점
                    - 데이터 복구 가능
                    - 감사 추적(Audit Trail) 유지
                    - 통계 데이터 보존
                    - 외래키 제약 조건 유지
     */
    public void delete() {
        this.status = BoardStatus.DELETED;
    }
}
