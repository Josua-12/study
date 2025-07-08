package scanner;

import java.util.Scanner;

public class ScannerLab4 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.print("문자열을 입력하세세요(exit : 종료) : ");
			String string = scanner.nextLine();
			
			if(string.equals("exit")) {
				System.out.println("프로그램을 종료합니다.");
                break;
			}
			System.out.println("입력한 문자열 : " + string);
		}
		scanner.close();
	}	
}
