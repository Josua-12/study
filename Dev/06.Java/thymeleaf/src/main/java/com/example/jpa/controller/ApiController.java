package com.example.jpa.controller;

import com.example.jpa.entity.MessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j              // Lombok 로깅 기능 자동 추가 (log변수 사용 가능)
@RestController     // @Controller + @ResponseBody => 모든 메서드가 JSON 반환
@RequestMapping("/api")    // 기본 경로 매핑, 애플리케이션 루트 url /api 시작
public class ApiController {
    // 메모리 기반 임시 데이터 저장소
    private List<MessageDTO> messages = new ArrayList<>();
    private Long idCounter = 1L;

    // 생성자 : Spring이 Bean 생성 시 자동 호출
    public ApiController() {
        //빌더 패턴으로 초기 메시지 생성
        messages.add(MessageDTO.builder()
                .id(idCounter++)
                .title("첫 번째 메시지")
                .content("Spring Boot에서 보내는 메시지입니다.")
                .author("System")
                .createdAt(LocalDateTime.now())
                .success(true)
                .build());

        //두번째 메시지 추가
        messages.add(MessageDTO.builder()
                .id(idCounter++)                    // ID : 2
                .title("환영 메시지")
                .content("비동기 요청 처리에 오신것을 환영합니다!")
                .author("Admin")
                .createdAt(LocalDateTime.now().minusMinutes(5))     // 5분전
                .success(true)
                .build()
        );

        //세번째 메시지 추가
        messages.add(MessageDTO.builder()
                .id(idCounter++)                    // ID : 3
                .title("공지 사항")
                .content("Fetch API를 사용하여 비동기 통신을 요청합니다. 개발자 도구를 열어 네트워크 탭을 확인해보세요.")
                .author("Developer")
                .createdAt(LocalDateTime.now().minusMinutes(10))     // 10분전
                .success(true)
                .build()
        );
    }

    /*
            JSON 객체 응답
                - Map을 사용한 JSON 데이터 반환
                - Spring이 자동으로 Map => JSON 변환
         */
    @GetMapping("/info")
    public Map<String, Object> getInfo() {
        log.info("GET /api/info 호출함");

        Map<String, Object> info = new HashMap<>();
        info.put("application", "Asyn Test");
        info.put("version", "1.0.0");
        info.put("timestamp", LocalDateTime.now());
        info.put("status", "running");

        return info; // {"application": "Asyn Test", ...}
    }

    /*
        서버 상태 체크
            - 서버 상태 정보를 JSON으로 반환
     */
    @GetMapping("/status")
    public Map<String, Object> getStatus() {
        log.info("GET /api/status 호출함");

        Map<String, Object> status = new HashMap<>();
        status.put("server", "online");         //서버 상태
        status.put("messageCount", messages.size());  //메시지 개수
        status.put("timestamp", System.currentTimeMillis());

        return status; // {"server": "online", "messageCount":1, ...}
    }

    @GetMapping("/messages")        // GET /api/messages
    public ResponseEntity<List<MessageDTO>> getAllmessages() {
        log.info("GET /api/messages - 전체 메시지 조회");

        // 200 + Body
        return ResponseEntity.ok(messages);  // [{id:1, title:"..",}]
    }

    @GetMapping("/messages/{id}")       // GET /api/messages/1
    public ResponseEntity<MessageDTO> getMessage(@PathVariable Long id) {
        log.info("GET /api/messages/{} - 특정 메시지 조회", id);

        return messages.stream()
                .filter(msg -> msg.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*
        간단한 텍스트 응답 - 단순한 문자열 반환
     */
    @GetMapping("/hello")
    public String hello() {
        log.info("GET /api/hello 호출됨");
        return "Hello from Spring Boot!!";      //클라이언트에 텍스트 반환
    }
}
