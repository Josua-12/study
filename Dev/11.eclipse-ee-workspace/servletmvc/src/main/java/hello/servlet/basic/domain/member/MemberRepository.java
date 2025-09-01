package hello.servlet.basic.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {
    // 회원 정보를 저장하는 Map
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;  // 회원 id 생성용 시퀀스 (자동 증가값)
    // 싱글톤 패턴 = 객체를 하나만 공유

    // 싱글톤 인스턴스 (클래스 로딩 시 단 한 번만 생성)
    private static final MemberRepository instance = new MemberRepository();

    // 싱글톤 인스턴스를 반환하는 메서드
    public static MemberRepository getInstance() {
        return instance;    // 유일한 인스턴스 반환
    }

    // private 생성자로 외부에서 new 키워드를 사용해 인스턴스를 생성하는 것을 방지
    private MemberRepository() {
    }

    // 회원 저장 메소드
    public Member save(Member member) {
        member.setId(++sequence);   // sequence를 1 증가시키고 회원 id로 설정
        store.put(member.getId(), member);  // Map에 회원 정보 저장
        return member;  // 저장된 회원 객체 반환
    }

    // id로 회원 조회 메서드
    public Member findById(Long id) {
        return store.get(id);   // Map 에서 해당 id의 회원 정보 반환
    }

    // 모든 회원 조회 메서드
    public List<Member> findAll() {
        return new ArrayList<>(store.values());     //Map의 모든 값을 새로운 ArrayList로 반환
    }   // 새로운 ArrayList로 반환하는 이유 : store의 값 보호 (외부 수정 방지)

    // 저장소 초기화 메서드
    public void clearStore() {
        store.clear();      // Map의 모든 데이터 삭제
    }
}