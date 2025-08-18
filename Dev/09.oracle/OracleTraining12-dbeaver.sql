-- 9-1
-- 단일 행 서브쿼리

/*
 *  JONES라는 직원의 급여를 조회
 */

SELECT SAL
FROM EMP
WHERE ENAME = 'JONES';

-- 9-2
/*
 *  급여가 2975보다 큰 직원들을 조회하시오
 */
SELECT *
FROM EMP
WHERE SAL > 2975;

-- 9-3
/* 
 * JONES의 급여보다 많이 받는 직원들을 조회하시오
 */
SELECT *
FROM EMP
WHERE SAL > (
				SELECT SAL
				FROM EMP
				WHERE ENAME = 'JONES'
			);

-- 9-4
/*
 *  SCOTT보다 먼저 입사한 직원들을 조회하시오
 */
SELECT *
FROM EMP
WHERE ENAME = 'SCOTT';

SELECT *
FROM EMP
WHERE HIREDATE < (
		SELECT HIREDATE
		FROM EMP
		WHERE ENAME = 'SCOTT'
	);

-- 9-5 : 서브쿼리와 조인을 함께 사용
/* 
 * 20번 부서에서 전체 직원 평균 급여보다 많이 받는 직원과 부서 정보를 조회하시오.
 */
SELECT AVG(SAL)
FROM EMP;

SELECT E.EMPNO , E.ENAME , E.JOB , E.SAL , D.DEPTNO , D.LOC 
FROM EMP E, DEPT D
WHERE E.DEPTNO = D.DEPTNO 	-- 조인 조건
AND E.DEPTNO = 20
AND E.SAL > (
		SELECT AVG(SAL)
		FROM EMP
	);

-- 9-6. IN 연산자
/*
 *	20번 또는 30번 부서에 속한 직원들을 조회하시오
 */ 
SELECT *
FROM EMP
WHERE DEPTNO IN (20, 30);

-- 9-7. 다중행 서브쿼리 - IN 연산자
/*
 *  각 부서별 최고 급여를 받는 직원들을 조회하시오
 */
SELECT DEPTNO, MAX(SAL)
FROM EMP
GROUP BY DEPTNO;

SELECT *
FROM EMP
WHERE SAL IN (
		SELECT MAX(SAL)		-- 서브쿼리
		FROM EMP
		GROUP BY DEPTNO
	);

-- 9-9 : ANY 연산자 사용
/*
 *	어느 한 부서의 최고 급여와 같은 급여를 받는 직원을 조회하시오
 */
SELECT MAX(SAL)
FROM EMP
GROUP BY DEPTNO;

SELECT *
FROM EMP
WHERE SAL = ANY (
		SELECT MAX(SAL)	-- 5000, 3000, 2850 중 하나와 같으면 OK
		FROM EMP
		GROUP BY DEPTNO
	);

-- 9-10 : SOME 연산자
/* 
 * SOME은 ANY와 완전히 동일한 기능
 * 어느 한 부서의 최고 급여와 같은 급여를 받는 직원을 조회하시오
 */
SELECT *
FROM EMP
WHERE SAL = SOME (
		SELECT MAX(SAL)
		FROM EMP
		GROUP BY DEPTNO
	);

-- 9-11, 9-12 : ANY 연산자 - 부등호 비교
/*
 * 	30번 부서 직원들 중 누군가보다 급여가 적은 직원들을 조회하시오.
 * < any : 최대값보다 작으면 true
 */
SELECT SAL
FROM EMP
WHERE DEPTNO = 30;

SELECT *
FROM EMP
WHERE SAL < ANY (
		SELECT SAL			-- 2850, ..., 950
		FROM EMP			-- 2850보다 작은 모든 직원이 조회함
		WHERE DEPTNO = 30
	);

-- 9-13 : ANY 연산자
/*
 * 	30번 부서 직원들 중 누군가보다 급여가 많은 직원을 조회하시오
 *  > any : 최소값보다 크면 true
 */
SELECT *
FROM EMP
WHERE SAL > ANY (
		SELECT SAL			-- 2850, ..., 950
		FROM EMP			-- 최솟값(950)보다 큰 직원들 조회
		WHERE DEPTNO = 30
	);

-- 9-14 : ALL 연산자 -- 작은 값 비교
/*
 *	30번 부서의 모든 직원보다 급여가 적은 직원을 조회하시오.
 *	< ALL : 최소값보다 작아야 TRUE
 */

SELECT *
FROM EMP
WHERE SAL < ALL (
		SELECT SAL			-- 2850, ..., 950
		FROM EMP			-- 모든 값보다 작아야 함 (<950)
		WHERE DEPTNO = 30
)

