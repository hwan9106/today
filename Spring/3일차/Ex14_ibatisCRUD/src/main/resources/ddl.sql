create table people(
	idx int primary key auto_increment,
	name varchar(200) not null,
	age int default 0
);

select * from people;

desc people;
insert into people(name, age) values
('한사람',23),
('두사람',25),
('세사람',42),
('네사람',33),
('오사람',28),
('육사람',18);

select * from people;
