-- 사용자 테이블을 작성한다. : 나중에 회원정보 테이블을 사용하면 된다.
CREATE SEQUENCE users_idx_seq;
CREATE TABLE users(
	idx NUMBER PRIMARY KEY,       		-- 키필드
	userid varchar2(100) NOT NULL, 		-- 사용자 아이디
	password varchar2(100) NOT NULL,	-- 사용자 비번
	enabled NUMBER DEFAULT 1			-- 사용가능 여부
);

INSERT INTO users VALUES (users_idx_seq.nextval,'user1','1234', 1);
INSERT INTO users VALUES (users_idx_seq.nextval,'user3','1234', 1);
INSERT INTO users VALUES (users_idx_seq.nextval,'admin','1234', 1);
INSERT INTO users VALUES (users_idx_seq.nextval,'dba','1234', 1);
INSERT INTO users VALUES (users_idx_seq.nextval,'root','1234', 1);
INSERT INTO users VALUES (users_idx_seq.nextval,'dba2','1234', 1);

SELECT * FROM users;

-- 권한 정보 테이블
CREATE SEQUENCE user_role_idx_seq;
CREATE TABLE user_role(
	idx NUMBER PRIMARY KEY,       		-- 키필드
	userid varchar2(100) NOT NULL, 		-- 사용자 아이디
	role varchar2(100) NOT NULL 		-- 권한
);

INSERT INTO user_role values(user_role_idx_seq.nextval,'user1', 'ROLE_USER');
INSERT INTO user_role values(user_role_idx_seq.nextval,'user3', 'ROLE_USER');
INSERT INTO user_role values(user_role_idx_seq.nextval,'admin', 'ROLE_ADMIN');
INSERT INTO user_role values(user_role_idx_seq.nextval,'admin', 'ROLE_DBA');
INSERT INTO user_role values(user_role_idx_seq.nextval,'root', 'ROLE_ADMIN');
INSERT INTO user_role values(user_role_idx_seq.nextval,'dba', 'ROLE_DBA');
INSERT INTO user_role values(user_role_idx_seq.nextval,'dba2', 'ROLE_DBA');

SELECT * FROM user_role;

COMMIT;