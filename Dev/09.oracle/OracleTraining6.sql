-- 회원 테이블 생성
DROP TABLE MEMBERS CASCADE CONSTRAINTS;
CREATE TABLE MEMBERS (
    MEMBER_ID       NUMBER(10)      NOT NULL,         -- 회원 고유번호 (시퀀스 사용 예정)
    EMAIL           VARCHAR2(100)   NOT NULL,      -- 이메일 (로그인 ID로 사용)`
    PASSWORD        VARCHAR2(200),      -- 비밀번호 (암호화 저장)
    MEMBER_NAME     VARCHAR2(50)    NOT NULL,       -- 회원 실명
    PHONE           VARCHAR2(20)    NOT NULL,       -- 휴대폰 번호
    BIRTH_DATE      DATE,               -- 생년월일 (마케팅 활용) 
    GENDER          CHAR(1),            -- 성별 (M/F, 선택사항)
    MEMBER_GRADE    VARCHAR2(20) DEFAULT 'BRONZE',  -- 회원등급  
    POINTS          NUMBER(10) DEFAULT 0,           -- 포인트 적립/사용
    TOTAL_PURCHASE  NUMBER(12) DEFAULT 0,    -- 누적 구매금액 (등급 산정용)
    -- 소셜 로그인 
    LOGIN_TYPE      VARCHAR2(20) DEFAULT 'NORMAL',  -- NORMAL/KAKAO/NAVER/GOOGLE
    SOCIAL_ID       VARCHAR2(100),              -- 소셜 플랫폼의 고유 ID 
    -- 마케팅 동의 
    IS_SNS_AGREE    CHAR(1) DEFAULT 'N',        -- SNS 수신 동의
    IS_EMAIL_AGREE  CHAR(1) DEFAULT 'N',        -- 이메일 수신 동의 
    -- 관리 
    JOIN_DATE       DATE DEFAULT SYSDATE,       -- 가입일
    LAST_LOGIN_DATE DATE,                       -- 최종 로그인 (휴면계정 관리)
    WITHDRAW_DATE   DATE,                       -- 탈퇴일 
    IS_ACTIVE       CHAR(1) DEFAULT 'Y' NOT NULL,     -- 계정 활성 상태 
    -- 제약조건
    CONSTRAINT PK_MEMBERS PRIMARY KEY(MEMBER_ID),
    CONSTRAINT UK_MEMBERS_EMAIL UNIQUE(EMAIL),
    CONSTRAINT UK_MEMBERS_SOCIAL UNIQUE(LOGIN_TYPE, SOCIAL_ID),
    CONSTRAINT CK_MEMBERS_GENDER CHECK (GENDER IN ('M', 'F')),
    CONSTRAINT CK_MEMBERS_GRADE CHECK (MEMBER_GRADE IN ('BRONZE', 'SILVER', 'GOLD', 'VIP', 'VVIP')),
    CONSTRAINT CK_MEMBERS_LOGIN_TYPE CHECK (LOGIN_TYPE IN ('NORMAL', 'KAKAO', 'NAVER', 'GOOGLE')),
    CONSTRAINT CK_MEMBERS_SNS_AGREE CHECK (IS_SNS_AGREE IN ('Y', 'N')),
    CONSTRAINT CK_MEMBERS_EMAIL_AGREE CHECK (IS_EMAIL_AGREE IN ('Y', 'N')),
    CONSTRAINT CK_MEMBERS_ACTIVE CHECK (IS_ACTIVE IN ('Y', 'N')),
    CONSTRAINT CK_MEMBERS_POINTS CHECK (POINTS >= 0),
    CONSTRAINT CK_MEMBERS_PURCHASE CHECK (TOTAL_PURCHASE >= 0)
);

-- 테이블 및 컬럼 주석
COMMENT ON TABLE MEMBERS IS '회원 정보 테이블';
COMMENT ON COLUMN MEMBERS.MEMBER_ID IS '회원 고유번호 (PK)';
COMMENT ON COLUMN MEMBERS.EMAIL IS '이메일 주소 (로그인 ID, UNIQUE)';
COMMENT ON COLUMN MEMBERS.PASSWORD IS '비밀번호 (암호화 저장)';