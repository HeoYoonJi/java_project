--도서테이블
create table book_tbl (
	id number(10) primary key,
	name varchar2(200) not null,
	genre varchar2(20) not null,
	book_img varchar2(100) not null,
	isbn10 char(10) not null,
	isbn13 char(13) not null,
	pubdate date,
	author varchar2(100) not null,
	pubs varchar2(100) not null,
	price number(10) not null,
	bs_price number(10) default 0,
	bs_rate number(3) default 0,
	page_num number(5),
	weight number(10),
	book_size varchar2(50),
	eval_point number(3,1) default 0
);


alter table book_tbl
ADD CONSTRAINT book_tbl_isbn10_un UNIQUE(isbn10);

alter table book_tbl
ADD CONSTRAINT book_tbl_isbn13_un UNIQUE(isbn13);

create sequence book_seq
start with 1
increment by 1
maxvalue 9999;

insert into book_tbl values (
book_seq.nextval,
'나는 말하듯이 쓴다 강원국의 말 잘하고 글 잘 쓰는 법',
'인문',
'a.png',
'1190786869',
'9791190786867',
'2020-06-18',
'강원국',
'위즈덤하우스',
16000,
14400,
10,
380,
664,
152*225*22,
8.5
);