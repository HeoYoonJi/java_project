select entrance_date  from customer_entrance_tbl
where entrance_time_band = '10:30 ~ 11:30';

SELECT TO_CHAR(entrance_date, 'yyyy-mm-dd hh24:mi:ss') from customer_entrance_tbl
where entrance_time_band = '10:30 ~ 11:30';


select * from customer_entrance_tbl
where  entrance_date = TO_DATE('2020-05-01 22:00:00','yyyy-mm-dd hh24:mi:ss','NLS_DATE_LANGUAGE = Korean')
and to_number(substr(entrance_time_band, 0, 2)) < to_number(substr('13:30 ~ 14:30', 0, 2))
order by entrance_time_band;
        
SELECT TO_DATE('2020-05-01 11:00:00',
               'yyyy-mm-dd hh24:mi:ss',
               'NLS_DATE_LANGUAGE = Korean')
FROM DUAL;


select * from customer_entrance_tbl
where  entrance_date = TO_DATE('2020-05-01 22:00:00','yyyy-mm-dd hh24:mi:ss','NLS_DATE_LANGUAGE = Korean')
and 
order by entrance_time_band;

select 10:30 ~ 11:30

SELECT TO_NUMBER (substr('10:30 ~ 11:30', 0, 2))
FROM DUAL;

select TO_NUMBER('1000.00', '9G9999D99')
FROM DUAL;



select * from customer_entrance_tbl
where entrance_date = #{entranceDate}
and entrance_time_band = #{entranceTimeBand}


select update_yn from stock_update_yn_tbl
where stock_date = '2020-08-05';
and time_band = '10:30 ~ 11:30';

delete stock_update_yn_tbl where stock_date = '2020-08-04';

commit;