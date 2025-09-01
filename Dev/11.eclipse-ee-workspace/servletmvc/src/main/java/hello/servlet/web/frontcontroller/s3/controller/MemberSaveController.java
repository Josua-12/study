package hello.servlet.web.frontcontroller.s3.controller;

import java.util.Map;

import hello.servlet.basic.domain.member.Member;
import hello.servlet.basic.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.s3.Controller;

public class MemberSaveController implements Controller{

	private MemberRepository memberRepository = MemberRepository.getInstance();
	
	@Override
	public ModelView process(Map<String, String> paramMap) {
		// HttpServletRequest 대신 Map<String, String> 사용
		
		// 파라미터를 Map에서 추출
		// request.getParameter() 사용하지 않음
		String username = paramMap.get("username");
		int age = Integer.parseInt(paramMap.get("age"));
		
		// 비즈니스 로직
		Member member = new Member(username, age);
		memberRepository.save(member);
		
		// ModelView 객체 생성 및 반환
		// 	- 논리적 View 이름만 저장 
		// 	- 전체 경로 아님 ("/WEB-INF/views/save-result.jsp")
		ModelView mv = new ModelView("save-result");
		
		// Model 데이터는 Map에 저장
		// 	- request.setAttribute() 사용하지 않음
		// 	- 순수 Java Map 사용 
		mv.getModel().put("member", member);
		
		return mv;
	}

}
