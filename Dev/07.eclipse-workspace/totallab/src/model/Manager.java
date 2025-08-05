package model;

/**
 * 쇼핑몰 관리자를 나타내는 클래스
 * Person 클래스를 상속받음
 */
public class Manager extends Person {
    private String department; // 부서
    private int employeeId;   // 사번
    
    /**
     * Manager 생성자
     * @param id 관리자 ID
     * @param name 관리자 이름
     * @param email 이메일
     * @param department 부서
     * @param employeeId 사번
     */
    public Manager(String id, String name, String email, String department, int employeeId) {
        super(id, name, email);
        this.department = department;
        this.employeeId = employeeId;
    }
    
    /**
     * Person의 추상 메서드 구현
     * @return "관리자"
     */
    @Override
    public String getUserType() {
        return "관리자";
    }
    
    public String getDepartment() { return department; }
    public int getEmployeeId() { return employeeId; }
    
    @Override
    public String toString() {
        return super.toString() + 
               String.format(", 부서: %s, 사번: %d", department, employeeId);
    }
}
