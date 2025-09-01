package hello.servlet.todo.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TodoRepositoryTest {

	// 테스트되는 대상
	TodoRepository todoRepository = TodoRepository.getInstance();
	
	// 각 테스트 메서드 실행 후 저장소 초기화
	@AfterEach
	void afterEach() {
		todoRepository.clearStore();
	}
	
	// 각 테스트 전에도 저장소 초기화 (안정성)
	@BeforeEach
	void beforeEach() {
		todoRepository.clearStore();
	}
	
	@Test
	@DisplayName("할 일 저장 테스트")
	void save() {
		// given - 테스트 데이터 준비
		Todo todo = new Todo("Spring 공부하기", "MVC 패턴 구현", "HIGH");		
		// when
		Todo savedTodo =todoRepository.save(todo);
		// then (검증)
		assertThat(savedTodo.getId()).isNotNull();		// ID가 자동 생성되었는지
		assertThat(savedTodo.getTitle()).isEqualTo("Spring 공부하기");
		assertThat(savedTodo.getDescription()).isEqualTo("MVC 패턴 구현");
		assertThat(savedTodo.getPriority()).isEqualTo("HIGH");
		assertThat(savedTodo.isCompleted()).isFalse();
		assertThat(savedTodo.getCreatedDate()).isNotNull();  
	}
}
