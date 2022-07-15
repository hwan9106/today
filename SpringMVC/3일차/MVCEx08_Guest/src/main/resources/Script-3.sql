SELECT * FROM tab;

DROP SEQUENCE guest_idx_seq;
CREATE SEQUENCE guest_idx_seq;

DROP TABLE guest;
CREATE TABLE guest(
	idx NUMBER PRIMARY KEY, -- 키필드
	REF NUMBER DEFAULT 0, -- 원본글 번호
	seq NUMBER DEFAULT 0, -- 표시 순서
	lev NUMBER DEFAULT 0, -- 몇 단계 답변이냐
	name varchar2(100) NOT NULL,
	password varchar2(100) NOT NULL,
	content varchar2(4000) NOT NULL,
	regDate TimeStamp DEFAULT sysdate,
	ip varchar2(50) NOT NULL,
	del char(1) DEFAULT 'N'
);

-- 원본글 3개
INSERT INTO guest VALUES (guest_idx_seq.nextval,1,0,0,'주인장1','1234','아마도 내용1',sysdate,'192.168.0.25','N');
INSERT INTO guest VALUES (guest_idx_seq.nextval,2,0,0,'주인장2','1234','아마도 내용2',sysdate,'192.168.0.25','N');
INSERT INTO guest VALUES (guest_idx_seq.nextval,3,0,0,'주인장3','1234','아마도 내용3',sysdate,'192.168.0.25','N');
SELECT * FROM guest;

-- 1번글의 답변 2개
INSERT INTO guest VALUES (guest_idx_seq.nextval,1,1,1,'주인장3','1234','아마도 내용3',sysdate,'192.168.0.25','N');
INSERT INTO guest VALUES (guest_idx_seq.nextval,1,2,1,'주인장3','1234','아마도 내용3',sysdate,'192.168.0.25','N');
SELECT * FROM guest;

-- 2번글의 답변 2개
INSERT INTO guest VALUES (guest_idx_seq.nextval,2,1,1,'주인장3','1234','아마도 내용3',sysdate,'192.168.0.25','N');
INSERT INTO guest VALUES (guest_idx_seq.nextval,2,2,1,'주인장3','1234','아마도 내용3',sysdate,'192.168.0.25','N');
SELECT * FROM guest;

-- 순서대로 불러오는 쿼리
SELECT * FROM guest ORDER BY REF DESC, seq ASC;

COMMIT;

