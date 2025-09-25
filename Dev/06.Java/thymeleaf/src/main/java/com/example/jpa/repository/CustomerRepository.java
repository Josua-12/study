package com.example.jpa.repository;


import com.example.jpa.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // 1) JpaRepository가 제공하는 기본 메시드들 : save(), findById(), deleteById(), count()..

    //2) 쿼리 메서드
    /*
        단순 조건 검색-username으로 고객 검색
        findBy = SELECT 쿼리
        Username = WHERE username 조건
        파라미터 (String username) = 조건 값

        => SELECT * FROM customer
           WHERE username = ?
     */
    Optional<Customer> findByUsername(String username);

    /*
        And 조건 검색
        - 로그인 체크
        - username과 password로 로그인 체크
        - SELECT * FROM customer WHERE username = ? AND password = ?
     */
    Optional<Customer> findByUsernameAndPassword(String username, String password);

    /*
        OR 조건 검색
        - 이름이나 직업으로 고객 검색
        - SELECT * FROM customer WHERE customername = ? OR occupation = ?
     */
    List<Customer> findByCustomerNameOrOccupation(String name, String occupation);

    /*
        비교 연산자 - Greater Than (초과)
        - 특정 나이 초과 고객 조회
        - SELECT * FROM customer WHERE age > ?
     */
    List<Customer> findByAgeGreaterThan(int age);

    /*
        비교 연산자 - Less Than Equal (이하)
        - 특정 나이 이하 고객 조회
        - SELECT * FROM customer WHERE age <= ?
     */
    List<Customer> findByAgeLessThanEqual(int age);

    /*
        Between - 범위 검색
        - 나이 범위로 고객 검색
        - SELECT * FROM customer WHERE age BETWEEN ? AND ?
     */
    List<Customer> findByAgeBetween(int startAge, int endAge);

    /*
        Like 검색 - Containing (포함)
        - 특정 문자가 포함된 고객 검색
        - SELECT * FROM customer WHERE customerName LIKE %?%
     */
    List<Customer> findByCustomerNameContaining(String keyword);

    /*
        Like 검색 - StartingWith (시작)
        - 특정 문자로 시작하는 username 검색
        - SELECT * FROM customer WHERE username LIKE ?%
     */
    List<Customer> findByUsernameStartingWith(String prefix);

    /*
        정렬 - Order By
        - 등급별로 나이 내림차순 정렬
        - SELECT * FROM customer WHERE rating = ? ORDER BY age DESC
     */
    List<Customer> findByRatingOrderByAgeDesc(String rating);

    /*
        복합 조건 + 정렬
        - 나이 범위 + 등급 + 적립금 정렬
        - SELECT * FROM customer WHERE rating = ? ORDER BY reserves DESC
     */
    List<Customer> findByAgeBetweenAndRatingOrderByReservesDesc(
            int startAge, int endAge, String rating
    );

    /*
        문제 : 삭제되지 않은 활성 회원들의 목록을 조회하시오. (deleted)
            - findByDeletedFalse()
     */
    List<Customer> findByDeletedFalse();

    /*
        문제 : 특정 회원 등급에 속한 회원의 총 인원수를 계산하시오.
            - countByRating()
     */
    long countByRating(String rating);

    /*
        문제 : 입력받은 아이디가 이미 사용 중인지 중복 체크하시오.
            - existsByUsername()
     */
    boolean existsByUsername(String username);

    /*
        문제 : 적립금이 가장 많은 상위 5명의 회원을 조회하시오.
            - findTop5ByOrderByReservesDesc()
     */
    List<Customer> findTop5ByOrderByReservesDesc();


    // ================== JPQL ==========================
    /*
        문제1: 입력받은 등급(rating)과 일치하면서, 삭제 상태(deleted)가 false인
        회원들을 모두 조회하는 JPQL을 작성하시오.
     */
    @Query("SELECT c FROM Customer c WHERE c.rating = :rating " +
            "AND c.deleted = false")
    List<Customer> findActiveCustomersByRating(@Param("rating") String rating);

    /*
        문제2: 특정 나이 범위에 속하면서 GOLD 또는 PLATINUM 등급이고,
              삭제 상태가 false인 회원들을 모두 조회하는 JPQL을 작성하시오.
     */
    @Query("SELECT c FROM Customer c " +
            "WHERE c.age BETWEEN :minAge AND :maxAge " +
            "AND c.rating IN('GOLD', 'PLATINUM') " +
            "AND c.deleted = false " +
            "ORDER BY c.reserves DESC")
    List<Customer> findPlatinumCustomersInAgeRange(@Param("minAge") int minAge,
                                                   @Param("maxAge")int maxAge);

    /*
    문제 3:
        삭제되지 않은 활성 회원들을 등급별로 그룹화하여 다음 통계 정보를 출력하시오.
         출력- 등급명, 회원 수, 평균 나이, 총 적립금 합계 출력
              회원수가 많은 등급부터 정렬하여 출력하시오.
     */
    @Query("SELECT c.rating, COUNT(c), AVG(c.age), SUM(c.reserves) " +
            "FROM Customer c " +
            "WHERE c.deleted = false " +
            "GROUP BY c.rating " +
            "ORDER BY COUNT(c) DESC")
    List<Object[]> getCustomerStaticsByRating();

    /*
     문제 4: 전체 회원의 평균 적립금보다 많은 적립금을 보유한 활성 회원들을 출력하시오.

     */
    @Query("SELECT c FROM Customer c " +
            "WHERE c.reserves > (SELECT AVG(c2.reserves) FROM Customer c2) " +
            "AND c.deleted = false")
    List<Customer> findCustomersAboveAverageReserves();
}


















