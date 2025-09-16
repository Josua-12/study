package com.initializr.springstarter.repository;

import com.initializr.springstarter.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.book.id = :book_id")
    Double findAverageRatingByBookId(@RequestParam("book_id") Long book_id);
}
