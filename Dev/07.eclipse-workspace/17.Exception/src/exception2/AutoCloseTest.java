package exception2;

public class AutoCloseTest {

	public static void main(String[] args) {
		try (AutoCloseObj obj = new AutoCloseObj()) {
			throw new Exception(); 	// 강제 예외 발생
		} catch (Exception e) {
			System.out.println("예외 발생 부분입니다.");
		}
	}
}
