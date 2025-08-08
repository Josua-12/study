package com.shopping.service;

import com.shopping.model.User;
import com.shopping.repository.UserRepository;
import com.shopping.util.ValidationUtils;

/*
 * 사용자 관련 비즈니스 로직을 처리하는 서비스 클래스
 * 	 - 로그인 인증 처리
 *   - 잔액 관리
 */

public class UserService {
	// 데이터 접근을 위한 Repository
	private UserRepository userRepository;
	
	// UserService 생성자 : UserRepository 인스턴스 생성
	public UserService() {
		userRepository = new UserRepository();
	}
	
	/*
	 * 회원 가입 처리 - ID 중복불가, 패스워드 최소 4자리, 이름 필수, 이름 최소 2자리
	 */
	
	public User register(String id, String password, String name) {
		// 입력값 검증 (한 곳에 모아서 처리)
		validateRegisterInput(id, password, name);
		
		// 비지니스 로직 1: ID 중복불가
		if(userRepository.existsById(id)) {
			throw new RuntimeException("이미 존재하는 ID입니다." + id);
		}
		
		// User 객체 생성
		User user = new User(id, password, name);
		
		// Repository 기능 호출
		User saveduser = userRepository.save(user);
		
		System.out.println("새 사용자 등록: "+ saveduser.getId());
		
		return saveduser;
		
	}

	/*
	 *  회원 가입 입력값 검증
	 */
	private void validateRegisterInput(String id, String password, String name) {
		ValidationUtils.requireNonEmpty(id,"ID를 입력해주세요.");
		ValidationUtils.requireMinLength(password, 4, "패스워드는 최소 4자리 이상이어야 합니다.");
		ValidationUtils.requireNonEmpty(name, "이름을 입력해주세요.");
		ValidationUtils.requireMinLength(name.trim(), 2, "이름은 2자 이상이어야 합니다.");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}