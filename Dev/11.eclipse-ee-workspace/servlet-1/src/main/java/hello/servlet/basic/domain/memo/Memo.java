package hello.servlet.basic.domain.memo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor      // 기본 생성자
@AllArgsConstructor      // 전체 필드 생성자
@Builder
public class Memo {
    // field
    private Long id;
    private String content;             // 메모 내용
    private LocalDateTime createdAt;    // 작성 시간

    // 포맷된 날짜 문자열
    /*
         패턴 설명
            yyyy : 4자리 연도 (2025)
            MM : 2자리 월 (01 ~ 12)
            dd : 2자리 일 (01 ~ 31)
            HH : 24시간 형식 시간 (00 ~ 23)
            mm : 2자리 분 (00 ~ 59)
     */
    public String getFormattedDate() {
        if(createdAt == null) return "";

        return createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
