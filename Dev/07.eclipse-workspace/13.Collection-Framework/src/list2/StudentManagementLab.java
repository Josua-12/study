package list2;
/*
 * 학생 관리 시스템
 * 	- ArrayList를 활용한 CRUD 작업
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class StudentManagementLab {

	// 전역변수
	private static List<Student> students = new ArrayList<>();
	
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		// 초기 데이터 생성 (DB에서 가져올 데이터)
		initializeData();
		
		int choice = 0;
		do {
			// 메뉴 출력
			printMenu();
			choice = scanner.nextInt();
			scanner.nextLine();	// 버퍼 지우기
			
			switch(choice) {
			
				case 0: System.out.println("프로그램을 종료합니다."); break;
				case 1: addStudent(); break;
				case 2: viewAllStudents(); break;
				case 3: searchStudent(); break;
				case 4: updateStudent(); break;
				default: System.out.println("잘못된 선택입니다.");
			}
		} while(choice != 0);
		
		scanner.close();
		
		
	}
	
	/*
	 * 기존 데이터 검색 후 수정
	 * 현재 정보 보여준 후 새 정보 입력받기
	 * 유효성 검증
	 */
	private static void updateStudent() {
		System.out.print("🔧 수정할 학생 이름 :");
		String targetName = scanner.nextLine();
		
		// 수정할 학생 찾기
		Student targetStudent = null; // 찾은 학생 객체를 저장할 변수
		
		for(Student student : students) {
			if(student.getName().equals(targetName)) {
				targetStudent = student;
				break; // 찾으면 루프 종료
			}
		}
		
		// 해당 학생을 찾지 못한 경우의 처리
		if(targetStudent == null) {
			System.out.println("❌ 해당 학생을 찾을 수 없습니다.");
			System.out.println("💡 '2. 전체 학생 조회'에서 등록된 학생을 찾아보세요.");
			return;
		}
		
		// 현재 정보 출력 - 사용자가 현재 값을 확인할 수 있도록 함
		System.out.println("\n 현재 정보");
		System.out.println("-".repeat(50));
		System.out.printf("이름: %s\n", targetStudent.getName());
		System.out.printf("나이: %s\n", targetStudent.getAge());
		System.out.printf("전공: %s\n", targetStudent.getMajor());
		System.out.printf("학점: %s\n", targetStudent.getGpa());
		System.out.println("-".repeat(50));
		
		// 수정 (유효성 검증 포함)
		int newAge;
		while(true) {
			System.out.print("새로운 나이 (현재: " + targetStudent.getAge() +  ") : ");
			try {
				newAge = scanner.nextInt();
				scanner.nextLine();
				
				if(newAge >= 0 && newAge <= 150) {
					break;
				} else {
					System.out.println("❌ 나이는 0 이상 150 이하여야 합니다. 다시 입력해주세요");
				}
			} catch(Exception e) {
				System.out.println("숫자만 입력해주세요!");
				scanner.nextLine();
			}
		}
		
		// 전공 수정 
		System.out.print("새로운 전공 (현재: " + targetStudent.getMajor() +  ") : ");
		String newMajor = scanner.nextLine();
		
		// 학점 수정
		double newGpa = 0.0;
		while(true) {
			System.out.print("새로운 학점 (현재: " + targetStudent.getGpa() +  ") : ");
			try {
				newGpa = scanner.nextDouble();
				scanner.nextLine();
				
				if(newGpa >= 0.0 && newGpa <= 4.0) {
					break;
				} else {
					System.out.println("❌ 학점은 0.0 이상 4.0 이하여야 합니다. 다시 입력해주세요");
				}
			} catch(Exception e) {
				System.out.println("숫자만 입력해주세요!");
				scanner.nextLine();
			}
		}
		
		//setter()
		targetStudent.setAge(newAge);
		targetStudent.setMajor(newMajor);
		targetStudent.setGpa(newGpa);
		
		// 성공 메시지 출력
		System.out.println("✅ 학생 '" + targetName + "'이(가) 성공적으로 추가되었습니다!");
	}

	private static void searchStudent() {
		System.out.print("🔎 검색할 학생 이름(부분 검색 기능) : ");
		String searchName = scanner.nextLine();
		
		// 지역변수로 별개의 임시 저장소 리스트 생성
		List<Student> foundStudents = new ArrayList<>();
		
		// 전체 학생 리스트에서 검색어가 포함된 학생 찾기
		for(Student student : students) {
			if(student.getName().toLowerCase().contains(searchName.toLowerCase())) {
					foundStudents.add(student);
			}
		}
		
		if(foundStudents.isEmpty()) {
			System.out.println("❌ '" + searchName + "'을(를) 포함한 학생을 찾을 수 없습니다.");
			System.out.println("💡 정확한 이름이나 이름의 일부를 입력해보세요.");
		} else {
			System.out.println("\n✅ 검색 결과 : " + foundStudents.size() + "명");
			System.out.println("-".repeat(50));
			System.out.printf("%-10s %-5s %-15s %-5s\n", "이름", "나이", "전공", "학점");
			System.out.println("-".repeat(50));
			
			// 검색된 학생들만 출력 (foundStudents 리스트)
			for(Student s : foundStudents) {
				System.out.printf ("%-10s %-5d %-15s %-5.1f\n", s.getName(), s.getAge(), s.getMajor(), s.getGpa());
			}
		}
		
	}

	/*
	 * 학생 추가
	 */
	private static void addStudent() {
	    System.out.print("\n 새 학생 정보를 입력하세요.");
	    System.out.println("-".repeat(25));
	    
	    // 학생 이름 입력
	    System.out.println("이름 : ");
	    String name = scanner.nextLine();
	    
	    // 나이 입력 -- 유효성 검증
	    int age = 0;
	    while(true) {
	        System.out.print("나이 (0-150) : ");
	        try {
	            age = scanner.nextInt();
	            scanner.nextLine();
	            if(age >= 0 && age <= 150) {
	                break;
	            } else {
	                System.out.println("❌ 나이는 0 이상 150 이하여야 합니다. 다시 입력해주세요");
	            }
	        } catch(Exception e) {
	            System.out.println("❌ 숫자만 입력해주세요!");
	            scanner.nextLine();
	        }
	    }
	    
	    // 전공 입력
	    System.out.print("전공 : ");
	    String major = scanner.nextLine();
	    
	    // 학점 입력 -- 유효성 검증
	    double gpa = 0.0;
	    while(true) {
	        System.out.print("학점 (0.0 ~ 4.0) : ");
	        try {
	            gpa = scanner.nextDouble();
	            scanner.nextLine();
	            if(gpa >= 0.0 && gpa <= 4.0) {
	                break;
	            } else {
	                System.out.print("❌ 학점은 0.0 이상 4.0 이하여야 합니다. 다시 입력해주세요");
	            }
	        } catch(Exception e) {
	            System.out.println("❌ 숫자만 입력해주세요!");
	            scanner.nextLine();
	        }
	    }
	    
	    // Student 객체 생성 및 ArrayList에 추가
	    Student newStudent = new Student(name, age, major, gpa);
	    students.add(newStudent);
	    System.out.println("✅ 학생 " + name + "'이(가) 성공적으로 추가되었습니다!");
	    System.out.println("📊 현재 총 학생 수 : " + students.size());
	}


	private static void viewAllStudents() {
		System.out.println("\n===== 전체 학생 목록 =====");
		System.out.println("이름        나이		  전공        학점");
		System.out.println("------------------------------------");
		for(Student s : students) {
			System.out.printf ("%s        %d       %s        %.1f\n", s.getName(), s.getAge(), s.getMajor(), s.getGpa());
		}
		System.out.printf("총 %s명의 학생이 등록되어 있습니다.\n", students.size());
		System.out.println();
		
		// 2. Iterator 사용
		System.out.println("\n [Iterator 사용]");
		Iterator<Student> itr = students.iterator();
		int count = 1;
		while(itr.hasNext()) {
			Student student = itr.next();
			System.out.println(count + ". " + student.getName() + "(" + student.getMajor() + ")");
			
			count++;
		}
	}
	
	
	

	private static void printMenu() {
		System.out.println("\n=== 학점 관리 시스템 ===");
		System.out.println("1. 학생 추가");
		System.out.println("2. 전체 학생 조회");
		System.out.println("3. 학생 검색");
		System.out.println("4. 학생 정보 수정");
		System.out.println("5. 학생 삭제");
		System.out.println("6. 통계 정보");
		System.out.println("0. 종료");
		System.out.print("선택 : ");
		
	}

	private static void initializeData() {
		students.add(new Student("이순신", 20, "컴퓨터공학", 3.8));
		students.add(new Student("신사임당", 20, "경제학과", 3.9));
		students.add(new Student("류성룡", 19, "전자공학", 4.0));
		students.add(new Student("유관순", 22, "디자인", 4.0));
		students.add(new Student("손흥민", 20, "컴퓨터공학", 3.7));
	}
}
