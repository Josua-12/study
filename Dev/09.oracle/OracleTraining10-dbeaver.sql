-- 8-1 - "모든 사원의 정보와 그 사원이 속한 부서의 정보를 함께 조회하시오"
/*
 * EMP 테이블과 DEPT 테이블의 카티전 곱을 구하시오.
 * 	- 실무에서는 절대 사용하지 않는 방식
 * 	- 출력된 총 행의 개수 : 14 * 4 = 56개
 *	- 예) 사원번호 7369(SMITH)는 몇 번 나타나는가? 4번
 *	- 조인은 본래 카티전 곱에서 시작함
 *	- 조인 조건의 역할 : 조인 조건이 카티전 곱을 필터링
 */
SELECT *
FROM EMP, DEPT
ORDER BY EMPNO;

-- 8-2 WHERE절에 JOIN 조건 명시(등가 조인 = Equi Join)
/*
 * - EMP 테이블과 DEPT 테이블을 조인
 * - JOIN 조건 : 사원의 부서번호(EMP.DEPTNO)와 부서의 부서번호(DEPT.DEPTNO)가 같은 경우
 * - WHERE절을 사용하여 조인 조건 명시 (Oracle 전통 방식)
 * - 출력 행 수 : 14개
 * - 각 사원 당 1개의 행만 출력됨
 */
SELECT *
FROM EMP, DEPT
WHERE EMP.DEPTNO = DEPT.DEPTNO	-- JOIN 조건 : 부서 번호가 같은 경우
ORDER BY EMPNO;

-- 8-3 테이블 별칭(Alias) 사용한 등가 조인
/*
 * 코드의 간결성과 가독성 향상 방법으로 이해
 */
SELECT *
FROM EMP E, DEPT D
WHERE E.DEPTNO = D.DEPTNO 	-- 별칭을 사용한 JOIN 조건
ORDER BY EMPNO;

-- 8-4, 8-5 열이름 충돌 오류 발생
-- 사원의 기본 정보(사번, 이름)와 부서 정보(부서 번호, 부서명, 위치)를 조회하시오.
SELECT E.EMPNO, E.ENAME, E.DEPTNO, D.DNAME, D.LOC
FROM EMP E, DEPT D
WHERE E.DEPTNO = D.DEPTNO;

-- 8-6 JOIN 조건과 추가 조건 함께 사용
/*
 * - 급여가 3000 이상인 고액 연봉자들의 사원 정보와 부서 정보를 조회하시오
 */
SELECT E.EMPNO, E.ENAME, E.SAL, D.DEPTNO, D.DNAME, D.LOC
FROM EMP E, DEPT D
WHERE E.DEPTNO = D.DEPTNO	-- JOIN 조건
AND SAL >= 3000;			-- 추가 조건

-- 8-7 비등가 조인 (Non-Equi JOIN)
/*
 * "모든 사원의 급여 등급을 조회하시오. 각 사원이 몇 등급에 해당하는지 확인하시오."
 * 		- EMP 테이블과 SALGRADE 테이블을 조인
 * 		- JOIN 조건 : 사원의 급여(SAL)가 급여 등급의 범위(LOSAL~HISAL)내에 있는 경우
 * 			- BETWEEN 연산자를 사용하여 범위 조건 작성
 * 			- 등호(=)가 아닌 범위 비교로 JOIN (비등가 조인)
 */
SELECT *
FROM EMP E, SALGRADE S
WHERE E.SAL BETWEEN S.LOSAL AND S.HISAL;

-- 8-8 자체 조인 (Self JOIN) - 같은 테이블을 두 번 사용
/*
 * - "모든 사원의 매니저 정보를 조회하시오. 각 사원과 그 사원의 매니저 이름을 한 줄에 표시하시오."
 * - EMP 테이블을 두 번 사용하여 자체 조인 수행
 * 		- 첫번째 EMP 테이블(E1) : 사원 정보용
 * 		- 두번째 EMP 테이블(E2) : 매니저 정보
 */ 
SELECT E1.EMPNO, E1.ENAME, E1.MGR,
		E2.EMPNO AS MGR_EMPNO,		-- 매니저의 사원 번호
		E2.ENAME AS MGR_ENAME		-- 매니저의 이름
FROM EMP E1, EMP E2
WHERE E1.MGR = E2.EMPNO;			-- 사원의 MGR 값 = 매니저의 EMPNO

-- 8-9 외부 조인 (Outer Join) - 왼쪽 외부 조인(Oracle Syntax)
/*
 * "모든 사원의 매니저 정보를 조회하시오.
 * 매니저가 없는 사원(KING)도 포함하여 전체 14명의 사원이 모두 출력되도록 하시오."
 * 
 * - 8-8의 자체 조인을 외부 조인으로 변경
 * - Oracle 전용 구문인 (+) 연산자 사용
 */
SELECT E1.EMPNO, E1.ENAME, E1.MGR,
		E2.EMPNO AS MGR_EMPNO,
		E2.ENAME AS MGR_ENAME
