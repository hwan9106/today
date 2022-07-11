DROP SEQUENCE student_id_seq;
CREATE SEQUENCE student_id_seq;

DROP TABLE student;

CREATE TABLE student(
	id NUMBER PRIMARY KEY,
	first_name varchar2(100) NOT NULL,
	last_name varchar2(100) NOT NULL,
	section varchar2(100) NOT NULL
);

SELECT * FROM student; 
