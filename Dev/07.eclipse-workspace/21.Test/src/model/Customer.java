package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 쇼핑몰 고객을 나타내는 클래스
 * Person 클래스를 상속받음
 */
public class Customer extends Person {
    private LocalDateTime joinDate; // 가입일

    /**
     * Customer 생성자
     * @param id 고객 ID
     * @param name 고객 이름
     * @param email 이메일
     */
    public Customer(String id, String name, String email) {
        super(id, name, email);
        this.joinDate = LocalDateTime.now(); // 생성자에서 가입일 자동 설정
    }

    @Override
    public String getUserType() {
        return "고객";
    }

    public LocalDateTime getJoinDate() { return joinDate; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return super.toString() +
                String.format(", 가입일: %s", joinDate.format(formatter));
    }
}