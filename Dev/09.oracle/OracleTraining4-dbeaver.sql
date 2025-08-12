-- 6-1. 대소문자 변환 함수
SELECT ENAME,
		UPPER(ENAME),	-- 대문자로
		LOWER(ENAME),	-- 소문자로
		INITCAP(ENAME)	-- 첫글자만 대문자 
FROM EMP;

-- 6-2. 대소문자 구분없이 검색하기 
-- 사용자 'scott', 'SCOTT', 'Scott' 어떻게 입력해도 찾을수 있음
SELECT *
FROM EMP
WHERE upper(ename) = upper('scott');

-- 6-3.  대소문자 구분없이 부분 검색
-- 이름에 'scott'이 포함된 모든 직원
SELECT *
FROM emp
WHERE upper(ename) LIKE UPPER('%scott%');

-- 6-4. LENGTH() - 문자열 길이 구하기 
SELECT ENAME,
	   LENGTH(ENAME) 
FROM EMP;

-- 6-5. 이름이 5글자 이상인 직원 조회
SELECT ENAME, LENGTH(ENAME)
FROM EMP
WHERE LENGTH(ENAME) >= 5;  -- 글자수가 5 이상인 경우만

-- 이름의 세번째 글자가 'A'인 직원의
-- 이름과 이름의 길이를 조회하시오. 이름으로 정렬 출력하시오.
SELECT ENAME,
		LENGTH(ENAME) AS NAME_LENGTH
FROM EMP 
WHERE ENAME LIKE '__A%'
ORDER BY ENAME;

-- 6-6. 한글과 영문의 바이트 길이 차이 (dual : 더미 테이블)
SELECT length('한글'),	-- 문자 개수
		LENGTHB('한글')	-- 바이트 수 (UTF-8 기준)
FROM dual;

-- 6-7. substr() - 문자열 자르기 (부분 추출)
-- substr(문자열, 시작위치, 길이)
-- 시작위치는 1부터 시작 (주의: 0이 아님!)
SELECT job,
	SUBSTR(job, 1, 2),
	substr(job, 3, 2),
	SUBSTR(job, 4)		-- 4번째부터 끝까지
FROM emp;

-- 6-8. substr() 음수 인덱스 사용
-- 음수를 사용하면 뒤에서부터 계산
SELECT JOB,
	SUBSTR(job, -LENGTH(JOB)),	-- 맨 뒤에서 전체길이만큼 => 전체
	SUBSTR(job, -LENGTH(JOB), 2), -- 맨 뒤에서 전체길이 위치부터 2글자
	substr(job, -3)		-- 뒤에서 3번째부터 끝까지 
FROM emp;

-- 사원명의 첫글자와 마지막 글자 추출하여 약어를 만드시오.
SELECT ENAME AS "사원명",
	LENGTH(ename) AS "이름길이",
	SUBSTR(ENAME, 1,1) AS "첫글자",
	SUBSTR(ENAME, -1,1) AS "마지막글자",
	SUBSTR(ENAME, 1,1) || SUBSTR(ENAME, -1,1) AS "약어"
FROM emp;

-- 6-9: INSTR() - 문자 위치 찾기 
-- 특정 문자가 처음 나타나는 위치를 반환 (없으면 0)
-- INSTR(문자열, 찾을문자) : 기본 형태 (첫번째 위치 반환)
-- INSTR(문자열, 찾을문자, 시작위치) : 특정위치부터 검색 
-- INSTR(문자열, 찾을문자, 시작위치, N번째) : N번째 출현 위치 
-- INSTR(문자열, 찾을문자, -1) : 뒤에서부터 검색 
SELECT INSTR('HELLO, ORACLE!', 'L') AS INSTR_1, -- 첫번째 L의 위치
		INSTR('HELLO, ORACLE!', 'L', 5) AS INSTR_2, -- 5번째 이후 첫 L의 위치
		INSTR('HELLO, ORACLE!', 'L', 2, 2) AS INSTR_3 -- 2번째부터 시작해서 2번째 L의 위치
FROM DUAL;

-- 6-10: INSTR을 WHERE절에서 사용
-- 이름에 'S'가 포함된 직원 찾으시오. (위치가 0보다 크면 존재함)
SELECT *
FROM EMP
WHERE INSTR(ENAME, 'S') > 0;

-- 6-11 : like 
-- 위와 동일한 결과
SELECT *
FROM EMP
WHERE ENAME LIKE '%S%';

-- 
SELECT LOWER(ENAME) || '@gmail.com' AS "이메일", -- 이메일 생성
	INSTR(LOWER(ENAME)|| '@gmail.com', '@') AS "@위치", -- @기호의 위치 찾기 
	SUBSTR(LOWER(ENAME)|| '@gmail.com',1,
			INSTR(LOWER(ENAME)|| '@gmail.com', '@') - 1) AS "아이디", -- @앞부분 추출
	SUBSTR(LOWER(ENAME)|| '@gmail.com',
				INSTR(LOWER(ENAME)|| '@gmail.com', '@') + 1) AS "도메인" -- @뒷부분 추출			
