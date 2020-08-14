create table book_stock (
	id number(10) primary key,
	book_id number(10) not null unique,
	quantity number(5) default 0,
	limit number(5) default 5,
	stand_location char(1) not null
);

alter table book_stock add constraint book_stock_sl_chk CHECK (stand_location in ('A', 'B', 'C', 'D'));

commit;

comment ON COLUMN book_stock.id IS '��� ���̵�';
comment ON COLUMN book_stock.book_id IS '���� ���̵�';
comment ON COLUMN book_stock.quantity IS '���� ��ġ ����';
comment ON COLUMN book_stock.limit IS '���� ��� ����';
comment ON COLUMN book_stock.stand_location IS '���� �Ŵ� ��ġ';

create sequence book_stock_seq
start with 1
increment by 1
maxvalue 9999;