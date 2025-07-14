package lab2;

public class RetangleProcedural {
	int width;
	int height;
	
	
	boolean isSquare() {
		return width == height;
	}

	int calculateCircumference() {
		return (width + height) * 2;
	}

	int calculateArea() {
		return width * height;
	}

	void area() {
		int area = calculateArea();
		System.out.println("넓이 : " + area);
	}

	void circum() {
		int circumference = calculateCircumference();
		System.out.println("둘레의 길이 : " + circumference);
	}

	void squre() {
		boolean square = isSquare();
		System.out.println("정사각형 여부(true, false) : " + square);
	}
}
