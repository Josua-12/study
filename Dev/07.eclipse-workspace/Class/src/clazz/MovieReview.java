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
public class MovieReview {
	
	public static void main(String[] args) {
		
		MovieReviewMain kpop = new MovieReviewMain();
		
		kpop.movieName = "케이팝 데몬 헌터스";
		kpop.movieReview = "신나는 분위기";
		
		MovieReviewMain yadang = new MovieReviewMain();
		
		yadang.movieName = "야당";
		yadang.movieReview = "연기가 뛰어나고 재미 있음";
		
		System.out.println("영화 제목: " +kpop.movieName+", 리뷰: "+kpop.movieReview);
		System.out.println("영화 제목: " +yadang.movieName+", 리뷰: "+yadang.movieReview);
	}
}
