package exception;

public class MultiCatchFinally {

	public static void main(String[] args) {
		String[] names = {"Alice", "Bob", null, "David"};
		
		try { 
			for (int i = 0; i <= names.length; i++) {
				System.out.println("이름 : " + names[i].toUpperCase());
			}
		} catch (NullPointerException e) {
			// null 참조에 대해 메서도를 호출할 때 발생
			System.out.println("예외 발생 : NullPointerException");
			System.out.println("예외 메시지 : " + e.getMessage());
		} catch (ArrayIndexOutOfBoundsException e) {
			// 배열 범위를 초과하면 발생
			System.out.println("예외 발생 : ArrayIndexOutOfBoundsException");
			System.out.println("예외 메시지 : " + e.getMessage());
		} finally {
			// 예외 발생 여부와 관계 없이 항상 실행
			System.out.println("finally 블록 : 자원 정리 또는 로그 출력 등에 사용");
		}
		
		System.out.println("✅ 프로그램 정상 종료");
	}
}
