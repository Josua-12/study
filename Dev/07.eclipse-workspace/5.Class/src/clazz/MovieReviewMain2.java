package clazz;
/*
 * 배열을 도입하고 영화 리뷰를 배열에서 관리하시오.
 * 리뷰 출력할 때 배열과 for문 사용 - 향상된 for 
 * 		- 출력 예시) 영화 제목: 케이팝 데몬 헌터스, 리뷰: 신나는 분위기
 * 				   영화 제목: 야당, 리뷰: 연기가 뛰어나고 재미 있음
 */
public class MovieReviewMain2 {

	public static void main(String[] args) {
		/*
		MovieReview kpop = new MovieReview();
		
		kpop.movieName = "케이팝 데몬 헌터스";
		kpop.movieReview = "신나는 분위기";
		
		MovieReview yadang = new MovieReview();
		
		yadang.movieName = "야당";
		yadang.movieReview = "연기가 뛰어나고 재미 있음";
		
		MovieReview[] inventory = new MovieReview[2];
		inventory[0] = kpop;			// 첫 번째 상품
		inventory[1] = yadang;
		
		for(int i = 0; i < inventory.length; i++) {
			System.out.println("영화 제목: " +inventory[i].movieName+", 리뷰: "+inventory[i].movieReview);
		}
		*/
		
		MovieReview[] reviews = new MovieReview[2];
		
		MovieReview kpop = new MovieReview();
		kpop.title = "케이팝 데몬 헌터스";
		kpop.review = "신나는 분위기";
		reviews[0] = kpop;
		
		MovieReview yadang = new MovieReview();
		yadang.title = "야당";
		yadang.review = "연기가 뛰어나고 재미 있음";
		reviews[1] = yadang;
		
		for(MovieReview review : reviews) {
			System.out.println("영화 제목: " + review.title + ", 리뷰: "+ review.review);
		}
	}
}
