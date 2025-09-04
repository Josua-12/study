package hello.thymleaf.basic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/practice")
public class TextPracticeController {
	
	@GetMapping("/text-test")
	public String textTest(Model model) {
		model.addAttribute("normalText", "안녕하세요. 반갑습니다!");
		model.addAttribute("htmlText", "Spring Boot는 <b>정말</b> 재밌어요!");
		
		// XSS 공격 시뮬레이션
		// 댓글, 게시글 등에 스크립트 삽입 시도
		model.addAttribute("scriptText", "<script>alert('해킹당했습니다.')</script>");
		
		return "practice/text-test";
	}
}
