-- NULL 처리 함수
-- 6-45 NVL - NULL값 처리
-- NULL과의 연산 결과는 항상 NULL이므로 NVL로 처리 필요함
-- NVL(컬럼, 대처값) : NULL이면 대체값 반환
SELECT EMPNO, ENAME, SAL, COMM,
		SAL+COMM,			-- COMM이 NULL이면 연산 결과가 무조건 NULL이 나오게 됨
		NVL(COMM, 0),		-- COMM이 NULL이면 0으로 대체
		SAL + NVL(COMM, 0)	-- NULL을 0으로 대체한 후 계산
FROM EMP;

-- 6-46 NVL2 : NULL 여부에 따른 분기 처리
-- NVL2(컬럼, NULL아닐 때 값, NULL일 때 값)
SELECT EMPNO, ENAME, SAL, COMM,
	NVL2(COMM, 'O', 'X'),	-- 커미션 있으면 O, 없으면 X
	NVL2(COMM, SAL*12+COMM, SAL*12) AS ANNSAL
FROM EMP;

-- 커미션 처리
SELECT ENAME,
		SAL,
		COMM,
		NVL(COMM, 0) AS "커미션_없으면_0",
		SAL + NVL(COMM, 0) AS "총_수입",
		NVL(TO_CHAR(COMM), '커미션 없음') AS "커미션_문자처리"
FROM EMP
WHERE DEPTNO = 30;

-- 조건 처리 함수
-- 6-47 DECODE - 조건별 값 반환 (Oracle 전용)
-- DECODE(컬럼, 조건1, 결과1, 조건2, 결과2, ...., 기본값)
-- SWITCH-CASE문과 유사
SELECT
	EMPNO, ENAME, JOB, SAL,
	DECODE(JOB,
			'MANAGER', SAL*1.1,		-- MANAGER면 10% 인상
			'SALESMAN', SAL*1.05,	-- SALESMAN이면 5% 인상
			'ANALYST', SAL,			-- ANALYST면 동결
			SAL*1.03) AS UPSAL		-- 나머지는 3% 인상
FROM EMP;

-- 6-48 CASE - 조건별 값 반환 (표준 SQL)
-- DECODE보다 복잡한 조건 처리 가능
-- 모든 DBMS에서 사용 가능
SELECT
	EMPNO, ENAME, JOB, SAL,
	CASE JOB
		WHEN 'MANAGER' THEN SAL * 1.1
		WHEN 'SALESMAN' THEN SAL * 1.05
		WHEN 'ANALYST' THEN SAL
		ELSE SAL * 1.03
	END AS UPSAL
FROM EMP;

-- 6-49 CASE - 범위 조건 처리
SELECT EMPNO, ENAME, COMM,
	CASE
		WHEN COMM IS NULL THEN '해당사항 없음'
		WHEN COMM = 0 THEN '수당 없음'
		WHEN COMM > 0 THEN '수당 : ' || COMM
	END AS COMM_TEXT	
FROM EMP;

-- 부서별 지역 표시
SELECT ENAME,
		DEPTNO,
		DECODE(DEPTNO,
				10, '뉴욕 본사 🗽',
				20, '달라스 연구소 🔬',
				30, '시카고 영업소 💼',
				40, '보스턴 운영팀 ⚙️',
				'기타 부서') AS "근무지"		
FROM EMP;

--1. 최고경영자
--직책(JOB)이 'PRESIDENT'인 경우
--
--2. 고급 관리자
--직책(JOB)이 'MANAGER'이면서
--급여(SAL)가 2,500 이상인 경우
--
--3. 일반 관리자
--직책(JOB)이 'MANAGER'이면서
--급여(SAL)가 2,500 미만인 경우
--
--4. 우수 영업사원
--직책(JOB)이 'SALESMAN'이면서
--커미션(COMM)이 500 초과인 경우
--
--5. 일반 영업사원
--직책(JOB)이 'SALESMAN'인 경우
--(커미션과 관계없이 위 4번 조건에 해당하지 않는 영업사원)
--
--6. 연구원
--부서번호(DEPTNO)가 20인 경우
--(직책과 관계없이 20번 부서 소속인 경우)
--
--7. 일반 직원
--위의 모든 조건에 해당하지 않는 나머지 직원

SELECT
	ENAME, JOB, LPAD(TO_CHAR(SAL, '9990.00'), 20, ' ') AS SAL, DEPTNO, 
		CASE 
			WHEN JOB = 'PRESIDENT' THEN '최고 경영자'
			WHEN JOB = 'MANAGER' AND SAL >= 2500 THEN '고급 관리자'
			WHEN JOB = 'MANAGER' AND SAL < 2500 THEN '일반 관리자'
			WHEN JOB = 'SALESMAN' AND COMM > 500 THEN '우수 영업사원'
			WHEN JOB = 'SALESMAN' THEN '일반 영업사원'
			WHEN DEPTNO = 20 THEN '연구원'
			ELSE '일반 직원'
		END AS "세부_직급"
FROM EMP;

--
SELECT ENAME, SAL, SAL * 1.1 AS "인상 후 급여"
FROM EMP;

--
SELECT SUM(SAL) AS "전체급여합계"
FROM EMP;






