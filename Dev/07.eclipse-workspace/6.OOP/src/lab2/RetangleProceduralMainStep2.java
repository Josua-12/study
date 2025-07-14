package lab2;

public class RetangleProceduralMainStep2 {

	public static void main(String[] args) {
		int width = 5;
		int height = 8;
		int area = calculateArea(width, height);
		System.out.println("넓이 : " + area);
		
		int circumference = calculateCircumference(width, height);
		System.out.println("둘레의 길이 : " + circumference);
		
		boolean square = isSquare(width, height);
		System.out.println("정사각형 여부(true, false) : " + square);
	}

	private static boolean isSquare(int width, int height) {
		return width == height;
	}

	private static int calculateCircumference(int width, int height) {
		return (width + height) * 2;
	}

	private static int calculateArea(int width, int height) {
		return width * height;
	}
}
