package hello.springmvc2.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
/*
 * Handler : 실제 비즈니스 로직을 처리하는 핸들러 
 * 			 컨트롤러와 모델 사이의 중간 처리자 역할
 */

import hello.springmvc2.model.Member;
import lombok.Builder;

@Component		// Spring 컨테이너가 관리하는 컴포넌트로 등록(빈으로 등록되어 )
public class MemberHandler {

	// 임시 데이터 저장소 (실제로는 데이터베이스 사용)
	// static : 클래스 레벨 변수로 모든 인스턴스가 공유  
	private static List<Member> memberDatabase = new ArrayList<>();
	
	// static 초기화 블록 : 클래스가 처음 로드될 때 한번만 실행
	static {
		// Lombok의 @Builder 패턴을 사용한 객체 생성
		memberDatabase.add(Member.builder()
						.id(1L)						//회원 ID 설정
						.name("이순신")				//회원 이름 설정
						.email("lee@gmail.com")		//회원 이메일 설정
						.build());					//Member 객체 생성
		memberDatabase.add(Member.builder()
						.id(2L)
						.name("신사임당")
						.email("shin@gmail.com")
						.build());
		memberDatabase.add(Member.builder()
						.id(3L)
						.name("손홍민")
						.email("son@gmail.com")
						.build());					
	}
	
	/*
	 * 모든 회원 조회 메서드 
	 */
	public List<Member> getAllMembers() {
		System.out.println(" [Handler] 모든 회원 데이터 조회 중....");
		return new ArrayList<>(memberDatabase); // 새로운 ArrayList를 생성하여 반환
	}
	
	/*
	 * ID로 특정 회원 조회 
	 * return: 조회된 회원 객체 
	 */
	public Member getMemberById(Long id) {
		System.out.println("  [Handler] ID " +id+ "번 회원 조회 중....");
		
		return memberDatabase.stream()			// List를 Stream으로 변환
				.filter(member -> member.getId().equals(id))  //ID가 일치하는 회원만 필터링
				.findFirst()				// 첫번째 결과 반환
				.orElse(Member.builder()	// 없으면 기본 객체 반환	
						.id(0L)
						.name("없음")
						.email("없음")
						.build());
	}
	
	public Member addMember(String name, String email) {
		// 새 회원의 ID 생성 (현재 회원 수 + 1)
		Long newId = (long) (memberDatabase.size() + 1);
		
		// Builder 패턴으로 새 회원 객체 생성
		Member newMember = Member.builder()
							.id(newId)		// 생성된 ID
							.name(name)		// 전달받은 이름
							.email(email)	// 전달받은 이메일
							.build();		// 객체 생성 완료
		
		// 데이터베이스에 추가
		memberDatabase.add(newMember);
		
		System.out.println(" [Handler] 새 회원 추가 : " + newMember.toString());
		
		// 새로 생성된 회원 객체 반환
		return newMember;
	}
}