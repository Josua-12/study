package hello.springmvc2.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import hello.springmvc2.model.Member;

/*
 *  Handler : 실제 비즈니스 로직을 처리하는 핸들러 
 *  		  컨트롤러와 모델 사이의 중간 처리자 역할
 */
@Component	// Spring 컨테이너가 관리하는 컴포넌트로 등록 (빈으로 등록되어 )
public class MemberHandler {

	// 임시 데이터 저장소 (실제로는 데이터베이스 사용)
	// static : 클래스 레벨 변수
	private static List<Member> memberDatabase = new ArrayList<>();
	
	// static 초기화 블록 : 클래스가 처음 로드될 때 한번만 실행
	static {
		// Lombok의 @Builder 패턴을 사용한 객체 생성
		memberDatabase.add(Member.builder()
				.id(1L)						// 회원 ID 설정
				.name("이순신")				// 회원 이름 설정
				.email("lee@gmail.com")		// 회원 이메일 설
				.build());					// Member 객체 생성
		
		memberDatabase.add(Member.builder()
				.id(2L)
				.name("신사임당")
				.email("shin@gmail.com")
				.build());
		memberDatabase.add(Member.builder()
				.id(2L)
				.name("손흥민")
				.email("son@gmail.com")
				.build());
	}
	
	/*
	 *  모든 회원 조회 메서드
	 */
	public List<Member> getAllMembers() {
		System.out.println(" [Handler] 모든 회원 데이터 조회 중...");
		return new ArrayList<>(memberDatabase);	// 새로운 ArrayList를 생성하여 반환
	}
	
	/*
	 * ID로 특정 회원 조회
	 * return: 조회된 회원 객체
	 */
	public Member getMemberById(Long id) {
		System.out.println("[Handler] ID " + id + "번 회원 조회 중...");
		
		return memberDatabase.stream()	// List를 Stream으로 변환
				.filter(member -> member.getId().equals(id)) // ID가 일치하는 회원만 필터링
				.findFirst()		// 첫밴째 결과 반환
				.orElse(Member.builder()
						.id(0L)
						.name("없음")
						.email("없음")
						.build());
	}
}
