package access.b;

import access.a.AccessData;
public class AccessDataOuterMain {
	
	public static void main(String[] args) {
		AccessData data = new AccessData();
		
		// public 호출 가능
		data.publicField = 11;
		data.publicMethod();
		
		// 다른 패키지의 default 호출 불가능
		//data.defaultField = 22;
		//data.defaultMethod();
		
		// private 호출 불가
		//data.privateField = 33;
		//data.privateMethod();
		
		System.out.println();
		data.innerAccess();
	}
}
