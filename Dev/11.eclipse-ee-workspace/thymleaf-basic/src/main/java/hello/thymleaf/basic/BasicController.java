package hello.thymleaf.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Data;

@Controller // Spring MVC 컨트롤러로 선언
@RequestMapping("/basic") // 이 컨트롤러의 모든 URL은 /basic으로 시작
public class BasicController {

	// XSS 공격 방지를 위한 기본 처리
	@GetMapping("/text-basic")	// GET /basic/text-basic
	public String textBasic(Model model) {
		
		model.addAttribute("data", "Hello <b>Spring<b>!");
		return "basic/text-basic";
	}
	
	
	@GetMapping("/text-unescaped")
	public String textUnescaped(Model model) {
		model.addAttribute("data", "Hello <b>Spring<b>!");
		return "basic/text-unescaped";
	}
	
	@GetMapping("/variable")
	public String variable(Model model) {
		// User 객체
		User userA = new User("userA", 10);
		User userB = new User("userB", 20);
		
		// 회원 목록
		List<User> list = new ArrayList<>();
		list.add(userA);
		list.add(userB);
		
		// Map 컬렉션 생성
		Map<String, User> map = new HashMap<>();
		map.put("userA", userA);
		map.put("userB", userB);
		
		model.addAttribute("user", userA);		// 단일 객체 전달
		model.addAttribute("users", list);		// 리스트 전달
		model.addAttribute("userMap", map);		// 맵 전달
		
		return "basic/variable";
	}
	
	@Data
	static class User {
		private String username;
		private int age;
		
		public User(String username, int age) {
			this.username = username;
			this.age = age;
		}
	}
}
