drop table memo;
create table memo(
	idx int primary key auto_increment,
	name varchar(100) not null,
	password varchar(100) not null,
	content text not null,
	regDate timestamp default now(),
	ip varchar(20) not null
);
desc memo;

insert into memo
	(name,password,content,ip)
values
	('한사람','1234','와 일빠다!!!\n<b>진하게 나옴</b>\n하하하하','192.168.0.24'),
	('두사람','1234','와 일빠다!!!\n<b>진하게 나옴</b>\n하하하하','192.168.0.25'),
	('세사람','1234','와 일빠다!!!\n<b>진하게 나옴</b>\n하하하하','192.168.0.26'),
	('네사람','1234','와 일빠다!!!\n<b>진하게 나옴</b>\n하하하하','192.168.0.27'),
	('오사람','1234','와 일빠다!!!\n<b>진하게 나옴</b>\n하하하하','192.168.0.28');
	
commit;