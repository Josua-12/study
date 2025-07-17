package encap;

import java.util.Objects;

/*
 *  * 요구사항
 *  	- 캐릭터명, 레벨, 체력, 최대 체력 필드
 *  	- 체력 회복, 데미지 받기 메서드 제공
 *  	- 레벨업 메서드 제공
 *  	- 체력은 0 이하로 떨어지지 않고 최대 체력을 초과하지도 않음
 *  	- 캐릭터 사망 여부 확인 메서드 제공
 */

public class GameCharacter {
	// private 필드 선언
	private String charName;
	private int level;
	private int health;
	private int maxHealth;
	
	// 생성자 포함
	public GameCharacter(String charName) {
		//super();
		this.charName = Objects.requireNonNull(charName, "캐릭터명은 null일 수 없습니다.");
		this.level = 1;
		this.maxHealth = 100;		// 초기 최대 체력
		this.health = maxHealth;	// 현재 체력을 최대 체력으로 설정함
	}
	
	// 체력 회복 메서드 정의
	public void heal(int amount) {
		if(amount <= 0) {
			System.out.println("회복량은 0보다 커야 합니다.");
			return;
		}
        int prevHealth = health;
        health += amount;
        
        // 최대 체력 초과 방지
        if (health > maxHealth) {
            health = maxHealth;
        }
        System.out.println(charName + "이(가) " + (health - prevHealth) + "만큼 회복했습니다.");
        System.out.println("현재 체력: " + health + "/" + maxHealth);
    }
	// 데미지 받기 메서드 : 체력 감소 처리
	public void damage(int amount) {
		if(amount <= 0) {
			System.out.println("데미지는 0보다 커야 합니다.");
			return;
		}		
		health -= amount;		// 체력 감소
		// 체력은 0 이하로 떨어지지 않도록 함
		if(health < 0) {
			health = 0;
		}
		System.out.println(charName + "이(가) " + amount +"의 데미지를 받았습니다.");
		System.out.println("현재 체력: " + health + "/" + maxHealth);
		
		// 사망 확인
		if(health == 0) {
			System.out.println(charName + "이(가) 사망했습니다.");
		}
	}
	// 레벨 업 메서드
	public void levelUp() {
		level++;
		int healthIncrease= 20;		// 레벨업 시 최대 체력 증가분
		maxHealth += healthIncrease;	// 최대 체력 증가
		health = maxHealth;			// 레벨업 시 체력 완전 회복 
		System.out.println(charName + "이(가) 레벨업했습니다!");
		System.out.println("현재 레벨: " + level);
		System.out.println("최대 체력이 " + healthIncrease + " 증가했습니다!");
		System.out.println("체력이 완전히 회복되었습니다. " + "(" + health + "/" + maxHealth + ")");
	}
	
	// 사망 여부 확인 메서드
	public boolean isDead() {
		return health <= 0;		// 체력이 0 이하면 사망
	}
	
	public String getName() {
		return charName;
	}
	
	public void printCharInfo() {
		System.out.println("=== 캐릭터 정보 ===");
		System.out.println("이름: " + charName);
		System.out.println("레벨: " + level);
		System.out.println("체력: " + health);
		System.out.println("상태: " + (isDead() ? "사망" : "생존"));
	}	
}