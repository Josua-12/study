package extend5.child;

import extend5.parent.Parent;

public class Child extends Parent {
	
	public void call() {
		publicValue = 1;
		protectedValue = 1;
		// defaultValue = 1;  // 다른 패키지라 접근 불가. 컴파일 오류
		// privateValue = 1;  // 접근 불가. 컴파일 오류
		
		publicMethod();
		protectedMethod();	  // (상속 관계 OR 같은 패키지)면 가능
		// defaultMethod();   // 다른 패키지라 접근 불가. 컴파일 오류
		// privateMethod();	  // 접근 불가. 컴파일 오류
		
		printMethod();
	}
}
