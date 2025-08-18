-- 7장 다중행 함수와 데이터 그룹화
-- 7-2 SUM() 함수
-- 특정 열의 모든 값을 다 더하는 함수
SELECT ENAME, SUM(SAL)
FROM EMP;			-- 그룹 함수는 일반 컬럼과 함께 사용 불가(=> GROUP BY)

-- 7-3 NULL 값 처리 
SELECT SUM(COMM)	-- SUM() 함수는 NULL값을 자동으로 제외하고 계산함
FROM EMP;

-- 7-4 DISTINCT와 ALL 옵션
SELECT SUM(SAL),
		SUM(ALL SAL),		-- ALL : 모든 값 포함하여 합계 (기본)
		SUM(DISTINCT SAL)	-- DISTINCT : 중복 값 제거 후 합계 (동일 급여는 한번만 계산)
FROM EMP;

-- 7-5 COUNT() - 테이블의 전체 행 개수 세기
SELECT COUNT(*)
FROM EMP;

-- 7-6 조건부 COUNT() - 조건을 만족하는 행의 개수
SELECT COUNT(*)
FROM EMP
WHERE DEPTNO = 30;

-- 7-7 COUNT의 DISTINCT 옵션
SELECT COUNT(SAL),		-- NULL 제외한 모든 급여 개수
		COUNT(ALL SAL),
		COUNT(DISTINCT SAL) -- 중복 제외한 급여 종류의 개수
FROM EMP;

-- 7-8 COUNT()와 NULL 처리
SELECT COUNT(COMM)
FROM EMP;

-- 7-9 NULL 명시적 제외
SELECT COUNT(COMM)
FROM EMP
WHERE COMM IS NOT NULL;

-- 7-10 MAX() 함수
SELECT MAX(SAL)
FROM EMP
WHERE DEPTNO = 10;

-- 7-11 MIN() 함수
SELECT MIN(SAL)
FROM EMP
WHERE DEPTNO = 10;

-- 7-12 날짜 데이터의 MAX() 함수 : 가장 최근 일자 출력
SELECT MAX(HIREDATE)
FROM EMP
WHERE DEPTNO = 20;

-- 7-13 날짜 데이터의 MIN() 함수 : 가장 오래된 일자 출력
SELECT MIN(HIREDATE)
FROM EMP
WHERE DEPTNO = 20;ALTER 

-- 7-14 AVG()
SELECT AVG(SAL)
FROM EMP
WHERE DEPTNO = 30;

-- 7-15 DISTINCT와 AVG()
SELECT AVG(DISTINCT SAL)	-- 중복 급여 제거 후 평균 계산
FROM EMP
WHERE DEPTNO = 30;

/*
 * 시나리오 - 당신은 IT 컨설팅 회사의 데이터 분석가입니다.
 * 		   CEO가 긴급 경영회의를 위해 인사이트를 요청했습니다.
 * 		   숨겨진 패턴을 찾아보세요.
 */
-- 1. 우리 회사의 인건비 구조가 건전한가요?
-- 1-1. 인건비 현황 대시보드
SELECT
	COUNT(*) AS "총 직원 수",
	COUNT(DISTINCT JOB) AS "직책 종류",
	COUNT(DISTINCT DEPTNO) AS "운영부서 수",
	SUM(SAL) AS "월 급여 총액",		-- 월 총 인건비
	SUM(SAL * 12) AS "연간 인건비",
	ROUND(AVG(SAL), 2) AS "평균 급여",
	MAX(SAL) AS "최고 급여",
	MIN(SAL) AS "최저 급여",
	MAX(SAL) - MIN(SAL) AS "급여 격차",
	ROUND(STDDEV(SAL), 2) AS "급여 편차"
FROM EMP

-- 2. 우리 회사의 부서별 비용 구조를 분석하시오.
-- 2-1. 부서별 인건비 집중도
SELECT DEPTNO AS "부서 번호",
		COUNT(*) AS "인원",
		SUM(SAL) AS "부서 총 인건비",
		ROUND(AVG(SAL), 2) AS "부서 평균 급여",
		MAX(SAL) AS "부서 내 최고 급여",
		MIN(SAL) AS "부서 내 최저 급여",
		MAX(SAL) - MIN(SAL) AS "부서 내 격차"
FROM EMP
GROUP BY DEPTNO
ORDER BY "부서 총 인건비" DESC;

-- 7-17 GROUP BY 절 - 데이터의 그룹화
-- 부서별 평균 급여
SELECT DEPTNO, AVG(SAL)
FROM EMP
GROUP BY DEPTNO;		-- GROUP BY : 특정 컬럼 기준으로 그룹 생성

-- 7-18 다중 컬럼 GROUP BY
-- 부서별, 직무별 평균 급여
SELECT DEPTNO, JOB, AVG(SAL)
FROM EMP
GROUP BY DEPTNO, JOB	-- 부서와 직무 조합으로 그룹화
ORDER BY DEPTNO, JOB;

-- 7-19 GROUP BY 오류
SELECT ENAME, DEPTNO, JOB, AVG(SAL)		-- ENAME이 GROUP BY에 없음 => 오류
FROM EMP
GROUP BY DEPTNO, JOB 	-- SELECT의 모든 일반 컬럼은 GROUP BY에 포함되어야 함
ORDER BY DEPTNO, JOB;

-- 7-20 HAVING 절 - 그룹 조건
SELECT DEPTNO, JOB, AVG(SAL)
FROM EMP
GROUP BY DEPTNO, JOB
HAVING AVG(SAL) >= 2000		-- 평균 급여 2000이상인 그룹만 표시
ORDER BY DEPTNO, JOB;

-- 7-21 WHERE에 집계 함수 사용 시 오류 발생
SELECT DEPTNO, JOB, AVG(SAL)
FROM EMP
WHERE AVG(SAL) >= 2000		-- WHERE절에서는 집계 함수 사용 불가 => 오류 발생
GROUP BY DEPTNO, JOB
ORDER BY DEPTNO, JOB;

-- 7-23 WHERE과 HAVING 함께 사용 가능
SELECT DEPTNO, JOB, AVG(SAL)
FROM EMP
WHERE SAL <= 3000			-- WHERE : 개별 행 필터링 (그룹화 전)
GROUP BY DEPTNO, JOB		-- 급여 3000 이하인 직원들만 그룹화
HAVING AVG(SAL) >= 2000		-- HAVING : 그룹 필터링 (그룹화 후)
ORDER BY DEPTNO, JOB;		-- 그룹 평균이 2000 이상인 것만 표시하기



















