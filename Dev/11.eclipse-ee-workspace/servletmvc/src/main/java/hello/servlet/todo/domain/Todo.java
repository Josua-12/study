package hello.servlet.todo.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/*
 * Todo 엔티티 클래스
 * 	- 할일 정보를 담는 클래스
 * 	- 실무에서는 DTO와 Entity를 분리함
 */

@Getter @Setter
public class Todo {

	private Long id;						// 할일 고유 식별자 (primary Key 역할)
	private String title;					// 할일 제목 (필수)
	private String description;				// 할일 상세 설명 (선택)
	private boolean completed;				// 완료 여부 (True: 완료, false: 미완료)
	private String priority;				// 우선 순위 (HIGH, MEDIUM, LOW)
	private LocalDateTime createdDate;		// 생성 일시
	private LocalDateTime completedDate;	// 완료 일시 (완료 시에만 설정)
	
	// 기본 생성자 
	public Todo() {
		this.completed = false;					// 기본값 : 미완료 상태
		this.createdDate = LocalDateTime.now();	// 생성 시간을 현재 시간으로 설정
		this.priority ="MEDIUM";
	}

	// 제목, 설명, 우선순위 - 생성자
	public Todo(String title, String description, String priority) {
		//super();
		this.title = title;
		this.description = description;
		this.priority = priority != null ? priority : "MEDIUM";
		this.completed = false;
		this.createdDate = LocalDateTime.now();
	}
	
	// 할 일 완료 메서드 
	public void complete() {
		this.completed = true;						// 완료 상태로 변경
		this.completedDate = LocalDateTime.now();	// 완료 시간 기록
	}
	
	// 할 일 미완료 메서드
	public void uncomplete() {
		this.completed = false;		// 미완료 상태로 변경
		this.completedDate = null;	// 완료 시간 초기화
	}
	
	// 토글 메서드 - 완료, 미완료 상태를 전환
	public void toggleComplete() {
		if(this.completed) {
			uncomplete(); 	// 완료 -> 미완료
		} else {
			complete();		// 미완료 -> 완료
		}
	}
	
	
	
}
