package com.example.jpa.service;

import com.example.jpa.entity.Customer;
import com.example.jpa.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service    // Service Bean으로 등록, 비지니스 로직 처리 계층
@AllArgsConstructor // 모든 필드를 파라미터로 받는 생성자 자동 생성 (DI용)
public class CustomerService {
    private final CustomerRepository customerRepository;    //생성자 주입

    //회원 등록
    @Transactional  // insert, update, delete 메서드 실행중 예외 발생시 자동 롤백
    public Customer register(Customer customer) {
        return customerRepository.save(customer);   // save() -> insert SQL
    }

    //특정회원 한명의 정보 가져오기
    @Transactional(readOnly = true)  // 읽기 전용, 성능 최적화
    public Optional<Customer> getById(Long id) {    // PK(id)
        return customerRepository.findById(id);     // SELECT * FROM customer WHERE id = ?
    }

    //회원목록 가져오기
    @Transactional(readOnly = true)
    public List<Customer> getAllCustomer() {

        List<Customer> allCustomers = customerRepository.findAll();

        // 삭제되지 않은 회원만 필터링 (deleteAt = false인 회원만)
        return allCustomers.stream()
                .filter(customer -> !customer.isDeleted())   // delete가 false인 것만
                .toList();
    }

    /*
        회원 수정
            - save() 메서드는 ID가 있으면 Update, 없으면 Insert
     */
    @Transactional
    public Customer update(Customer customer) {
        // 기존 회원 정보 조회
        Customer existingCustomer =
        customerRepository.findById(customer.getId())
                .orElseThrow(()->new IllegalArgumentException(
                        "회원을 찾을 수 없습니다. ID : " + customer.getId()
                ));
        // 수정 가능한 필드만 업데이트 (username은 변경 불가능하므로 제외)
        existingCustomer.setCustomerName(customer.getCustomerName());
        existingCustomer.setAge(customer.getAge());
        existingCustomer.setOccupation(customer.getOccupation());
        existingCustomer.setRating(customer.getRating());
        existingCustomer.setReserves(customer.getReserves());

        // 변경된 엔터티 저장
        return customerRepository.save(existingCustomer);
    }

