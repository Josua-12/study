package hello.servlet.domain.member;

import hello.servlet.basic.domain.member.Member;
import hello.servlet.basic.domain.member.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class MemberRepositoryTest {

    // 싱글톤 인스턴스 획득
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach      // 각 테스트 메소드 실행 후 자동 실행
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test           // 테스트 메소드임을 표시
    void save() {   // 회원 저장 기능 테스트
        // given
        Member member = new Member("lee", 20);  // 테스트용 회원 객체 생성
        // when
        Member savedMember = memberRepository.save(member);  // 회원 저장
        // then
        Member findMember = memberRepository.findById(savedMember.getId());     // 저장된 id로 회원 조회
        assertThat(findMember).isEqualTo(savedMember);      // 저장된 회원과 조회한 회원이 동일한지 검증
    }

    @Test           // 테스트 메서드임을 표시
    void findAll() {    // 전체 회원 조회 기능 테스트
        // given
        Member member1 = new Member("member1", 20);     // 첫 번째 테스트 회원
        Member member2 = new Member("member2", 30);     // 두 번째 테스트 회원

        memberRepository.save(member1);     // 첫 번째 회원 저장
        memberRepository.save(member2);     // 두 번째 회원 저장
        // when
        List<Member> result = memberRepository.findAll();       // 모든 회원 조회
        // then
        assertThat(result.size()).isEqualTo(2);     // 조회된 회원 수가 2명인지 검증
        assertThat(result).contains(member1, member2);      // 조회 결과에 저장된 두 회원이 포함되어 있는지 검증


    }
}