-- 9-15 : ALL 연산자
/* 
 *	30번 부서의 모든 직원보다 급여가 많은 직원을 조회하시오
 *	> ALL : 최대값보다 커야 TRUE (2850보다 커야함)
 */
SELECT *
FROM EMP
WHERE SAL > ALL (
		SELECT SAL			-- 2850, ..., 950
		FROM EMP			-- 모든 값보다 커야 함(>2850)
		WHERE DEPTNO = 30
)

-- 9-16. EXISTS 연산자 - TRUE 케이스
/*
 * EXISTS : 서브쿼리 결과가 한 건이라도 있으면 TRUE
 * "10번 부서가 존재하면 모든 직원을 조회하시오."
 */
SELECT *
FROM DEPT
WHERE DEPTNO = 10;

SELECT *
FROM EMP 
WHERE EXISTS (
		SELECT *
		FROM DEPT
		WHERE DEPTNO = 10
	);

	
-- 9-17 : EXITST 연산자 - FALSE 케이스
/*
 * 	50번 부서는 존재하지 않으므로 아무도 조회되지 않음
 * 	EXISTS는 존재 여부만 확인, 실제 값은 사용하지 않음
 */
SELECT *
FROM DEPT
WHERE DEPTNO = 10;

SELECT *
FROM EMP 
WHERE EXISTS (
		SELECT *
		FROM DEPT
		WHERE DEPTNO = 50
	);

-- 9-18 : 다중 열 서브쿼리
/*
 * 	"각 부서에서 최고 급여를 받는 직원들을 조회하시오"
 * 	- (부서번호, 급여) 쌍을 동시에 비교
 */
SELECT DEPTNO, MAX(SAL)
FROM EMP
GROUP BY DEPTNO;

SELECT *
FROM EMP
WHERE (DEPTNO, SAL) IN (
		SELECT DEPTNO, MAX(SAL)
		FROM EMP
		GROUP BY DEPTNO
	);

-- 9-19 : 인라인 뷰 (FROM 절 서브쿼리) 사용
/*
 *	FROM절에 서브쿼리를 사용하여 가상 테이블 생성
 *	"10번 부서 직원들과 부서 정보를 조인해서 출력하시오" 
 */
SELECT E10.EMPNO , E10.ENAME , E10.DEPTNO , D.DNAME , D.LOC 
FROM (SELECT * FROM EMP WHERE DEPTNO = 10) E10, -- 인라인 뷰 : 10번 부서 직원
(SELECT * FROM DEPT) D							-- 인라인 뷰 : 모든 부서
WHERE E10.DEPTNO = D. DEPTNO ;

-- 9-20. WITH절
/*
 *	WITH절을 사용하여 임시 테이블 정의
 *	- 가독성이 좋고 재사용 가능 
 */

WITH 
E10 AS (SELECT * FROM EMP WHERE DEPTNO = 10),	-- 10번 부서 직원
D AS (SELECT * FROM DEPT)
SELECT E10.EMPNO, E10.ENAME, E10. DEPTNO, D.DNAME, D.LOC
FROM E10, D
WHERE E10.DEPTNO = D.DEPTNO ;

-- 9-21 : 스칼라 서브쿼리 (SELECT절 서브쿼리)
/*
 *  "각 직원의 등급과 부서명을 함께 표시하시오."
 */
SELECT E.EMPNO, E.ENAME, E.JOB, E.SAL,
       (
           SELECT GRADE
           FROM SALGRADE
           WHERE E.SAL BETWEEN LOSAL AND HISAL
       ) AS SALGRADE,
       E.DEPTNO,
       (
           SELECT D.DNAME
           FROM DEPT D
           WHERE E.DEPTNO = D.DEPTNO
       ) AS DNAME
FROM EMP E;


-- SUBQUERY LAB
/*
 * 	평균 급여보다 많이 받으면서 MANAGER(JOB) 직급인 직원의
 * 	사번, 이름, 직급, 급여를 조회하시오. 
 */
SELECT EMPNO, ENAME, JOB, SAL 
FROM EMP
WHERE JOB = 'MANAGER' 
AND SAL > (
		SELECT AVG(SAL)		
		FROM EMP			
);

/*
 *	4. ANY 연산자 사용
 *
 * 	SALESMAN 직급 중 누구보다도 급여를 많이 받는 다른 직급의 직원들을 조회하시오.
 * 	(급여순으로 정렬) 
 */
SELECT EMPNO, ENAME, JOB, SAL
FROM EMP
WHERE JOB != 'SALESMAN'
AND SAL > ANY (
		SELECT SAL			
		FROM EMP		
		WHERE JOB = 'SALESMAN'
	)
ORDER BY SAL;