package io;

import java.io.IOException;

public class SystemInTest2 {

	public static void main(String[] args) {
		System.out.println("알파벳을 여러 개를 쓰고 [Enter]를 누르세요");
		
		int i;
		
		try {
			while((i = System.in.read()) != -1) {	// 한바이트씩 읽음
				System.out.print((char)i);			// 문자로 변환하여 출력
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
