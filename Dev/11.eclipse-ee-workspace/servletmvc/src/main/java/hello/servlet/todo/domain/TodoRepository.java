package hello.servlet.todo.domain;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/*
 * Todo 저장소 클래스
 * 	- 싱글톤 패턴으로 구성
 * 	- 메모리 저장소 구현
 */
public class TodoRepository {
	
	// Thread-Safe를 위한 ConcurrentHashMap 사용
	private static Map<Long, Todo> store = new ConcurrentHashMap<>();
	
	// Thread-Safe를 위한 AtomicLong 사용 (동시성 문제 해결) 
	private static AtomicLong sequence = new AtomicLong(0L);
	
	// 싱글톤(외부에서 사용할 수 없는 생성자) 인스턴스 (클래스 로딩 시 한 번만 생성)
	private static final TodoRepository instance = new TodoRepository();
	
	// 싱글톤 인스턴스를 반환하는 메서드
    public static TodoRepository getInstance() {
        return instance;    // 유일한 인스턴스 반환
    }
    
    // 기본 생성자
    private TodoRepository() {
    	// 초기 더미 데이터 생성
    	initDummyData();
    }

	public void initDummyData() {
		Todo todo1 = new Todo("Spring MVC 공부", "컨트롤러, 서비스, 리파지토리의 패턴 공부", "HIGH");
		save(todo1);
		
		Todo todo2 = new Todo("웹 프로젝트 기획하기", "웹 프로젝트 기획 구상 및 기술하기", "HIGH");
		save(todo2);
		
		Todo todo3 = new Todo("자격증 공부하기", "기사 시험 문제 100문제 풀기", "MEDIUM");
		save(todo3);
		
		Todo todo4 = new Todo("기술 도서 읽기", "LLM 도서 3장까지 읽기", "LOW");
		save(todo4);
	}

	// 할 일 저장
	public Todo save(Todo todo) {
		// ID 자동 생성 및 설정 (1씩 증가)
		todo.setId(sequence.incrementAndGet());
		// Map 저장
		store.put(todo.getId(), todo);
		
		// 저장된 객체 반환
		return todo;
	}
	
	// ID로 할 일 조회
	public Todo findById(Long id) {
		return store.get(id);
	}
	
	// 모든 할 일 조회 - 생성일 기준 내림차순 정렬
	public List<Todo> findAll() {
		return store.values().stream()
		.sorted((a, b) -> b.getCreatedDate().compareTo(a.getCreatedDate()))
		.collect(Collectors.toList());
	}
	
	// 할 일 삭제
	public boolean delete(Long id) {
		Todo removed = store.remove(id);
		return removed != null;		// 제거 성공 여부 반환
	}
	
	// 할 일 수정
	public boolean update(Long id, String title,
			String description, String priority) {
		Todo todo = findById(id);
		if(todo != null) {
			todo.setTitle(title);
			todo.setDescription(description);
			todo.setPriority(priority);
			return true;
		}
		return false;
	}
	
	// 저장소 초기화
	public void clearStore() {
		store.clear(); 	// Map 초기화
		sequence.set(0L);
	}
} 