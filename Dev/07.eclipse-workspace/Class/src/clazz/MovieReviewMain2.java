package clazz;
/*
 * 배열을 도입하고 영화 리뷰를 배열에서 관리하시오.
 * 리뷰 출력할 때 배열과 for문 사용 - 향상된 for 
 * 		- 출력 예시) 영화 제목: 케이팝 데몬 헌터스, 리뷰: 신나는 분위기
 * 				   영화 제목: 야당, 리뷰: 연기가 뛰어나고 재미 있음
 */
public class MovieReviewMain2 {

	public static void main(String[] args) {
	MovieReviewMain kpop = new MovieReviewMain();
		
		kpop.movieName = "케이팝 데몬 헌터스";
		kpop.movieReview = "신나는 분위기";
		
		MovieReviewMain yadang = new MovieReviewMain();
		
		yadang.movieName = "야당";
		yadang.movieReview = "연기가 뛰어나고 재미 있음";
		
		MovieReviewMain[] inventory = new MovieReviewMain[2];
		inventory[0] = kpop;			// 첫 번째 상품
		inventory[1] = yadang;
		
		for(int i = 0; i < inventory.length; i++) {
			System.out.println("영화 제목: " +inventory[i].movieName+", 리뷰: "+inventory[i].movieReview);
		}
	}
}
