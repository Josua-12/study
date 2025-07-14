package lab2;

public class RetangleProceduralMain {

	public static void main(String[] args) {
		RetangleProcedural retangle = new RetangleProcedural();
		retangle.width = 5;				//	|
		retangle.height = 8;			//	ㄴ-> 생성자  
		
		int area = retangle.calculateArea();
		System.out.println("넓이: " + area);
		
		int circumference = retangle.calculateCircumference();
		System.out.println("둘레 길이: " + circumference);
		
		boolean bsquare = retangle.isSquare();
		System.out.println("정사각형 여부 : " + bsquare);
		
		System.out.println();
		
		
		//넓이 구하기
		retangle.area();
		
		//둘레 구하기
		retangle.circum();
		
		//정사각형 여부
		retangle.squre();
	}
}
