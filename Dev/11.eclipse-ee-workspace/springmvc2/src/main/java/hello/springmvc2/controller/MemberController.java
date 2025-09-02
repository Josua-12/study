package hello.springmvc2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import hello.springmvc2.Springmvc2Application;
import hello.springmvc2.handler.MemberHandler;
import hello.springmvc2.model.Member;

/*
 * Controller : HTTP 요청을 받아 처리하는 컨트롤러
 * 				MVC 패턴에서 'C'에 해당
 * 
 * Spring MVC 흐름 
 * 	1) 클라이언트가 HTTP 요청
 *  2) DispatcherServelt이 요청을 받음
 *  3) HandlerMapping이 이 컨트롤러를 찾음
 *  4) 컨트롤러가 요청을 처리
 */
@Controller			// 이 클래스가 컨트롤러임을 Spring에게 알림(컴포넌트 스캔 대상)
@RequestMapping("/members")	// 이 컨트롤러의 기본 URL 경로 설정 (모든 메서드에 공통적용)
public class MemberController {

    private final Springmvc2Application springmvc2Application;

	@Autowired		//의존성 자동 주입 (Spring이 MemberHandler 객체를 자동으로 연결)
	private MemberHandler memberHandler;

    MemberController(Springmvc2Application springmvc2Application) {
        this.springmvc2Application = springmvc2Application;
    } // 비즈니스 로직을 처리할 핸들러 
	
	/*
	 * 회원 목록 조회 
	 * GET /members
	 */
	@GetMapping("")		//GET 방식의 /members 요청을 이 메서드가 처리
	public String listMembers(Model model) { //Model 객체는 Spring이 자동으로 주입(view로 데이터 전달용) 
		System.out.println("=== MVC 흐름 시작 ===");
		System.out.println("1. 클라이언트 요청: GET /members");
		System.out.println("2. DispatcherServlet이 요청을 받음");
		System.out.println("3. HandlerMapping이 MemberController.listMembers()를 찾음");
		System.out.println("4. 핸들러 어댑터가 컨트롤러 메소드 실행");
		
		// 핸들러를 통해 비즈니스 로직 처리 (회원 목록 조회)
		List<Member> members = memberHandler.getAllMembers();
		
		// Model에 데이터 추가 
		// JSP에서 ${members}로 접근 가능 
		model.addAttribute("members", members);
		
		System.out.println("5. Model에 데이터 추가 완료");
		System.out.println("6. ViewResolver가 'member-list'를 /WEB-INF/views/member-list.jsp로 변환");
		System.out.println("7. View 반환: member-list");
		
		return "member-list";
	}
	
	/*
	 * 	회원 추가 처리 메서드 
	 *  POST /members 요청 처리
	 *  HTML 폼에서 전송된 데이터를 받아 처리
	 *  요청의 파라미터를 받아 처리
	 */
	public String addMember() {
		
	}
	
	/*
	 * 특정 회원 상세 조회
	 * GET /members/{id} 요청 처리
	 */
	@GetMapping("/{id}")   // URL 경로 변수 {id} 사용 (예: /members/1, /members/2)
	public ModelAndView getMember(@PathVariable("id") Long id) {  //@PathVariable: URL의 {id}값을 매개변수로 받음
		System.out.println("===MVC 상세 조회 흐름===");
		System.out.println("1. 클라이언트 요청: GET /members/" + id);
		System.out.println("2. DispatcherServlet이 요청을 받음");
		System.out.println("3. HandlerMapping이 MemberController.getMember()를 찾음");
		
		ModelAndView mav = new ModelAndView(); //Model과 View 정보를 함께 담는 객체
		
		//핸들러 통해 특정 회원 조회
		Member member = memberHandler.getMemberById(id);
		
		//ModelAndView에 데이터 추가 (Model 역할)
		mav.addObject("member", member);	//key: "member" value = 조회된 회원 객체
		
		//View 이름 설정
		mav.setViewName("member-detail");
		
		System.out.println("4. ModelAndView 객체 생성 및 데이터 설정");
		System.out.println("5. ViewResolver가 view 이름을 실제 JSP 경로로 반환");
		System.out.println("6. ModelAndView 반환");
		
		return mav;		//ModelAndView 객체 반환 
		
	}
	
	/*
	 * 회원 추가 폼 화면 표시
	 * GET /members/new 요청 처리 
	 */
	@GetMapping("/new")
	public String showAddForm() {
		System.out.println("===회원 추가 폼 요청 ===");
		System.out.println("GET /members/new - 회원 추가 폼 표시");
		
		// View 이름만 반환 (데이터 없이 폼만 표시)
		return "member-form";  // ViewResolver가 /WEB-INF/views/member-form.jsp로 변환
	}
	
}












