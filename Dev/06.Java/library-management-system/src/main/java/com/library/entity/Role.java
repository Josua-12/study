package com.library.entity;

/*
    사용자 권한을 정의하는 열거형
    Spring Security의 권한 체계와 연동
 */
public enum Role {

    USER("ROLE_USER", "일반 사용자"),
    LIBRARIAN("ROLE_LIBRARIAN", "사서"),
    ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String description;

    Role(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }

    // 권한 레벨 반환
    public int getLevel() {
        switch (this) {
            case USER: return 1;
            case LIBRARIAN: return 2;
            case ADMIN: return 3;
            default: return 0;
        }
    }
}
