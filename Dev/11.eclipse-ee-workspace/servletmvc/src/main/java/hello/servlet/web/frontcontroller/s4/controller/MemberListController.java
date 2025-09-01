package hello.servlet.web.frontcontroller.s4.controller;

import java.util.List;
import java.util.Map;

import hello.servlet.basic.domain.member.Member;
import hello.servlet.basic.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.s4.Controller;

public class MemberListController implements Controller{

	private final MemberRepository memberRepository = MemberRepository.getInstance();
	
	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {
		
		List<Member> members =  memberRepository.findAll();
		
		model.put("members", members);
		return "members";
	}

}
