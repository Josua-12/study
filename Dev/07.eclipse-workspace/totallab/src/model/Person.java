package model;

/**
 * 사람을 나타내는 추상 클래스
 * Customer와 Manager의 공통 부모 클래스
 */
public abstract class Person {
    protected String id;     // 고유 ID
    protected String name;   // 이름
    protected String email;  // 이메일

    /**
     * Person 생성자
     * @param id 고유 ID
     * @param name 이름
     * @param email 이메일
     */
    public Person(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    /**
     * 사용자 유형을 반환하는 추상 메서드
     * 하위 클래스에서 반드시 구현해야 함
     * @return 사용자 유형 (예: "고객", "관리자")
     */
    public abstract String getUserType();

    @Override
    public String toString() {
        return String.format("ID: %s, 이름: %s, 이메일: %s, 유형: %s",
                id, name, email, getUserType());
    }
}