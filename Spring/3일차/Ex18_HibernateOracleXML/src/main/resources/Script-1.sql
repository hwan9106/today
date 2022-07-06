
CREATE SEQUENCE student_id_seq;

create table STUDENT (
   id INT NOT NULL,
   first_name VARCHAR2(30) NOT NULL,
   last_name  VARCHAR2(30) NOT NULL,
   section    VARCHAR2(30) NOT NULL,
   PRIMARY KEY (id)
);

select * from student;