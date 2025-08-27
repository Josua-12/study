package hello.servlet.basic.domain.memo;


import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter @Setter
@NoArgsConstructor      // 기본 생성자
@AllArgsConstructor     // 전체 필드 생성자
@Builder                // 빌더 패턴 구현 => 컴파일 시점에 빌더 클래스 자동 생성
public class Memo {
    // field
    private Long id;
    private String content;              // 메모 내용
    private LocalDateTime createdAt;     // 작성 시간

    // 포맷된 날짜 문자열
    public String getFormattedDate() {
        if(createdAt == null) return "";
        // DateTimeFormatter를 사용한 날짜 포맷팅
        /*
            패턴 설명
                yyyy : 4자리 연도 (2025)
                MM : 2자리 월 (01 ~ 12)
                dd : 2자리 일 (01 ~ 31)
                HH : 24시간 형식 시간 (00 ~ 23)
                mm : 2자리 분 (00 ~ 59)
         */
        return createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