    @Transactional
    public void softDelete(Long id) {
        // 삭제할 회원 조회
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "회원을 찾을 수 없습니다. ID :" + id
                ));
        // 이미 삭제된 회원인지 확인
        if (customer.isDeleted()) {
            throw new IllegalArgumentException("이미 삭제된 회원입니다.");
        }

        // 삭제 플래그 설정
        customer.setDeleted(true);          // 삭제 상태로 변경
        customer.setDeletedAt(new Date());  // 삭제 시간 기록

        // 변경사항 저장 (UPDATE SQL 실행)
        customerRepository.save(customer);
    }

    // 쿼리 메서드 테스트
    @Transactional(readOnly = true)
    public void testQueryMethods() {
        Optional<Customer> user = customerRepository.findByUsername("test");
        System.out.println("test 사용자 조회");
        user.ifPresent(c -> System.out.println(" -> " + c.getCustomerName()));
    }

    // 쿼리 메서드 테스트 - 로그인 체크
    @Transactional(readOnly = true)
    public void test2FindByUsernameAndPassword() {
        System.out.println("\n [test2] test2FindByUsernameAndPassword");
        System.out.println("=========================================");

        Optional<Customer> loginUser =
        customerRepository.findByUsernameAndPassword("test1", "123");

        System.out.println(" * 테스트 계정 : test1 / 123");
        System.out.println(" * 테스트 결과 : " + (loginUser.isPresent() ? "✅ 성공" : "❌ 실패"));

        if (loginUser.isPresent()) {
            System.out.println(" * 로그인 사용자 : " + loginUser.get().getCustomerName());
        }
        System.out.println("\n 생성된 SQL : SELECT * FROM customer WHERE username = ? AND password = ?");
    }

    // 쿼리 메서드 테스트 - OR 조건 검색
    @Transactional(readOnly = true)
    public void test3FindByCustomerNameOrOccupation() {
        System.out.println("\n [test3] test3FindByCustomerNameOrOccupation");
        System.out.println("=========================================");

        List<Customer> orResult =
        customerRepository.findByCustomerNameOrOccupation("손흥민", "국가대표선수");
        System.out.println("* 검색 조건 : 이름='손흥민' OR '개발자'");
        System.out.println("* 검색 결과 : " + orResult.size() + "명");
        for (Customer c : orResult) {
            System.out.println(" => " + c.getCustomerName() + "(" + c.getOccupation() + ")");
        }
        System.out.println("\n 생성된 SQL : SELECT * FROM customer WHERE customerName = ? OR occupation = ?");
    }

    // 쿼리 메서드 테스트 - 나이 초과
    @Transactional(readOnly = true)
    public void test4FindByAgeGreaterThan() {
        System.out.println("\n [test4] test4FindByAgeGreaterThan");
        System.out.println("=========================================");
        
        List<Customer> overAge = customerRepository.findByAgeGreaterThan(25);
        System.out.println("* 검색 조건 : 나이 > 25");
        System.out.println("* 검색 결과 : " + overAge.size() + "명");
        overAge.stream().limit(3).forEach(c ->
            System.out.println(" => " + c.getCustomerName() + "(" + c.getAge() + "세)"));
            System.out.println("\n 생성된 SQL: SELECT * FROM customer WHERE age > ?");
    }

    // 쿼리 메서드 테스트 - 나이 이하
    @Transactional(readOnly = true)
    public void test5FindByAgeLessThanEqual() {
        System.out.println("\n [test5] test5FindByAgeLessThanEqual");
        System.out.println("=========================================");

        List<Customer> lessAge = customerRepository.findByAgeLessThanEqual(40);
        System.out.println("* 검색 조건 : 나이 <= 40");
        System.out.println("* 검색 결과 : " + lessAge.size() + "명");
        lessAge.stream().limit(5).forEach(c ->
                System.out.println(" => " + c.getCustomerName() + "(" + c.getAge() + "세)"));
        System.out.println("\n 생성된 SQL: SELECT * FROM customer WHERE age <= ?");
    }

    // Between 범위 검색
    @Transactional(readOnly = true)
    public void test6FindByAgeBetween() {
        System.out.println("\n [test6] test6FindByAgeBetween");
        System.out.println("=========================================");

        List<Customer> ageRange = customerRepository.findByAgeBetween(20, 30);
        System.out.println("* 검색 조건 : 20 <= 나이 <= 30");
        System.out.println("* 검색 결과 : " + ageRange.size() + "명");
        ageRange.stream().limit(5).forEach(c ->
                System.out.println(" => " + c.getCustomerName() + "(" + c.getAge() + "세)"));
        System.out.println("\n 생성된 SQL: SELECT * FROM customer WHERE age BETWEEN ? AND ?");
    }

    // Containing 포함 검색
    @Transactional(readOnly = true)
    public void test7FindByCustomerNameContaining() {
        System.out.println("\n [test7] test7FindByCustomerNameContaining");
        System.out.println("============================================");

        List<Customer> containingName = customerRepository.findByCustomerNameContaining("순");
        System.out.println("* 검색 조건 : '순'이 들어가는 고객명");
        System.out.println("* 검색 결과 : " + containingName.size() + "명");
        containingName.forEach(c ->
                System.out.println(" => " + c.getCustomerName() + "(" + c.getAge() + "세)"));
        System.out.println("\n 생성된 SQL: SELECT * FROM customer WHERE customerName LIKE '%?%'");
    }

    // username 시작 문자 검색
    @Transactional(readOnly = true)
    public void test8FindByUsernameStartingWith() {
        System.out.println("\n [test8] test8FindByUsernameStartingWith");
        System.out.println("============================================");

        List<Customer> startsWith = customerRepository.findByUsernameStartingWith("이");
        System.out.println("* 검색 조건 : '이'로 시작하는 고객명");
        System.out.println("* 검색 결과 : " + startsWith.size() + "명");
        startsWith.forEach(c ->
                System.out.println(" => " + c.getUsername() + "(" + c.getCustomerName()+ ")"));
        System.out.println("\n 생성된 SQL: SELECT * FROM customer WHERE username LIKE '?%'");
    }

    // 정렬
    @Transactional(readOnly = true)
    public void test9FindByRatingOrderByAgeDesc() {
        System.out.println("\n [test9] test9FindByRatingOrderByAgeDesc");
        System.out.println("============================================");

        List<Customer> platinumSorted = customerRepository.findByRatingOrderByAgeDesc("PLATINUM");
        System.out.println("* 검색 조건 : PLATINUM, 나이 내림차순");
        System.out.println("* 검색 결과 : " + platinumSorted.size() + "명");
        platinumSorted.forEach(c ->
                System.out.println(" => " + c.getUsername() + "(" + c.getAge()+ "세)"));
        System.out.println("\n 생성된 SQL: SELECT * FROM customer WHERE rating = ? ORDER BY age DESC");
    }


    @Transactional(readOnly = true)
    public void test10FindByAgeBetweenAndRatingOrderByReservesDesc() {
        System.out.println("\n [test10] test10FindByAgeBetweenAndRatingOrderByReservesDesc");
        System.out.println("==============================================================");

        List<Customer> multiple = customerRepository.findByAgeBetweenAndRatingOrderByReservesDesc(20, 30, "BRONZE");
        System.out.println("* 검색 조건 : 나이 범위 + 등급별 적립금 정렬");
        System.out.println("* 검색 결과 : " + multiple.size() + "명");
        multiple.forEach(c ->
                System.out.println(" => " + c.getCustomerName() + "(적립금" + c.getReserves()+ ")"));
        System.out.println("\n 생성된 SQL: SELECT * FROM customer WHERE age BETWEEN ? AND ? AND rating = ? ORDER BY reserves DESC");
    }

    @Transactional(readOnly = true)
    public void test11FindByDeletedFalse() {
        System.out.println("\n [test11] test11FindByDeletedFalse");
        System.out.println("=====================================");

        List<Customer> deleted = customerRepository.findByDeletedFalse();
        System.out.println("* 검색 조건 : deleted = false");
        System.out.println("* 활성 고객 수 : " + deleted.size() + "명");
        deleted.forEach(c ->
                System.out.println(" => " + c.getCustomerName() + "(" + c.getAge()+ "세)"));
        System.out.println("\n 생성된 SQL: SELECT * FROM customer WHERE deleted = false");
    }

    @Transactional(readOnly = true)
    public void test12CountByRating() {
        System.out.println("\n [test12] test12CountByRating");
        System.out.println("===============================");

        long goldCount = customerRepository.countByRating("GOLD");
        long silverCount = customerRepository.countByRating("SILVER");
        long bronzeCount = customerRepository.countByRating("BRONZE");
        long platinumCount = customerRepository.countByRating("PLATINUM");

        System.out.println("* Gold 등급 : " + goldCount + "명");
        System.out.println("* SILVER 등급 : " + silverCount + "명");
        System.out.println("* BRONZE 등급 : " + bronzeCount + "명");
        System.out.println("* PLATINUM 등급 : " + platinumCount + "명");

        System.out.println("\n 생성된 SQL: SELECT COUNT(*) FROM customer WHERE rating = ?");
    }

    @Transactional(readOnly = true)
    public void test13ExistsByUsername() {
        System.out.println("\n [test13] test13ExistsByUsername");
        System.out.println("=================================");

        boolean exists1 = customerRepository.existsByUsername("jo");
        boolean exists2 = customerRepository.existsByUsername("abcde");

        System.out.println("* 'jo' 존재 여부 : " + (exists1 ? "존재하는 아이디입니다." : "존재하지 않는 아이디입니다."));
        System.out.println("* 'abcde' 존재 여부 : " + (exists2 ? "존재하는 아이디입니다." : "존재하지 않는 아이디입니다."));

        System.out.println("\n 생성된 SQL: SELECT EXISTS (SELECT 1 FROM customer WHERE username = ?)");
    }

    @Transactional(readOnly = true)
    public void test14FindTop5ByOrderByReservesDesc() {
        System.out.println("\n [test14] test14FindTop5ByOrderByReservesDesc");
        System.out.println("===============================================");

        List<Customer> top5 = customerRepository.findTop5ByOrderByReservesDesc();

        System.out.println(" * 적립금 TOP 5 :");
        int rank = 1;
        for(Customer c: top5) {
            System.out.println(" " + rank + "위 : " + c.getCustomerName() +
                    " (적립금 : " + c.getReserves() + "원)");
            rank++;
        }
        System.out.println("\n 생성된 SQL : SELECT * FROM customer ORDER BY reserves LIMIT 5");
    }

    // ================== JPQL 테스트 메서드들 ====================
    // 1. 기본 JPQL (where 조건)
    @Transactional(readOnly = true)
    public void jpqlTest1FindActiveCustomersByRating() {
        System.out.println("\n [JPQL test1] jpqlTest1FindActiveCustomersByRating");
        System.out.println("===============================================");

        List<Customer> bronzeActive = customerRepository.findActiveCustomersByRating("BRONZE");

        System.out.println(" * 검색 결과 : " + bronzeActive.size() + "명");
        bronzeActive.stream().limit(2).forEach(c -> {
            System.out.println(" => " + c.getCustomerName() + "(" + c.getRating() + ")");
        });
    }

    @Transactional(readOnly = true)
    public void jpqlTest2FindPlatinumCustomerInAgeRange() {
        System.out.println("\n [JPQL test2] jpqlTest2FindPlatinumCustomerInAgeRange");
        System.out.println("=======================================================");

        List<Customer> platinumInRange = customerRepository.findPlatinumCustomerInAgeRange(20, 40);

        System.out.println(" * 검색 결과 : " + platinumInRange.size() + "명");
        platinumInRange.stream().limit(3).forEach(c -> {
            System.out.println(" => " + c.getCustomerName() + "(" + c.getAge() + "세" +
                    ", " + c.getRating() + ")");
        });
    }

    // 집계 함수, 그룹화
    @Transactional(readOnly = true)
    public void jpqlTest3GetCustomerStaticsByRating() {
        List<Object[]> statics = customerRepository.getCustomerStaticsByRating();

        System.out.println("\n 등급별 통계 : ");
        for (Object[] stat : statics) {
         String rating = (String) stat[0];
         Long count = (Long) stat[1];
         Double avgAge = (Double) stat[2];
         Long totalReserves = (Long) stat[3] != null ? (Long) stat[3] : 0L;

        System.out.printf(" => %s등급 : %d명, 평균 나이 : %.1f세, 총 적립금 : %d원\n",
                            rating, count, avgAge != null ? avgAge : 0.0, totalReserves);
        System.out.println("\n 특징 : GROUP BY와 집계함수 사용");
        }
    }

    // JPQL - 서브 쿼리
    @Transactional(readOnly = true)
    public void jpqlTest4FindCustomersAboveAverageReserves() {
        System.out.println("\n [JPQL test4] jpqlTest4FindCustomersAboveAverageReserves");
        System.out.println("===========================================================");

        List<Customer> aboveAverages = customerRepository.findCustomersAboveAverageReserves();

        System.out.println(" * 검색 결과 : " + aboveAverages.size() + "명");
        aboveAverages.stream().limit(5).forEach(c -> {
            System.out.println(" => " + c.getCustomerName() + ", 적립금 : " + c.getReserves() + ")");
        });
        System.out.println("\n 특징 : where 절에 서브쿼리 사용");
    }
}
