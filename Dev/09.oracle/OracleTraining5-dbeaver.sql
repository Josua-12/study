-- 1. 사원 이름을 다양한 형태로 출력하시오.
SELECT
	ENAME AS "원본이름",
	UPPER(ENAME) AS "대문자로_출력하기",
	LOWER(ENAME) AS "소문자로_출력하기",
	INITCAP(ENAME) AS "공식문서용_출력하기"
FROM EMP
WHERE DEPTNO = 20;

-- 2. 사용자가 'scott', 'SCOTT', 'Scott'
-- 대소문자 구분 없이 검색하시오
SELECT *
FROM EMP
WHERE UPPER(ENAME) = UPPER('scott');
-- WHERE LOWER(ENAME) = LOWER('scott');

-- 3. 이름에 'A'가 들어가는 사원을 출력하시오. (대소문자 무관)
SELECT *
FROM EMP
WHERE UPPER(ENAME) LIKE '%A%';

SELECT ENAME,
		LENGTH(ENAME) AS NAME_LENGTH
FROM EMP
WHERE ENAME LIKE '__A%'
ORDER BY ENAME;

