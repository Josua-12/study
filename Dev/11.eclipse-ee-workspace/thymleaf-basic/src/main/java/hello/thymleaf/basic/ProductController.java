package hello.thymleaf.basic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Data;

@Controller
@RequestMapping("/product")
public class ProductController {

	@GetMapping("/detail")
	public String productDetail(Model model) {
		// 상품 기본 정보 - DB에서 조회한 상품 데이터
		Product product = new Product(1001L, "AriPods Pro 2", 400000, 4.5, true);
		
		// 상품 옵션 리스트 - 색상, 사이즈 등 선택 가능한 옵션들
		List<ProductOption> options = new ArrayList<>();
		options.add(new ProductOption("색상", "그라파이트", 0));
		options.add(new ProductOption("색상", "보라퍼플", 0));
		options.add(new ProductOption("색상", "화이트", 0));
		options.add(new ProductOption("보증", "1년 연장보증", 15000));
		
		// 리뷰 목록 리스트
		List<Review> reviews = Arrays.asList(
				new Review("이**", 5, "음질이 정말 좋아요! 노이즈 캔슬링도 만족합니다.", 
						LocalDateTime.now().minusDays(2)),  // 2일전
				new Review("김**", 5, "배송 빠르고 제품도 좋은데 케이스가 벗겨졌어요", 
						LocalDateTime.now().minusDays(5)),
				new Review("신**", 5, "아이폰 사용자라면 강추! 연동이 완벽해요", 
						LocalDateTime.now().minusWeeks(1))
				);
		
		// Model에 데이터 추가 - View에서 사용할 모든 데이터
		model.addAttribute("product", product);		// 단일 객체
		model.addAttribute("options", options);		// 리스트
		model.addAttribute("reviews", reviews);		// 리뷰 리스트
		
		// view 이름 반환
		return "product/detail";
		
	}
	
	@Data
	static class Product {
		private Long id;
		private String name;
		private int price;
		private double rating;		// 평점 (0.0 ~ 5.0)
		private boolean inStock;	// 재고 여부
		
		public Product(Long id, String name, int price, double rating, boolean inStock) {
			//super();
			this.id = id;
			this.name = name;
			this.price = price;
			this.rating = rating;
			this.inStock = inStock;
		}
		
		public String getFormattedPrice() {
			return String.format("%,d원", price);
		}
		
	}
	
	@Data
	static class ProductOption {
		private String type;			// 옵션 종류(색상, 사이즈 등)
		private String value;			// 옵션 값
		private int additionalPrice;	// 추가 금액
		public ProductOption(String type, String value, int additionalPrice) {
			//super();
			this.type = type;
			this.value = value;
			this.additionalPrice = additionalPrice;
		}
		
	}
	
	@Data
	static class Review {
		private String writer;
		private int rating;		// 평점(1-5)
		private String content;
		private LocalDateTime createAt;
		public Review(String writer, int rating, String content, LocalDateTime createAt) {
			//super();
			this.writer = writer;
			this.rating = rating;
			this.content = content;
			this.createAt = createAt;
		}
		
	}
}
