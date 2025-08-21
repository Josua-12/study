-- DML : insert, update, delete

-- 테이블 생성 
CREATE TABLE EMP_WORK AS SELECT * FROM EMP;
CREATE TABLE DEPT_WORK AS SELECT * FROM DEPT;
CREATE TABLE SALGRADE_WORK AS SELECT * FROM SALGRADE;

-- 1. 신입사원 입사 처리
/*
    2025년 신입사원 3명 입사했습니다.
    이순신(개발팀), 신사임당(마케팅팀), 손흥민(개발팀)
*/
DESC EMP_WORK;
SELECT EMPNO FROM EMP_WORK;
SELECT MAX(EMPNO) FROM EMP_WORK;        -- 현재 최대 사번(7934)
SELECT * FROM EMP_WORK;

SELECT DEPTNO, DNAME FROM DEPT_WORK;

ALTER TABLE EMP_WORK
MODIFY ENAME VARCHAR2(30);

INSERT INTO EMP_WORK(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) 
               VALUES(7935, '이순신', '개발팀', NULL, SYSDATE, 200, NULL, 20);

INSERT INTO EMP_WORK(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) 
               VALUES(7936, '신사임당', '마케팅', NULL, SYSDATE, 200, NULL, 30);
               
INSERT INTO EMP_WORK(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) 
               VALUES(7937, '손흥민', '개발팀', NULL, SYSDATE, 200, NULL, 20);
               
SELECT * FROM EMP_WORK WHERE EMPNO >= 7935;

-- 2. 부서 통폐합
/*
    30번 부서(SALES)와 40번 부서(OPERATIONS)를 통합하여 
    35번 부서(SALES_OP)로 만들고, 직원들을 이동시키시오.
*/

-- STEP1 : 새 부서 생성하기
DESC DEPT_WORK;
SELECT * FROM DEPT_WORK;
SELECT * FROM EMP_WORK;
INSERT INTO DEPT_WORK (DEPTNO, DNAME, LOC)
VALUES (35, 'SALES_OP', 'SEOUL');

-- step 2: 직원들 부서 이동
update emp_work
set deptno = 15
where deptno in (30,40);

-- step3 : 기존 부서 삭제
delete from dept_work
where deptno in (30, 40);
