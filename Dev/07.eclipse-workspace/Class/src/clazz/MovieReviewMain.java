package clazz;
/*
 * 	* 영화 리뷰 정보 관리하기
 * 		- 요구 사항
 * 			- MovieReview 클래스는 아래 멤버 변수를 포함해야 함
 * 				- 영화 제목, 리뷰 내용
 * 			- MovieReviewMain 
 * 				- main() 메서드 포함
 * 				- 영화 리뷰 정보 선언 및 출력
 * 
 * 				- 출력 예시) 영화 제목: 케이팝 데몬 헌터스, 리뷰: 신나는 분위기
 * 						   영화 제목: 야당, 리뷰: 연기가 뛰어나고 재미 있음
 */
public class MovieReviewMain {
	
	public static void main(String[] args) {
		
		MovieReview kpop = new MovieReview();
		
		kpop.title = "케이팝 데몬 헌터스";
		kpop.review = "신나는 분위기";
		
		MovieReview yadang = new MovieReview();
		
		yadang.title = "야당";
		yadang.review = "연기가 뛰어나고 재미 있음";
		
		System.out.println("영화 제목: " +kpop.title+", 리뷰: "+kpop.review);
		System.out.println("영화 제목: " +yadang.title+", 리뷰: "+yadang.review);
	}
}
