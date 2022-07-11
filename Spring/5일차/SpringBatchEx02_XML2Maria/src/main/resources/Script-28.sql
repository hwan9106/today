drop table EXAM_RESULT;

create table EXAM_RESULT(
	IDX INT primary key auto_increment,
	Student_name varchar(200) not null,
	DOB date not null,
	percentage float(7,2) not null
);

select * from exam_result;