FROM EMP E1, EMP E2
WHERE E1.MGR = E2.EMPNO(+)		-- E1의 모든 행을 포함(LEFT OUTER JOIN)
ORDER BY E1.EMPNO;

-- 8-10 외부 조인 - 오른쪽 외부 조인(Oracle Syntax)
/*
 * "부하 직원이 없는 사원을 포함하여 모든 사원을 매니저 관점에서 조회하시오.
 * 각 사원이 관리하는 부하 직원이 있는지 확인하고 부하 직원이 없는 일반 사원도 결과에 포함시키시오."
 * 
 * - E1 : 부하 직원 테이블 (MGR 컬럼 사용)
 * - E2 : 매니저 테이블 (EMPNO 컬럼 사용)
 * 
 * - E1.MGR(+) = E2.EMPNO
 * 		- E2(오른쪽)의 모든 데이터 보존
 * 		- (+)위치 : 왼쪽 테이블에 붙임
 */
SELECT E1.EMPNO, E1.ENAME, E1.MGR,
		E2.EMPNO AS MGR_EMPNO,
		E2.ENAME AS MGR_ENAME
FROM EMP E1, EMP E2
WHERE E1.MGR(+) = E2.EMPNO 
ORDER BY E1.EMPNO, MGR_EMPNO;

-- 8-13 JOIN ~ ON - 가장 유연한 조인 조건 지정 방법
/*
 * - ANSI SQL 표준인 JOIN ON 구문 사용 (내부 조인)
 * - ON 절에 JOIN 조건을 명시적이고 자유롭게 작성
 * 
 * "JOIN ON 구문을 사용하여 급여 3000 이하인 사원들의 정보와 부서 정보를 조회하시오.
 * JOIN 조건을 가장 명확하고 유연한 방식으로 작성하시오."
 */
SELECT E.EMPNO, E.JOB, E.MGR, E.HIREDATE, E.SAL, E.COMM
FROM EMP E JOIN DEPT D ON E.DEPTNO = D.DEPTNO	-- 명확한 조인 조건
WHERE E.SAL <= 3000		-- 조인 후 필터 조건
ORDER BY E.DEPTNO, EMPNO;

-- 8-12 JOIN ~ USING - 조인할 컬럼을 명시적으로 저장
/*
 * - ANSI SQL 표준인 JOIN USING 구문 사용 (내부 조인)
 * - USING 절에 조인할 컬럼을 명시적으로 지정
 * 
 * "JOIN USING 구문을 사용하여 급여 3000 이상인 고액 연봉자들의
 * 사원 정보와 부서 정보를 조회하시오. 조인할 컬럼을 명시적으로 지정하시오."
 */
SELECT E.EMPNO, E.ENAME, E.MGR, E.HIREDATE, E.SAL, E.COMM  
FROM EMP E JOIN DEPT D USING (DEPTNO)		-- JOIN 컬럼을 명시적으로 지정함
WHERE SAL >= 3000		-- 조인 후 필터 조건 적용
ORDER BY DEPTNO, E.EMPNO;

-- 8-14 LEFT OUTER JOIN  - ANSI 표준 구문
/*
 *  " ANSI 표준 LEFT OUTER JOIN을 사용하여 모든 사원의 매니저 정보를 조회하시오.
 * 	매니저가 없는 최고 경영자(KING)도 반드시 결과에 포함되어야 합니다."
 * 
 * - LEFR OUTER JOIN = LEFT JOIN (OUTER는 생략 가능)
 * - "왼쪽 테이블(E1)을 기준으로 하여 오른쪽 테이블(E2)과 매칭되는 데이터를 가져오되,
 *    매칭이 안 되더라도 왼쪽 데이터는 모두 보이게 하시오."
 */
SELECT E1.EMPNO, E1.ENAME, E1.MGR,
		E2.EMPNO AS MGR_EMPNO,
		E2.ENAME AS MGR_ENAME
FROM EMP E1 LEFT OUTER JOIN EMP E2 ON (E1.MGR = E2.EMPNO)	-- ANSI 표준 왼쪽 외부 조인
ORDER BY E1.EMPNO;

-- 8-15 RIGHT OUTER JOIN - ANSI 표준 구문
/*
 * "ANSI 표준 RIGHT OUTER JOIN을 사용하여 모든 사원을 매니저 관점에서 조회하시오.
 * 	부하 직원이 없는 일반 사원들도 매니저 후보로서 결과에 포함되어야 합니다."
 * 
 * 
 */
SELECT E1.EMPNO, E1.ENAME, E1.MGR,
		E2.EMPNO AS MGR_EMPNO,
		E2.ENAME AS MGR_ENAME
FROM EMP E1 RIGHT JOIN EMP E2 ON (E1.MGR = E2.EMPNO)		-- ANSI 표준 오른쪽 외부 조인
ORDER BY E1.EMPNO, MGR_EMPNO;













