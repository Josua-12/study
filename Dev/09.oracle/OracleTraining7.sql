-- 뷰(VIEW)
-- 13-14 : 뷰 생성 권한 부여 (SYSTEM 계정에서 실행)
-- sqlplus sys/oracle as sysdba
-- grant create view to scott;

-- 13-15 : 뷰 생성
-- 20번 부서 직원 정보만 선택적으로 보여주는 논리적 테이블
CREATE VIEW VW_EMP30
 AS (SELECT EMPNO, ENAME, JOB, DEPTNO
     FROM EMP
     WHERE DEPTNO = 20); -- 20번 부서만 필터링
     
-- 13-16: 생성된 뷰 확인
SELECT * FROM VW_EMP30;

-- 13-18 : 뷰 삭제
DROP VIEW Vw_EMP30;

-- 13-20 : ROWNUM 기본 사용
SELECT ROWNUM,  -- 행번호 
        E.* 
FROM EMP E;

-- 13-21 : ROWNUM과 ORDER BY 사용시 이슈
-- 정렬 전에 ROWNUM이 우선 적용이 된 후, ORDER BY 가 적용이 됨
-- 의도한 결과대로 나오지 않음 (의도는 ROWNUM은 해당 결과에 줄번호 세우려는 목적이었음)
SELECT ROWNUM,  -- ROWNUM이 먼저 부여된 후 정렬되므로 순서가 뒤섞임
        E.* 
FROM EMP E
ORDER BY SAL DESC;

--- 13-22 : 서브쿼리를 사용해서 정렬 후 ROWNUM 부여
SELECT ROWNUM,              -- 2. 정렬 후 행번호가 부여
        E.* 
FROM ( SELECT *
        FROM EMP
        ORDER BY SAL DESC)  -- 1. 서브쿼리에서 먼저 정렬
        E;

-- 13-23 : with 절을 사용한 ROWNUM 부여
-- WITH 절 : 임시 결과 집합을 정의 (가독성 향상)
WITH E AS (
            SELECT *
            FROM EMP
            ORDER BY SAL DESC
            )   --임시 테이블 E 정의
SELECT ROWNUM, E.*
FROM E;

-- 13-24 : TOP-N 쿼리 (상위 3명)
-- ROWNUM을 사용한 상위 N개 레코드 추출
SELECT ROWNUM,              -- 2. 정렬 후 행번호가 부여
        E.* 
FROM ( SELECT *
        FROM EMP
        ORDER BY SAL DESC)  -- 1. 서브쿼리에서 먼저 정렬
        E
WHERE ROWNUM <= 5;

-- SEQUENCE (시퀀스)
-- 13-26: 시퀀스 생성 권한 부여 (SYSTEM 계정에서 실행)
-- grant create sequence to scott;

-- 13-27: 테이블 구조 복사
-- DROP TABLE DEPT_SEQUENCE;
CREATE TABLE DEPT_SEQUENCE
AS SELECT *
FROM DEPT
WHERE 1 <> 1;       --거짓 조건 : 구조만 복사

-- 13-28 : 시퀀스 생성
-- DROP SEQUENCE SEQ_DEPT_SEQUENCE;  --수정할때는 무결성이 깨질 수 있기 때문에(기존의 값이 꼬일 수 있음), 아예 삭제하고 새로 생성하는 것이 맞음. (+위의 테이블도 마찬가지) (이미 운영중인 경우는 예외)
CREATE SEQUENCE SEQ_DEPT_SEQUENCE
    INCREMENT BY 10             -- 10씩 증가
    START WITH 10               -- 시작값
    Maxvalue 1000               -- 최대값
    MINVALUE 0                  -- 최소값
    NOCYCLE                     -- 최대값 도달시 순환하지 않음
    CACHE 2;                    -- 메모리에 2개씩 미리 생성 -> 더 빠른 속도 위함
    
-- 13-29: 시퀀스 정보 확인
SELECT * FROM USER_SEQUENCES;

-- 13-30 : 시퀀스를 사용한 데이터 입력
-- NEXTVAL : 다음 시퀀스 값 반환 (값 증가함 <-10씩 증가한다고 정의했기 때문)
INSERT INTO DEPT_SEQUENCE (DEPTNO, DNAME, LOC)
VALUES (SEQ_DEPT_SEQUENCE.NEXTVAL, 'DATABASE', 'SEOUL');

SELECT * FROM DEPT_SEQUENCE ORDER BY DEPTNO;

-- 13-31: 현재 시퀀스 값 확인
--CURRVAL : 현재 시퀀스 값 반환 (값이 증가하지 않음)
SELECT SEQ_DEPT_SEQUENCE.CURRVAL
FROM DUAL;

-- 13-32: 추가 데이터 입력
INSERT INTO DEPT_SEQUENCE (DEPTNO, DNAME, LOC)
VALUES (SEQ_DEPT_SEQUENCE.NEXTVAL, 'DATABASE', 'SEOUL');

SELECT * FROM DEPT_SEQUENCE ORDER BY DEPTNO;

-- 13-33: 시퀀스 수정
-- ALTER SEQUENCE : 시퀀스 속성 변경 (START WITH는 변경 불가)















