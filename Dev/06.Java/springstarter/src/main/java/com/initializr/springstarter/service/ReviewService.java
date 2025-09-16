package com.initializr.springstarter.service;

import com.initializr.springstarter.entity.Review;
import com.initializr.springstarter.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    // 생성자 주입 방식
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    // 리뷰 저장 메서드 (새 리뷰 등록 또는 기존 리뷰 수정)
    public Review save(Review review) {
        //JPA save() 메서드 호출 - ID가 없으면 INSERT, ID가 있으면 UPDATE
        return reviewRepository.save(review);
    }

    // 리뷰 삭제 메서드
    @Transactional      // 트랜잭션 처리-메서드 실행 예외 발생 시 자동 롤백, 정상 완료시 커밋
    public void deleteReviewById(Long review_id) {
        reviewRepository.deleteById(review_id); //DELETE FROM review WHERE id = ?
    }

    public Double reviewAvgRating(Long book_id) {
        return reviewRepository.findAverageRatingByBookId(book_id);
    }
}


















