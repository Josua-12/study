desc emp;

SELECT * FROM emp;

SELECT DEPTNO FROM EMP;

SELECT DISTINCT DEPTNO FROM EMP;

select distinct job, deptno 
from emp;

select job, deptno 
from emp;

SELECT * FROM DEPT;

select ename, sal, sal*12+comm as annsal,comm from emp;
select ename, sal, sal*12+comm as "annsal",comm from emp;
select ename, sal, sal*12+comm annsal,comm from emp;

select * from emp
order by sal;

select * from emp
order by empno;

select * from emp order by sal desc;
select * from emp order by empno desc;

-- emp 테이블의 전체 열으리 부서 번호(오름차순)와 급여(내림차순으로 정렬하기)
select * from emp order by deptno asc, sal desc;