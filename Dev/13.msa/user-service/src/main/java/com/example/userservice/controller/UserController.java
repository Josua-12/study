package com.example.userservice.controller;

import com.example.userservice.dto.SignUpRequestDto;
import com.example.userservice.dto.UserResponseDto;
import com.example.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("signup")      // POST /users/signup 요청 처리 (회원가입)
    public ResponseEntity<Void> signUp(  // 상태코드만 반환.
            @RequestBody SignUpRequestDto signUpRequestDto  // 요청 본문을 DTO로 바인딩
    ) {
        userService.signUp(signUpRequestDto);       // 회원가입 로직
        return ResponseEntity.noContent().build();  // 204 No Content
    }

    @GetMapping("{userId}")     // GET /users/{userId} 요청 처리 (특정 사용자 조회)
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long userId) {
        UserResponseDto userResponseDto = userService.getUser(userId); // 사용자 조회 로직 호출
        return ResponseEntity.ok(userResponseDto);  // 200 OK: 성공 응답과 함께 사용자 데이터 조회
    }

    // @RequestParam: 쿼리 파라미터를 List로 바인딩
    @GetMapping()   //GET /users 요청 처리 (여러 사용자 일괄 조회)  - 쿼리 파라미터 방식
    public ResponseEntity<List<UserResponseDto>> getUsersByIds(
            @RequestParam List<Long> ids   // /users?ids=1&ids=2&ids=3 또는 /user?ids=1,2,3
    ) {
        List<UserResponseDto> userResponseDtos = userService.getUsersByIds(ids);     // 서비스에서 일괄 조회 수행
        return ResponseEntity.ok(userResponseDtos);     // 200 OK : 성공 응답과 함께 여러 사용자 데이터 반환
    }
}
