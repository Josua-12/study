create database securitydb
	character set utf8mb4
	collate utf8mb4_general_ci;

insert into `role` (id, name )
values (1, 'USER');

insert into `role` (id, name )
values (2, 'MANAGER');

insert into `role` (id, name )
values (3, 'ADMIN');

create database librarydb
	character set utf8mb4
	collate utf8mb4_general_ci;

-- 외래 키 제약 조건 비활성화
set foreign_key_checks = 0;

-- 필요한 만큼 drop table 문 추가
drop table if exists board;
drop table if exists members;


INSERT INTO librarydb.members
(	email, 
	password,
	name,
	phone, 
	address, 
	join_date, 
	member_type, 
	status, 
	role, 
	created_at, 
	updated_at)
VALUES( 
	'test1@library.com', 
	'$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 
	'테스트1', 
	'010-1234-5678',
	'서울시 마포구 신촌로 123', 
	NOW() - interval 120 day,
	'REGULAR', 
	'ACTIVE', 
	'USER', 
	NOW() - interval 120 day, 
	NOW() - interval 120 day);

INSERT INTO librarydb.members
(	email, 
	password,
	name,
	phone, 
	address, 
	join_date, 
	member_type, 
	status, 
	role, 
	created_at, 
	updated_at)
VALUES( 
	'test2@library.com', 
	'$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 
	'테스트2', 
	'010-2222-2222',
	'서울시 마포구 신촌로 456', 
	NOW() - interval 90 day,
	'REGULAR', 
	'ACTIVE', 
	'USER', 
	NOW() - interval 90 day, 
	NOW() - interval 90 day);

INSERT INTO librarydb.members
(	email, 
	password,
	name,
	phone, 
	address, 
	join_date, 
	member_type, 
	status, 
	role, 
	created_at, 
	updated_at)
VALUES( 
	'test3@library.com', 
	'$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 
	'테스트3', 
	'010-3333-3333',
	'서울시 마포구 신촌로 789', 
	NOW() - interval 180 day,
	'SILVER', 
	'ACTIVE', 
	'USER', 
	NOW() - interval 180 day, 
	NOW() - interval 180 day);

INSERT INTO librarydb.members
(	email, 
	password,
	name,
	phone, 
	address, 
	join_date, 
	member_type, 
	status, 
	role, 
	created_at, 
	updated_at)
VALUES( 
	'test4@library.com', 
	'$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 
	'테스트4', 
	'010-4444-4444',
	'서울시 마포구 홍대입구로 101', 
	NOW() - interval 365 day,
	'GOLD', 
	'ACTIVE', 
	'USER', 
	NOW() - interval 365 day, 
	NOW() - interval 365 day);

INSERT INTO librarydb.members
(	email, 
	password,
	name,
	phone, 
	address, 
	join_date, 
	member_type, 
	status, 
	role, 
	created_at, 
	updated_at)
VALUES( 
	'test5@library.com', 
	'$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 
	'테스트5', 
	'010-5555-5555',
	'서울시 중구 을지로 234', 
	NOW() - interval 500 day,
	'VIP', 
	'ACTIVE', 
	'LIBRARIAN', 
	NOW() - interval 500 day, 
	NOW() - interval 500 day);

-- 실제 회원 ID 확인 (게시글 작성자로 사용)
-- 더미 데이터 100개 추가 
-- 1-10 공지사항 (NOTICE)
INSERT INTO librarydb.board
(
	title, 
	content,
	author_id,
	view_count,
	like_count, 
	status,  
	category, 
	created_at, 
	updated_at
	)
VALUES(
	'📢2025년 하반기 도서 구입 계획', 
	'회원 여러분의 의견을 수렴하여 2025년 하반기 신규 도서 구입을 계획하고 있습니다. 원하는 도서가 있으시면 댓글로 남겨주세요', 
	5 , 
	2500, 
	180, 
	'ACTIVE', 
	'NOTICE', 
	NOW() - interval 60 day,
	NOW() - interval 60 day);


INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('📢 열람실 좌석 예약 시스템 오픈', '이제 온라인으로 열람실 좌석을 미리 예약하실 수 있습니다. 많은 이용 부탁드립니다.', 5, 1800, 140, 'ACTIVE', 'NOTICE', NOW() - INTERVAL 55 DAY, NOW() - INTERVAL 55 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('📢 회원 등급 혜택 안내', 'REGULAR, SILVER, GOLD, VIP 등급별 혜택이 변경되었습니다. 자세한 내용은 본문을 참고해주세요.', 5, 3200, 200, 'ACTIVE', 'NOTICE', NOW() - INTERVAL 50 DAY, NOW() - INTERVAL 50 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('📢 도서 반납함 추가 설치', '편의를 위해 지하 1층과 3층에 무인 반납함이 추가 설치되었습니다.', 4, 1500, 95, 'ACTIVE', 'NOTICE', NOW() - INTERVAL 45 DAY, NOW() - INTERVAL 45 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('📢 신규 전자책 서비스 론칭', '이제 모바일 앱에서도 전자책을 대여하실 수 있습니다!', 5, 4200, 320, 'ACTIVE', 'NOTICE', NOW() - INTERVAL 40 DAY, NOW() - INTERVAL 40 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('📢 여름 독서 프로그램 안내', '7-8월 여름방학 기간 어린이 독서 프로그램이 진행됩니다.', 4, 2100, 150, 'ACTIVE', 'NOTICE', NOW() - INTERVAL 35 DAY, NOW() - INTERVAL 35 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('📢 임시 휴관 안내', '시설 보수 공사로 인해 10월 20일 임시 휴관합니다.', 5, 1900, 80, 'ACTIVE', 'NOTICE', NOW() - INTERVAL 30 DAY, NOW() - INTERVAL 30 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('📢 신규 희망도서 신청 서비스', '원하는 도서를 직접 신청하실 수 있는 희망도서 신청 서비스가 오픈되었습니다.', 4, 2800, 190, 'ACTIVE', 'NOTICE', NOW() - INTERVAL 25 DAY, NOW() - INTERVAL 25 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('📢 주차 공간 확대 안내', '주차장이 기존 50대에서 80대 수용 가능하도록 확대되었습니다.', 5, 1600, 70, 'ACTIVE', 'NOTICE', NOW() - INTERVAL 20 DAY, NOW() - INTERVAL 20 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('📢 겨울 방학 개관 시간 안내', '12월-2월 겨울방학 기간 개관 시간이 변경됩니다.', 5, 2200, 120, 'ACTIVE', 'NOTICE', NOW() - INTERVAL 15 DAY, NOW() - INTERVAL 15 DAY);

ALTER TABLE board MODIFY category VARCHAR(10);

-- 41-70: 질문답변 (QNA)
INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('회원가입은 어떻게 하나요?', '온라인 회원가입 절차가 궁금합니다.', 1, 450, 38, 'ACTIVE', 'QNA', NOW() - INTERVAL 58 DAY, NOW() - INTERVAL 58 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('대출 기간은 며칠인가요?', '일반 도서 대출 기간이 궁금해요.', 2, 520, 45, 'ACTIVE', 'QNA', NOW() - INTERVAL 57 DAY, NOW() - INTERVAL 57 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('연장 신청은 몇 번까지 가능한가요?', '대출 연장에 제한이 있나요?', 3, 380, 32, 'ACTIVE', 'QNA', NOW() - INTERVAL 56 DAY, NOW() - INTERVAL 56 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('분실 도서 처리 방법', '책을 분실했을 때 어떻게 처리하나요?', 1, 680, 52, 'ACTIVE', 'QNA', NOW() - INTERVAL 55 DAY, NOW() - INTERVAL 55 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('회원증 재발급 방법', '회원증을 잃어버렸는데 재발급 받을 수 있나요?', 2, 340, 28, 'ACTIVE', 'QNA', NOW() - INTERVAL 54 DAY, NOW() - INTERVAL 54 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('희망도서 신청 후 얼마나 걸리나요?', '신청한 도서는 언제쯤 들어오나요?', 3, 490, 41, 'ACTIVE', 'QNA', NOW() - INTERVAL 53 DAY, NOW() - INTERVAL 53 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('어린이 회원 가입 가능한가요?', '초등학생도 회원 가입이 가능한가요?', 1, 410, 35, 'ACTIVE', 'QNA', NOW() - INTERVAL 52 DAY, NOW() - INTERVAL 52 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('비회원도 열람실 이용 가능한가요?', '회원 가입 안 해도 열람실 사용할 수 있나요?', 2, 580, 48, 'ACTIVE', 'QNA', NOW() - INTERVAL 51 DAY, NOW() - INTERVAL 51 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('예약 도서 찾는 방법', '예약한 도서가 들어왔는지 어떻게 확인하나요?', 3, 320, 27, 'ACTIVE', 'QNA', NOW() - INTERVAL 50 DAY, NOW() - INTERVAL 50 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('스터디룸 예약 시간', '스터디룸은 몇 시간까지 이용 가능한가요?', 1, 540, 44, 'ACTIVE', 'QNA', NOW() - INTERVAL 49 DAY, NOW() - INTERVAL 49 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('도서 검색 방법', '원하는 책을 빨리 찾는 방법이 있나요?', 2, 290, 24, 'ACTIVE', 'QNA', NOW() - INTERVAL 48 DAY, NOW() - INTERVAL 48 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('전자책 대여 방법', '전자책은 어떻게 빌리나요?', 3, 470, 39, 'ACTIVE', 'QNA', NOW() - INTERVAL 47 DAY, NOW() - INTERVAL 47 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('회원 등급 상향 조건', 'SILVER 등급으로 올라가려면 어떻게 해야 하나요?', 1, 620, 51, 'ACTIVE', 'QNA', NOW() - INTERVAL 46 DAY, NOW() - INTERVAL 46 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('무인 반납함 사용법', '무인 반납함은 어떻게 사용하나요?', 2, 350, 29, 'ACTIVE', 'QNA', NOW() - INTERVAL 45 DAY, NOW() - INTERVAL 45 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('주말 개관 시간 문의', '주말에는 몇 시부터 열리나요?', 3, 430, 36, 'ACTIVE', 'QNA', NOW() - INTERVAL 44 DAY, NOW() - INTERVAL 44 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('대출 중인 책 예약 방법', '이미 대출된 책을 예약할 수 있나요?', 1, 510, 42, 'ACTIVE', 'QNA', NOW() - INTERVAL 43 DAY, NOW() - INTERVAL 43 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('연체료 확인 방법', '내가 낸 연체료는 어디서 확인하나요?', 2, 390, 33, 'ACTIVE', 'QNA', NOW() - INTERVAL 42 DAY, NOW() - INTERVAL 42 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('외국 서적도 있나요?', '영어 원서를 빌릴 수 있나요?', 3, 280, 23, 'ACTIVE', 'QNA', NOW() - INTERVAL 41 DAY, NOW() - INTERVAL 41 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('복사 서비스 이용 방법', '복사는 어디서 할 수 있나요?', 1, 320, 26, 'ACTIVE', 'QNA', NOW() - INTERVAL 40 DAY, NOW() - INTERVAL 40 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('열람실 자리 배정', '열람실 좌석은 지정제인가요?', 2, 450, 37, 'ACTIVE', 'QNA', NOW() - INTERVAL 39 DAY, NOW() - INTERVAL 39 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('노트북 사용 가능 구역', '노트북은 어디서 사용할 수 있나요?', 3, 530, 44, 'ACTIVE', 'QNA', NOW() - INTERVAL 38 DAY, NOW() - INTERVAL 38 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('와이파이 비밀번호', '와이파이 접속 방법이 궁금해요.', 1, 410, 34, 'ACTIVE', 'QNA', NOW() - INTERVAL 37 DAY, NOW() - INTERVAL 37 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('그룹 스터디룸 인원 제한', '스터디룸은 몇 명까지 이용 가능한가요?', 2, 370, 31, 'ACTIVE', 'QNA', NOW() - INTERVAL 36 DAY, NOW() - INTERVAL 36 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('주차 시간 제한', '주차는 몇 시간까지 무료인가요?', 3, 490, 40, 'ACTIVE', 'QNA', NOW() - INTERVAL 35 DAY, NOW() - INTERVAL 35 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('신분증 필수인가요?', '신분증이 없어도 이용할 수 있나요?', 1, 310, 25, 'ACTIVE', 'QNA', NOW() - INTERVAL 34 DAY, NOW() - INTERVAL 34 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('음식물 반입 금지인가요?', '간단한 간식은 괜찮나요?', 2, 420, 35, 'ACTIVE', 'QNA', NOW() - INTERVAL 33 DAY, NOW() - INTERVAL 33 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('휴관일은 언제인가요?', '정기 휴관일이 있나요?', 3, 270, 22, 'ACTIVE', 'QNA', NOW() - INTERVAL 32 DAY, NOW() - INTERVAL 32 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('모바일 회원증 사용', '모바일 회원증으로도 대출이 가능한가요?', 1, 560, 46, 'ACTIVE', 'QNA', NOW() - INTERVAL 31 DAY, NOW() - INTERVAL 31 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('단체 견학 신청', '학교 단체로 방문하려면 어떻게 하나요?', 2, 340, 28, 'ACTIVE', 'QNA', NOW() - INTERVAL 30 DAY, NOW() - INTERVAL 30 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('도서 기증 방법', '집에 있는 책을 기증하고 싶은데 가능한가요?', 3, 480, 39, 'ACTIVE', 'QNA', NOW() - INTERVAL 29 DAY, NOW() - INTERVAL 29 DAY);


-- 외래 키 제약 조건 활성화
set foreign_key_checks = 1;