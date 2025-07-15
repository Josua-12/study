package constructor7;
/*
 * 		제목:  , 저자:  , 페이지: 0
 * 		제목: 10분 만에 따라 하는 Cluade MCP 업무 자동화 혁신 가이드 , 저자: 이호준 , 페이지: 0
 * 		제목: 생성형 AI를 위한 프롬프트 엔지니어링 , 저자: 제임스 피닉스 , 페이지: 492
 */
public class BookMain {

	public static void main(String[] args) {
		//기본 생성자 사용
		Book book1 = new Book();
		book1.displayInfo();
		
		// title과 author만을 매개변수로 받는 생성자
		Book book2 = new Book("10분 만에 따라 하는 Cluade MCP 업무 자동화 혁신 가이드", "이호준");
		book2.displayInfo();
		
		// title과 author, page를 매개변수로 받는 생성자
		Book book3 = new Book("생성형 AI를 위한 프롬프트 엔지니어링", "제임스 피닉스", 492);
		book3.displayInfo();
	}
}
