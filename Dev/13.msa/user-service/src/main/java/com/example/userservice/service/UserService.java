package com.example.userservice.service;

import com.example.userservice.domain.User;
import com.example.userservice.domain.UserRepository;
import com.example.userservice.dto.SignUpRequestDto;
import com.example.userservice.dto.UserResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void signUp(SignUpRequestDto signUpRequestDto) { // DB 저장만 수행
        User user = new User(       // User 엔티티 객체 생성 (DTO 데이터를 도메인 모델로 변환)
                signUpRequestDto.getEmail(),    // DTO에서 이메일 추출
                signUpRequestDto.getName(),     // DTO에서 이름 추출
                signUpRequestDto.getPassword()  // DTO에서 비밀번호 추출
        );

        this.userRepository.save(user);   // JPA를 통해 DB에 사용자 정보 저장 (Insert 쿼리 실행)
    }

    public UserResponseDto getUser(Long id) {   // 사용자 정보를 DTO로 변환하여 반환
        User user = userRepository.findById(id)     // DB에서 ID로 사용자 조회 (SELECT 쿼리 실행)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다.")); // 조회결과 없으면 예외 발생

        return new UserResponseDto(     // 엔티티를 DTO로 변환 (도메인 모델을 DTO로 변환)
                user.getUserId(),       // 사용자 ID 추출
                user.getEmail(),        // 사용자 이메일 추출
                user.getName()          // 사용자 이름 추출
        );
    }

    // 여러 사용자 ID로 일괄 조회
    public List<UserResponseDto> getUsersByIds(List<Long> ids) {
        List<User> users = userRepository.findAllById(ids); // SELECT * FROM users WHERE user_id IN (1, 2, 3);

        return users.stream()       // Stream API
                .map(user -> new UserResponseDto(
                        user.getUserId(), // 사용자 ID 추출
                        user.getEmail(),  // 사용자 이메일 추출
                        user.getName()    // 사용자 이름 추출
                )).collect(Collectors.toList());    // Stream 결과를 List로 수집하여 반환
    }
}
















