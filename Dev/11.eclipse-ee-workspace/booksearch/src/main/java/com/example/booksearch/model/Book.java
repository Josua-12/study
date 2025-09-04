package com.example.booksearch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 도서 정보를 담는 모델 클래스 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	private Long id;	//도서 고유 ID
	private String title;
	private String author;
	private String category;
	private Integer price;
	private Integer stock;		// 재고 수량
}






