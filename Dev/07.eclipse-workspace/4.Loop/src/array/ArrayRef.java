package array;

public class ArrayRef {

	public static void main(String[] args) {
		int[] students;  // 배열 변수 선언
		students = new int[5];  // 배열 생성, 5개의 int 공간이 생성, 0으로 자동 초기화
		
		// 변수 값 대입
		students[0] = 90;
		students[1] = 80;
		students[2] = 70;
		students[3] = 60;
		students[4] = 50;
		
		
		// 변수 값 사용
		System.out.println("학생 1 점수" + students[0]);
		System.out.println("학생 2 점수" + students[1]);
		System.out.println("학생 3 점수" + students[2]);
		System.out.println("학생 4 점수" + students[3]);
		System.out.println("학생 5 점수" + students[4]);
		// System.out.println("학생 6 점수" + students[5]);  // 인덱스 허용 범위를 넘어설 때 발생하는 오류
		
	}
}
