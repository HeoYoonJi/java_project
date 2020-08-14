--내방 고객 현황
create table customer_entrance_tbl(
    id number(5) primary key,
    entrance_date date not null,
    entrance_time_band char(13) not null,
    visit1 varchar2(100),
    visit2 varchar2(100),
    visit3 varchar2(100),
    visit4 varchar2(100),
    visit5 varchar2(100),
    visit6 varchar2(100),
    visit7 varchar2(100),
    visit8 varchar2(100),
    visit9 varchar2(100),
    visit10 varchar2(100),
    visit11 varchar2(100),
    visit12 varchar2(100),
    visit13 varchar2(100),
    visit14 varchar2(100),
    visit15 varchar2(100),
    visit16 varchar2(100),
    visit17 varchar2(100)
);

create sequence customer_entrance_seq
start with 1
increment by 1
maxvalue 9999;

--내방 고객별 도서 구매 현황
