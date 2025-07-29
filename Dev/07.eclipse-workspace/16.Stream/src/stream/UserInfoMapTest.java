package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


class UserInfo {
	private String name;
	private int age;
	
	public UserInfo(String name, int age) {
		//super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}
	
	
}

public class UserInfoMapTest {

	public static void main(String[] args) {
		UserInfo userLee = new UserInfo("이순신", 30);
		UserInfo userShin = new UserInfo("신사임당", 40);
		UserInfo userRyu = new UserInfo("류승룡", 50);
		
		List<UserInfo> userInfos = new ArrayList<>();
		userInfos.add(userLee);
		userInfos.add(userShin);
		userInfos.add(userRyu);
		
		// 스트림을 이용하여 40세 이상인 사람들의 이름만 출력하시오.
		// filter와 map 중간 연산
		/*
		 * ::
		 * 		- 메서드 레퍼런스
		 * 		- 이미 존재하는 메서드를 "참조"해서 람다식 대신 사용하는 문법임
		 */
		userInfos.stream().filter(user -> user.getAge() >= 40).map(UserInfo::getName)
				//.forEach(s -> System.out.print(s + " ")
				  .forEach(System.out::println);
		
		List<String> names =
		Arrays.asList("김철수", "이영희", "박민수", "최지혜", "김영수");
		
		System.out.println("원본 데이터 : ");
		names.forEach(name -> System.out.println(name));
		System.out.println("----------------");
		names.forEach(System.out::println);
		
		System.out.println("\n----------------");
		names.stream()		// Stream <String> 객체가 생성됨
			.filter(name -> name.startsWith("김")) // 중간연산: 조건에 맞는 요소만 통과됨
			.forEach(System.out::println); 		  // 최종연산: 각 요소를 출력
		
		System.out.println("\n----------------");
		
		// 배열에서 Stream 생성
		String[] array = {"사과", "바나나", "오렌지"};
		Arrays.stream(array)
			.forEach(System.out::println);
		
		System.out.println("\n----------------");
		
		Stream.of("A", "B", "C").forEach(System.out::println);
	}
}