FROM emp
WHERE ROWNUM <= 5; 

-- 6-12: replace() - 문자 치환
-- replace(원본, 찾을문자, 바꿀문자)
-- 세번째 매개변수 생략시 해당 문자 제거 
SELECT '010-1234-5678' AS REPLACE_BEFORE,
		REPLACE('010-1234-5678', '-', ' '),  -- -를 공백으로
		REPLACE('010-1234-5678', '-') -- -를 제거
FROM dual;

SELECT '010-1234-5678' AS "하이픈형식",
		REPLACE('010-1234-5678', '-', '') AS "하이픈제거",
		REPLACE('010.1234.5678', '.', '') AS "점제거",
		REPLACE('010 1234 5678', ' ', '') AS "공백제거"
FROM DUAL;

-- 실행 결과를 단계별로 진행
SELECT
	ENAME AS "원본",
	REPLACE(ENAME, 'S', '*') AS "1단계_S->*",
	REPLACE(REPLACE(ENAME, 'S', '*'),'A','#') AS "2단계_A->#",
	REPLACE(REPLACE(REPLACE(ENAME, 'S', '*'),'A','#'),'E', '@') AS "3단계_E->@_최종"
FROM EMP 
WHERE ENAME IN ('SMITH','ALLEN','JAMES','JONES')
;

-- REPLACE() 중첩 사용 
SELECT
	ENAME AS "원본",
	REPLACE(REPLACE(REPLACE(ENAME, 'S', '*'),'A','#'),'E', '@') AS "암호화된_이름"
FROM EMP 
WHERE ENAME IN ('SMITH','ALLEN','JAMES','JONES')
;

/*
 * WITH 절은 복잡한 쿼리를 간단하게 만들어주는 "임시 테이블"임.
 * 	- 쿼리 실행 중에만 존재하는 가상의 테이블
 * 	- 복잡한 쿼리를 단계별로 나누어 작성 가능 
 * 	- 가독성이 좋아지고 유지보수가 쉬어짐
 * 	- 서브쿼리를 여러 번 사용할 때 효율적임 
 * 
 * 	- 기본 구조 
 * 		WITH 임시테이블명 AS (
 * 			SELECT 문
 * 		)
 * 
 * 		SELECT * FROM 임시테이블명;
 */
-- 가장 간단한 WITH절 
WITH simple_demo AS (
	SELECT 'Hello' AS greeting, 'World' AS target FROM dual
)
SELECT * FROM simple_demo;

-- emp 테이블로 기본 with절 사용
WITH high_salary AS (
	SELECT * FROM emp WHERE sal >= 2000
)
SELECT * FROM high_salary;

-- REPLACE() 중첩을 WITH절로 개선하기 
-- 원본 (복잡한 중첩)
SELECT
	ENAME,
	REPLACE(REPLACE(REPLACE(ENAME, 'S', '*'),'A','#'),'E', '@') AS result
FROM EMP; 

-- WHIT절로 개선
WITH 
	step1 AS (
		SELECT ENAME, REPLACE(ENAME, 'S', '*') AS name_modified
		FROM emp 
	),
	step2 AS (
		SELECT ENAME, REPLACE(name_modified,'A','#') AS name_modified
		FROM step1 
	),	
	step3 AS (
		SELECT ENAME, REPLACE(name_modified,'E', '@') AS final_name
		FROM step2 
	)
SELECT 
	ENAME AS "원본",
	final_name as "암호화"
FROM step3; 	


-- 중첩된 replace()를 변수처럼 이해하기 
WITH step_by_step AS (
	SELECT
		ENAME AS "원본",
		REPLACE(ENAME, 'S', '*') AS "1단계_S->*",
		REPLACE(REPLACE(ENAME, 'S', '*'),'A','#') AS "2단계_A->#",
		REPLACE(REPLACE(REPLACE(ENAME, 'S', '*'),'A','#'),'E', '@') AS "3단계_E->@_최종"
	FROM EMP 
)
SELECT * FROM step_by_step;

-- 6-15 : concat() - 문자열 연결 
-- CONCAT(문자열1, 문자열2) - 2개만 연결 가능 
-- || 연산자와 동일 
-- concat()은 2개만 연결, ||는 여러개 연결 가능 
SELECT CONCAT(EMPNO, ENAME),	-- 사번과 이름 연결
		CONCAT(EMPNO, CONCAT(' : ', ENAME)) -- 중첩 사용으로 3개 연결
FROM emp 
WHERE ENAME = 'SCOTT';

