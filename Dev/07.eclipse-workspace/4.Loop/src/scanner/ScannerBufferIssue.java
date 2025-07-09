package scanner;

import java.util.Scanner;

public class ScannerBufferIssue {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("📌 숫자 입력 후 문자열 입력 시도 케이스");
		System.out.print("나이를 입력하세요");
		int age = scanner.nextInt(); // 정수만 입력받고 줄바꿈(\n)은 버퍼에 남음
		
		System.out.print("이름을 입력하세요 : ");
		// 버퍼에 남아있는 \n을 읽어버려서 name은 빈 문자열이 됨
		String name = scanner.nextLine();
		
		System.out.println("⛔ 입력된 이름 : '" + name + "' ");
		System.out.println();
		
		// 1. nextLine()을 한 번 더 써서 줄바꿈 문자 제거
		System.out.println("✅[이슈 해결 방법1] nextLine() 줄바꿈 제거 후 입력 ");
		System.out.print("나이를 입력하세요");
		age = scanner.nextInt();
		scanner.nextLine();  // 줄바꿈(\n) 문자 제거 => 버퍼 비우기
		
		System.out.print("이름을 입력하세요 :");
		name = scanner.nextLine();
		
		System.out.println("🎉 입력된 이름 : '" + name + "' ");
		System.out.println();
		
		// 2. nextLine()만 사용하고 필요한 타입으로 직접 파싱
		System.out.println("✅[이슈 해결 방법2] nextLine()만 사용 + 파싱");
		System.out.print("나이를 입력하세요");
		
		String ageInput = scanner.nextLine();
		// 문자열을 숫자로 변환
		int parsedAge = Integer.parseInt(ageInput);
		
		System.out.print("이름을 입력하세요 :");
		name = scanner.nextLine();
		
		System.out.println("🎉 입력된 이름 : '" + name + "', 나이 : " + parsedAge);
		
		
	}
}
