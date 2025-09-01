package hello.servlet.web.frontcontroller.s3.controller;

import java.util.List;
import java.util.Map;

import hello.servlet.basic.domain.member.Member;
import hello.servlet.basic.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.s3.Controller;

public class MemberListController implements Controller {

	private final MemberRepository memberRepository = MemberRepository.getInstance();
	
	@Override
	public ModelView process(Map<String, String> paramMap) {
		List<Member> members =  memberRepository.findAll();
		ModelView mv = new ModelView("members");
		
		mv.getModel().put("members", members);
		
		return mv;
	}

}
