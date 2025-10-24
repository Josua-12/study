package com.library.entity.board;

import com.library.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

// 게시글 첨부파일 Entity - 게시글에 첨부된 파일 정보를 관리하며, Board와 다대일(N:1) 연관관계를 가짐
@Entity
@Table(name = "board_file")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class BoardFile extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 소속 게시글 (Board와 N:1 관계) - 게시글 삭제시 첨부파일도 함께 삭제됨
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Column(nullable = false, length = 255)
    private String originalFilename;    // 원본 파일명 (사용자가 업로드한 파일 원래 이름)

    @Column(nullable = false, length = 255)
    private String storedFilename;      // 저장된 파일명 (UUID-파일명 중복 방지 위함)

    @Column(nullable = false, length = 500)
    private String filePath;    // 파일 경로 (서버에 저장된 파일의 상대 경로)

    @Column(nullable = false)
    private Long fileSize;      // 파일 크기 (바이트 단위)

    @Column(length = 10)
    private String fileExtension;   // 파일 확장자 (소문자) - ex) "pdf", "jpg", "png"

    @Column(length = 100)
    private String mimeType;    // MIME 타입 - 예) "application/pdf", "image/jpg"

    @Column(nullable = false)
    @Builder.Default
    private Long downloadCount = 0L;    // 다운로드 횟수

    // 다운로드 횟수 증가
    public void increaseDownloadCount() {
        this.downloadCount++;
    }

    /*
        JPA에서 양방향 관계를 맺을 때는 양쪽 모두에 값을 설정해야 함
            - Board의 files 컬렉션에 파일 추가
            - BoardFile의 board필드에 게시글 설정
            - 둘중 하나만 설정하면 데이터 불일치가 발생할 수 있음.
        사용 방법
            - 이 메서드는 직접 호출하지 않고, Board.addFile() 메서드 내에서 자동으로 호출함
     */
    public void setBoard(Board board) {
        this.board = board;
    }
}
