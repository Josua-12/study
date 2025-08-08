-- 6-1. 대소문자 변환 함수
SELECT ENAME,
		UPPER(ENAME),	-- 대문자로
		LOWER(ENAME),	-- 소문자로 
		INITCAP(ENAME)
FROM EMP;

-- 6-2. 대소문자 구분없이 검색하기
-- 사용자가 'scott', 'SCOTT', 'Scott' 등 어떻게 입력해도 찾을 수 있음
SELECT *
FROM EMP
WHERE UPPER(ENAME) = UPPER('scott');

-- 6-3. 대소문자 구분없이 부분 검색
-- 이름에 'scott'이 포함된 모든 직원
SELECT *
FROM EMP
WHERE UPPER(ename) LIKE UPPER('%scott%');

-- 6-4. LENGTH() - 문자열 길이 구하기
SELECT ENAME,
		LENGTH(ENAME)
FROM EMP;

-- 6-5. 이름이 5글자 이상인 직원 조회
SELECT ENAME, LENGTH(ENAME)
FROM EMP
WHERE LENGTH(ENAME) > 5;	-- 글자 수가 5 이상인 경우만 

-- 이름의 세번째 글자가 'A'인 직원의
-- 이름과 이름의 길이를 조회하시오.
SELECT ENAME, LENGTH(ENAME)
FROM EMP
WHERE ENAME LIKE '__A%'
ORDER BY ENAME;