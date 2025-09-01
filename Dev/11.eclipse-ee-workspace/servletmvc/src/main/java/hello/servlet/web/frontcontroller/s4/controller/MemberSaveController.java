package hello.servlet.web.frontcontroller.s4.controller;

import java.util.Map;

import hello.servlet.basic.domain.member.Member;
import hello.servlet.basic.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.s4.Controller;

public class MemberSaveController implements Controller{
	
	private MemberRepository memberRepository = MemberRepository.getInstance();

	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {
		
		// 파라미터 추출
		String username = paramMap.get("username");
		int age = Integer.parseInt(paramMap.get("age"));
		
		// 비즈니스 로직
		Member member = new Member(username, age);
		memberRepository.save(member);
		
		// FrontController가 전달한 model Map에 직접 데이터 추가
		model.put("member", member);
		
		// View 이름만 반환		
		return "save-result";
	}

}
