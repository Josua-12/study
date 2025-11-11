package com.example.boardservice.client;

/*
    UserClient : user-service와의 HTTP 통신을 전담하는 클라이언트 컴포넌트
        - 역할
            - user-service API 호출 캡슐화
            - HTTP 통신 로직을 비즈니스 로직에서 분리
            - 재사용 가능한 사용자 정보 조회 인터페이스 제공
 */

import com.example.boardservice.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component          // Spring 빈으로 등록 => 의존성 주입 가능
public class UserClient {
    // 통신 설정
    private final RestClient restClient;  // Spring 6.1+에서 도입된 최신 HTTP 클라이언트

    public UserClient(@Value("${client.user-service.url}") String userServiceUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(userServiceUrl)
                .build();   // RestClient 인스턴스 생성
    }

    /*
        user-service로부터 사용자 정보 조회
            - 통신 흐름
                - HTTP GET 요청 생성
                - /users/{userId} 엔트포인트 호출
                - JSON 응답을 UserResponseDto로 자동 변환
                - 변환된 객체 반환
     */
    public Optional<UserResponseDto> fetchUser(Long userId) { // MSA간 HTTP 통신
        try {
            UserResponseDto userResponseDto = this.restClient.get()    // HTTP GET 요청 시작
                    .uri("/users/{userId}", userId) // 요청 URI 설정 (파라미터 바인딩)
                    .retrieve()     // HTTP 응답 받기
                    .body(UserResponseDto.class);   // 응답 본문 UserResponseDto 객체로 자동 변환(JSON=>JAVA 객체 변환)
            return Optional.ofNullable(userResponseDto);
        } catch (RestClientException e) {
            return Optional.empty();
        }
    }

    // 여러 사용자 일괄 조회 - user-service의 GET /users?ids=1,2,3 엔드포인트 호출
    public List<UserResponseDto> fetchUsersByIds(List<Long> ids) {
        try {
            return this.restClient.get()    // HTTP GET 요청 시작
                    .uri(uriBuilder -> uriBuilder   // UriBuilder로 동적 URI 생성 (쿼리 파라미터 추가 시 유용)
                            .path("/users") // 경로 설정
                            .queryParam("ids", ids)     // 쿼리 파라미터 추가
                            .build()    // URI 객체 생성 완료
                    )
                    .retrieve()     // HTTP 응답 받기
                    .body(new ParameterizedTypeReference<List<UserResponseDto>>() {});
        } catch (RestClientException e) {
            return Collections.emptyList();     // 빈 리스트 반환 (안전하게 처리 가능)
        }
    }
}

