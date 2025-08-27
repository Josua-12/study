package hello.servlet.basic.domain.memo;

import hello.servlet.basic.domain.member.MemberRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/*


 */
public class MemoRepository {
    // Thread-safe한 HashMap 구현체
    private static final Map<Long, Memo> store = new ConcurrentHashMap<>();
    private static final AtomicLong sequence = new AtomicLong(0);
    private static final MemoRepository instance = new MemoRepository();
    // 싱글톤 인스턴스 반환
    public static MemoRepository getInstance() {
        return instance;
    }
    // private 생성자
    private MemoRepository() {
        // 샘플 메모 데이터 3개 추가
        save(Memo.builder().content("오늘 JSP 완전 마스터!").build());
        save(Memo.builder().content("이제 여름이 가니...").build());
        save(Memo.builder().content("Sping Boot 어여...").build());
    }

    // 메모 저장
    public Memo save(Memo memo) {
        // ID 자동 생성 : 1부터 시작, 1씩 증가
        memo.setId(sequence.incrementAndGet());     // 원자적으로 1 증가 후 반환
        memo.setCreatedAt(LocalDateTime.now());     // 현재 시스템 시간
        // Map에 저장
        store.put(memo.getId(), memo);
        return memo;        // 저장된 객체 반환
    }

    // 전체 메모 조회 - 모든 메모 리스트 (최신 메모가 먼저)
    /*
        람다식 정렬
            - (a, b) : 비교할 두 요소
            - b.compareTo(a) : 내림차순 (큰 ID가 먼저)
            - a.compareTeo(b) : 오른차순(작은 ID가 먼저
     */
    public List<Memo> findAll() {
        return new ArrayList<>(store.values())
                .stream()
                .sorted((a, b) -> b.getId().compareTo(a.getId()))
                .collect(Collectors.toList());      // Stream을 List로 변환
    }

    // 메모 삭제 - 반환값 없음
    public  void  delete(Long id) {
        store.remove(id);
    }

    // 전체 메모 개수 조회
    public  int getCount() {
        return store. size();
    }

}
