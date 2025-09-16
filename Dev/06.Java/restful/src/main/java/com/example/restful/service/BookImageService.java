package com.example.restful.service;

import com.example.restful.entity.BookImage;
import com.example.restful.repository.BookImageRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookImageService {
    private final BookImageRepository bookImageRepository;

    // 생성자 주입
    public BookImageService(BookImageRepository bookImageRepository) {
        this.bookImageRepository = bookImageRepository;
    }

    // 이미지 정보 저장
    public BookImage save(BookImage bookImage) {
        return bookImageRepository.save(bookImage);
    }

    // ID로 이미지 조회
    public Optional<BookImage> findById(Long imageId) {
        return bookImageRepository.findById(imageId);
    }

    // 이미지 삭제
    public void delete(BookImage bookImage) {
        bookImageRepository.delete(bookImage);
    }
}





















