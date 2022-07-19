-- 자료실을 만들기 위한 시퀀스 및 테이블 생성
SELECT * FROM tab;
DROP SEQUENCE fileBoard_idx_seq;
CREATE SEQUENCE fileBoard_idx_seq;

DROP TABLE fileBoard;
CREATE TABLE fileBoard(
	idx NUMBER PRIMARY KEY,
	name varchar2(50) NOT NULL,
	password varchar2(50) NOT NULL,
	subject varchar2(500) NOT NULL,
	content varchar2(4000) NOT NULL,
	regDate timestamp DEFAULT sysdate,
	ip varchar2(50) NOT NULL
);

DROP SEQUENCE upfile_idx_seq;
CREATE SEQUENCE upfile_idx_seq;

DROP TABLE upfile;
CREATE TABLE upfile(
	idx NUMBER PRIMARY KEY,
	REF NUMBER DEFAULT 0,
	savefilename varchar2(500) NOT NULL,
	originalfilename varchar2(500) NOT NULL
);


