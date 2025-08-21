-- DDL
-- 12-1 : EMP_DDL

CREATE TABLE EMP_DDL(
    EMPNO   NUMBER(4),        -- 사원번호 : 4자리 숫자
    ENAME   VARCHAR2(10),     -- 사원명 : 최대 10자 가변길이 문자열
    JOB     VARCHAR2(9),      -- 직책 : 최대 9자 가변길이
    MGR     NUMBER(4),        -- 관리자 사원번호 : 4자리 숫자
    HIREDAT DATE,             -- 입사일 : 날짜 타입
    SAL     NUMBER(7,2),      -- 급여 : 전체 7자리, 소수점 2자리 (99999.99)
    COMM    NUMBER(7,2),      -- 커미션
    DEPTNO  NUMBER(2)         -- 부서번호 : 2자리 숫자
);

-- 테이블 구조 확인 (DESCRIBE)
DESC EMP_DDL;

-- 12-2 : CTAS를 이용한 테이블 복사 (구조 + 데이터)
/*
    - CREATE TABLE AS SELECT (CTAS) 구문 사용
    - 제약조건은 복사되지 않음
    - 백업 테이블 생성 시 자주 사용
*/
CREATE TABLE DEPT_DDL
 AS SELECT * FROM DEPT;
 
DESC DEPT_DDL;

-- 12-3 : 복사된 데이터 확인
SELECT * FROM DEPT_DDL;

-- 12-4 : 조건부 데이터 복사 (WHERE절 사용)
CREATE TABLE EMP_DDL_30
 AS SELECT * FROM EMP WHERE DEPTNO = 30;
 
SELECT * FROM EMP_DDL_30;

-- 12-5 : 테이블 구조만 복사 (데이터 제외)
/*
    - 두 테이블을 조인한 결과의 구조만 필요
    - WHERE 1 <> 1 조건으로 데이터 제외
    - 테스트용 빈 테이블 생성시 활용
    - 조인된 결과의 컬럼 구조 생성
*/
CREATE TABLE EMPDEPT_DDL
 AS SELECT E.EMPNO, E.ENAME, E.JOB, E.MGR, E.HIREDATE, E.SAL, E.COMM,
           D.DEPTNO, D.DNAME, D.LOC
    FROM EMP E, DEPT D
    WHERE 1 <> 1; -- 항상 거짓인 조건 => 데이터는 0건, 구조만 생성
    
SELECT * FROM EMPDEPT_DDL;

-- 12-6 : ALTER TABLE
CREATE TABLE EMP_ALTER
 AS SELECT * FROM EMP;
 
SELECT * FROM EMP_ALTER;

-- 12-7 : 컬럼 추가 (ADD)
/*
    - HP
    - VARCHAR2(20)
    - 새컬럼의 기본값은 NULL
*/
ALTER TABLE EMP_ALTER
 ADD HP VARCHAR2(20);    -- HP 컬럼 추가

SELECT * FROM EMP_ALTER; -- 맨 끝에 HP 컬럼 추가됨 (값은 모두 NULL)

-- 12-8 : 컬럼명 변경 (RENAME COLUMN)
ALTER TABLE EMP_ALTER
RENAME COLUMN HP TO TEL;

SELECT * FROM EMP_ALTER;

DESC EMP_ALTER;
-- 12-9 : 컬럼 속성 변경 (MODIFY)
/*
    기존 : NUMBER(4)
    변경 : NUMBER(5)
    데이터 손실 없이 크기 확장
    축소시에는 기존 데이터 크기 확인 필요
*/
ALTER TABLE EMP_ALTER
MODIFY EMPNO NUMBER(5);
 
SELECT * FROM EMP_ALTER;
 
-- 12-10 : 컬럼 삭제 (DROP COLUMN)
/*
    - TEL 컬럼 완전 삭제
    - 해당 컬럼의 모든 데이터도 함께 삭제
    - 복구 불가능 (신중히 실행)
*/
ALTER TABLE EMP_ALTER
 DROP COLUMN TEL;
 
SELECT * FROM EMP_ALTER;

-- 12-11 : 테이블명 변경 (RENAME)
/*
    EMP_ALTER => EMP_RENAME
    - 참조하는 다른 객체들 확인 필요
*/
RENAME EMP_ALTER TO EMP_RENAME;

-- 12-12
DESC EMP_ALTER;

-- 12-13 
SELECT * FROM EMP_RENAME;

-- 12-14 : TRUNCATE - 데이터만 전체 삭제
/*
    - 구조는 유지
    - 모든 데이터는 즉시 삭제 (ROLLBACK 불가)
    - DELETE보다 빠른 성능 (DDL)
*/
TRUNCATE TABLE EMP_RENAME;  -- 모든 행 삭제, 구조는 유지

-- 12-15 : DROP TABLE - 테이블 완전 제거
/*
    - 테이블 구조와 데이터 모두 삭제
    - 관련 인덱스도 함께 삭제
    - 휴지통(RECYCLE BIN)에 보관 (PURGE 옵션 없으면)
    - 복구하려면 FLASHBACCK TABLE 사용
*/
DROP TABLE EMP_RENAME;

-- 12-16
DESC EMP_RENAME;    