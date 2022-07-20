
create sequence member_idx_seq;
DROP TABLE MEMBER;
create table member(
	idx 		number primary key,
	userid		varchar2(100) not null,
	password	varchar2(100) not null,
	username	varchar2(100) not null,
	nickname	varchar2(100) not null,
	email		varchar2(100) not null,
	gender		varchar2(1) CONSTRAINT gender_ck CHECK (gender IN ('M','F')),
	hp			varchar2(100) not null,
	zipcode		varchar2(10)  not null,
	address1	varchar2(200) not null,
	address2	varchar2(300) not null,
	use			varchar2(1)  DEFAULT '0' CONSTRAINT use_ck CHECK (use IN ('1','0')),
	regDate     timeStamp DEFAULT sysdate ,
	modiDate    timeStamp DEFAULT sysdate ,
	col1		varchar2(200),
	col2		varchar2(200),
	col3		varchar2(200)
); 
-- 여기에서 관리자 아이디를 몇개 만들어 놓아야 한다.
-- INSERT INTO MEMBER VALUES (member_idx_seq.nextval,'admin', '123456')


SELECT * FROM MEMBER;

CREATE SEQUENCE member_role_idx_seq;
CREATE TABLE member_role(
	idx NUMBER PRIMARY KEY,
	username varchar2(100) NOT NULL,
	role varchar2(30) NOT NULL
);
INSERT INTO member_role VALUES (member_role_idx_seq.nextval, 'admin','ROLE_ADMIN');

SELECT * FROM member_role ;