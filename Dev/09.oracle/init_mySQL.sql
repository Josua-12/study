-- 데이터베이스 생성
create database if not exists sboot
character set uft8mb4	-- MYSQL의 표준 UTF-8 인코딩(이모지까지 지원)
collate utf8mb3_general_ci;	-- 대소문자 구분하지 않고 정렬/검색하는 일반 규칙

create database if not exists bookjpa;