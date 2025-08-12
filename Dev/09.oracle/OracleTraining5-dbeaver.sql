-- 1.  사원 이름을 다양한 형태로 출력하시오.
SELECT 
	ENAME AS "원본이름",
	UPPER(ENAME) AS "대문자로_출력하기",
	LOWER(ENAME) AS "소문자로_출력하기",
	INITCAP(ENAME) AS "공식 문서용_출력하기"
FROM EMP
WHERE DEPTNO = 20;

-- 2. 사용자가 'scott', 'SCOTT', 'Scott' 
-- 대소문자 구분 없이 검색하시오.
SELECT *
FROM emp
WHERE upper(ENAME) = UPPER('scott');
--WHERE LOWER(ENAME) = LOWER('scott');

--3. 이름에 'A'가 들어가는 사원을 출력하시오. (대소문자 무관)
SELECT ENAME, job 
FROM emp
WHERE UPPER(ENAME) LIKE '%A%';






