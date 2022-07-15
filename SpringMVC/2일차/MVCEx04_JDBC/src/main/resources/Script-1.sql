SELECT * FROM tab;

CREATE  SEQUENCE emp_idx_seq;

CREATE TABLE emp(
	idx NUMBER PRIMARY KEY,
	name varchar2(50) NOT null,
	role varchar2(50) NOT null	 
);

INSERT INTO emp values(emp_idx_seq.nextval,'한사람','자바프로그래머');
INSERT INTO emp values(emp_idx_seq.nextval,'두사람','웹프로그래머');
INSERT INTO emp values(emp_idx_seq.nextval,'세사람','응용프로그래머');
INSERT INTO emp values(emp_idx_seq.nextval,'네사람','서버프로그래머');
INSERT INTO emp values(emp_idx_seq.nextval,'오사람','백엔드프로그래머');

COMMIT;

SELECT * FROM emp;