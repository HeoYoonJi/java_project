--도서 재고 정보 초기화
CREATE OR REPLACE PROCEDURE book_stock_record_gen
IS
v_book_id NUMBER(10);
v_isbn10 CHAR(10);
v_genre VARCHAR2(20);
v_stand_location CHAR(1);
BEGIN
    SELECT MIN(ID) INTO v_book_id FROM BOOK_TBL;
 
    FOR i IN 1..80 LOOP
    
        SELECT isbn10 INTO v_isbn10 FROM BOOK_TBL
        WHERE ID = v_book_id;
        
        SELECT genre INTO v_genre FROM BOOK_TBL
        WHERE ID = v_book_id;
        
         IF  v_genre = 'IT 모바일'  THEN 
            v_stand_location := 'A';
        ELSIF  v_genre = '소설'  THEN
            v_stand_location := 'B';
        ELSIF  v_genre = '자기계발'  THEN
            v_stand_location := 'C';    
        ELSE
            v_stand_location := 'D';
        END IF;
        
        INSERT INTO book_stock VALUES (book_stock_seq.nextval, v_isbn10, 0, 5, v_stand_location);
         
        SELECT MIN(ID) INTO v_book_id FROM BOOK_TBL
        WHERE ID > v_book_id
        ORDER BY ID;
        
     END LOOP;
 
    COMMIT;    
END;
/
 
EXECUTE  book_stock_record_gen;
