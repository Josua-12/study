-- Join lab

/*
 *	- Oracal 전통 방식 : where 절에 조인조건과 필터조건 함께
 *	- ANSI 표준 방식 : ON절에 조인조건, WHERE절에 필터조건 분리
 *	- 외부조인 표기 : Oracle (+) vs LEFT/RIGHT JOIN
 *	- 자체 조인
 *	- 비등가 조인
 */

-- 1. DALLAS에 근무하는 모든 사원의 사번, 이름, 직무, 부서명을 조회하시오

-- ORACLE 전통방식(방언)
SELECT e.EMPNO, d.DNAME, E.JOB, D.DEPTNO
FROM EMP e, DEPT d			-- EMP와 DEPT 테이블을 조인 (별칭 사용)
WHERE E.DEPTNO = D.DEPTNO 	-- 조인조건
AND LOC = 'DALLAS'			-- 필터조건
ORDER BY E.DEPTNO;

-- ANSI 표준 방식
SELECT E.EMPNO , E.ENAME , E.JOB , D.DEPTNO 
FROM EMP E
JOIN DEPT D ON E.DEPTNO = D.DEPTNO 
WHERE D.LOC = 'DALLAS'
ORDER BY E.DEPTNO ;

-- 2. 급여 등급별 사원 현황
/*
 *	급여등급이 3등급인 사원들의 이름, 급여, 부서명을 조회하시오.
 *	힌트 : 3개 테이블 조인 필요
 */
-- ORACLE 전통방식(방언)
SELECT E.ENAME , E.SAL , D.DNAME  
FROM EMP E, DEPT D, SALGRADE s
WHERE  E.DEPTNO = D.DEPTNO
AND    E.SAL BETWEEN S.LOSAL AND S.HISAL
AND    S.GRADE = 3
ORDER BY E.SAL;

-- ANSI 표준 방식
SELECT E.ENAME , E.SAL , D.DNAME  
FROM EMP E
JOIN DEPT D ON E.DEPTNO = D.DEPTNO 
JOIN SALGRADE s ON E.SAL BETWEEN S.LOSAL AND S.HISAL 
WHERE S.GRADE = 3;