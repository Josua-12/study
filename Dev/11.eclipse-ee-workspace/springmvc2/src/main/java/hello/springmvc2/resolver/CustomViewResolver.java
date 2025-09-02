package hello.springmvc2.resolver;

import java.util.Locale;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

/*
 *  Spring이 자동으로 제공하는 InternalResourceViewResolver를 사용함
 *  
 */
public class CustomViewResolver implements ViewResolver, Ordered {

	/*
	 *  ViewResolver의 우선순위 설정
	 *  낮은 숫자(0)일수록 높은 우선순위를 부여받음
	 *  리턴값 : 우선순위 값
	 */
	@Override
	public int getOrder() {
		// 우선 순위 1 (Spring 기본 ViewResolver)
		return 0;
	}

	/*
	 *  viewName 이름을 받아서 실제 View 객체로 변환하는 메서드
	 *  	- 컨트롤러가 반환한 논리적 뷰 이름 (member-list)
	 *  	- 현재 요청의 
	 */
	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		System.out.println(" [ViewResolver] View 이름 '" + viewName + "'을 처리중..");
		System.out.println(" [ViewResolver] 로케일 : " + locale);
		return null;
	}

}
