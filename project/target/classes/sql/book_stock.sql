create table book_stock (
	id number(10) primary key,
	isbn10 char(10) not null unique,
	quantity number(5) default 0,
	limit number(5) default 5,
	stand_location char(1) not null
);

alter table book_stock add constraint book_stock_sl_chk CHECK (stand_location in ('A', 'B', 'C', 'D'));

commit;

comment ON COLUMN book_stock.id IS '?ï¿½ï¿½ï¿?? ?ï¿½ï¿½?ï¿½ï¿½?ï¿½ï¿½';
comment ON COLUMN book_stock.book_id IS '?ï¿½ï¿½?ï¿½ï¿½ ?ï¿½ï¿½?ï¿½ï¿½?ï¿½ï¿½';
comment ON COLUMN book_stock.quantity IS '?ï¿½ï¿½?ï¿½ï¿½ ë¹„ì¹˜ ?ï¿½ï¿½?ï¿½ï¿½';
comment ON COLUMN book_stock.limit IS '?ï¿½ï¿½?ï¿½ï¿½ ?ï¿½ï¿½ï¿?? ?ï¿½ï¿½?ï¿½ï¿½';
comment ON COLUMN book_stock.stand_location IS '?ï¿½ï¿½?ï¿½ï¿½ ë§¤ï¿½? ?ï¿½ï¿½ï¿??';

create sequence book_stock_seq
start with 1
increment by 1
maxvalue 9999;

--?ï¿½ï¿½?ï¿½ï¿½ ?ï¿½ï¿½ï¿?? ë°˜ì˜ ?ï¿½ï¿½ï¿?? ?ï¿½ï¿½?ï¿½ï¿½ï¿??
create table stock_update_yn_tbl (
	stock_date char(10) not null,
	time_band char(13) not null,
	update_yn char(1) default 'n'
);

drop table stock_update_yn_tbl;

--ê¸ˆì¼ ?ï¿½ï¿½?ï¿½ï¿½ ?ï¿½ï¿½ì§œëŠ” y(yes)ï¿?? ë°˜ì˜ (yes : ?ï¿½ï¿½ï¿?? ë°˜ì˜)
update stock_update_yn_tbl set update_yn = 'y'
where stock_date

select TO_NUMBER('123') from dual;

delete stock_update_yn_tbl;

--LAST_DAY of month
select substr(to_char(last_day(TO_DATE('2020-05-01 22:00:00','yyyy-mm-dd hh24:mi:ss','NLS_DATE_LANGUAGE = Korean'))),7,2)
from dual;

--today
select to_number(substr(to_char(sysdate),7,2)) from dual;

CREATE OR REPLACE PROCEDURE stock_update_yn_proc
IS
	temp_date varchar2(2);
	temp_month varchar2(2);
	stock_date varchar2(10);
	time_band char(13);
    last_day_may number(3);
    last_day_june number(3);
    last_day_july number(3);
    last_day_aug number(3);
    tot_last_day number(3);
BEGIN
 
    select substr(to_char(last_day(TO_DATE('2020-05-01 22:00:00','yyyy-mm-dd hh24:mi:ss','NLS_DATE_LANGUAGE = Korean'))),7,2) into last_day_may
    from dual;
    select substr(to_char(last_day(TO_DATE('2020-05-01 22:00:00','yyyy-mm-dd hh24:mi:ss','NLS_DATE_LANGUAGE = Korean'))),7,2) into last_day_june
    from dual;
    select substr(to_char(last_day(TO_DATE('2020-05-01 22:00:00','yyyy-mm-dd hh24:mi:ss','NLS_DATE_LANGUAGE = Korean'))),7,2) into last_day_july
    from dual;
    select to_number(substr(to_char(sysdate),7,2)) into last_day_aug from dual;
    
    last_day_aug := last_day_aug - 2;
    tot_last_day := last_day_may + last_day_june + last_day_july + last_day_aug;
    
    FOR i IN 1..tot_last_day LOOP
    	IF i>31 AND i<=61 THEN
    		temp_month := '06';
            IF i<41 THEN
                temp_date := '0' || to_char(i - 31);
            ELSE
                temp_date := to_char(i - 31);
            END IF;
    	ELSIF i>61 AND i<=92 THEN
    		temp_month := '07';
            IF i<71 THEN
                temp_date := '0' || to_char(i - 61);
            ELSE
                temp_date := to_char(i - 61);
            END IF;
        ELSIF i>92 THEN
    		temp_month := '08';
            IF i<102 THEN
                temp_date := '0' || to_char(i - 92);
            ELSE
                temp_date := to_char(i - 92);
            END IF;
        ELSIF i<10 THEN
            temp_date := '0' || i;
    		temp_month := '05';
        ELSIF i>=10 AND i<=31 THEN
            temp_date := i;
            temp_month := '05';
    	END IF;
        
    	stock_date := '2020-' || temp_month || '-' || temp_date;
    	
    	FOR j IN 1..11 LOOP
    		time_band := (j+9) || ':30 ~ ' || (j+10) || ':30';
        	INSERT INTO stock_update_yn_tbl VALUES (stock_date, time_band, 'y');
     	END LOOP;
     	   
     END LOOP;
 
    COMMIT;    
END;
/

EXEC stock_update_yn_proc;