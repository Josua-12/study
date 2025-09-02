package hello.springmvc2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data		// Getter, Setter, toString, equals, hashCode 자동 생성
@NoArgsConstructor	// 기본 생성자 생성 : Member()
@AllArgsConstructor	// 모든 필드 생성자 : Member(Long id, String name, String email)
@Builder	// 빌더 패턴 적용
public class Member {

	private Long id;
	private String name;
	private String email;
}