-- CONCAT()
SELECT CONCAT(CONCAT(CONCAT('[', DEPTNO),'] '), ENAME) AS "CONCAT 방식"
FROM EMP 
WHERE ROWNUM <= 3;

-- || 연산자 사용 (더 간단함)
SELECT '[' || DEPTNO || '] ' || ENAME AS "파이프방식"
FROM EMP 
WHERE ROWNUM <= 3;

-- 6-13: LPAD()/ RPAD() - 문자열 채우기 
-- LPAD() : 왼쪽에 채움
-- RPAD() : 오른쪽에 채움 
-- 주로 고정 길이 포맷 맞추거나 마스킹 처리에 사용 
SELECT 'Oracle',
	LPAD('Oracle', 10, '#') AS LPAD_1, -- 10자리, 왼쪽을 #으로
	RPAD('Oracle', 10, '*') AS RPAD_1,  -- 10자리, 오른쪽을 *로 
	LPAD('Oracle', 10) AS LPAD_2, -- 채울 문자 생략시 공백
	RPAD('Oracle', 10) AS RPAD_2  -- 채울 문자 생략시 공백
FROM DUAL;

-- 6-14: 개인정보 마스킹 처리
SELECT RPAD('971225-', 14, '*') AS RPAD_JMNO,
	   RPAD('010-1234-', 13, '*') AS RPAD_PHONE
FROM dual;

-- 카드번호 
SELECT '1234-5678-9012-3456' AS "원본카드번호",
	SUBSTR('1234-5678-9012-3456', 1, 4) || '-' ||
	RPAD('*', 4, '*')|| '-' ||
	RPAD('*', 4, '*')|| '-' ||
	SUBSTR('1234-5678-9012-3456', -4) AS "마스킹결과"
FROM dual;

-- 사원정보를 정제하여 출력하시오
SELECT RPAD(LPAD(rownum, 3, '0'),5) AS "NO",
		RPAD(UPPER(SUBSTR(ENAME, 1,1)) || LOWER(SUBSTR(ENAME,2)), 10) AS "이름",
		RPAD(REPLACE(JOB,'MAN', 'MGR'), 12) AS "직무"
FROM emp 
;

/*
 * 6-16
 * 
 * - TRIM([옵션] [제거할문자] FROM 원본문자열)
 * 
 * 		- 옵션 3가지
 * 			- LEADING	: 앞쪽(왼쪽)에서만 제거
 * 			- TRAILING	: 뒤쪽(오른쪽)에서만 제거
 * 			- BOTH		: 양쪽에서 제거 (기본값)
 */
-- 기본 TRIM(옵션없음)
SELECT 
	TRIM('  Hello  ') AS result1,
	TRIM('***Hello***') AS result2  -- 공백이 없어서 변화 없음
FROM DUAL;

-- 옵션만 있는 TRIM()
SELECT 
	-- LEADING FROM: 앞쪽 공백만 제거 
	TRIM(LEADING FROM '  Hello  ') AS leading_result,
	-- TRAILING FROM: 뒤쪽 공백만 제거 
	TRIM(TRAILING FROM '  Hello  ') AS trailing_result,
	-- BOTH FROM: 양쪽 공백 제거 
	TRIM(BOTH FROM '  Hello  ') AS both_result
FROM DUAL;

-- 
SELECT
	'원본: [' || '  Hello  ' || ']' AS "원본_문자열",
	'TRIM: [' || TRIM('  Hello  ') || ']' AS "양쪽_제거",
	'LEADING: [' || TRIM(LEADING FROM '  Hello  ') || ']' AS "앞만_제거",
	'TRAILING: [' || TRIM(TRAILING FROM '  Hello  ') || ']' AS "뒤만_제거"
FROM dual;

-- 특정 문자 제거 (공백이 아닌 다른 문자)
SELECT 
	-- 기본 TRIM()은 공백만 제거 
	TRIM('***HELLO***') AS "기본_TRIM",		-- (변화없음)
	-- 특정 문자를 지정하여 제거 
	TRIM('*' FROM '***HELLO***') AS "별표_제거",
	TRIM(LEADING '*' FROM '***HELLO***') AS "앞_별표만",
	TRIM(TRAILING '*' FROM '***HELLO***') AS "뒤_별표만",
	TRIM(BOTH '*' FROM '***HELLO***') AS "양쪽_별표"
FROM DUAL;

-- 다양한 TRIM 활용
SELECT  
	'   HELLO   ' AS "원본",
	TRIM('   HELLO   ') AS "양쪽_공백제거",
	LTRIM('   HELLO   ') AS "왼쪽만_제거",
	RTRIM('   HELLO   ') AS "오른쪽만_제거",
	TRIM('*' FROM '***HELLO***') AS "별표_제거"
FROM DUAL;





