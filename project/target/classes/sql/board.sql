CREATE SEQUENCE board_seq
START WITH 1
INCREMENT BY 1
MAXVALUE 99999
NOCYCLE;
 
CREATE TABLE board (
    board_num NUMBER DEFAULT 0,
    board_name VARCHAR2(30) NOT NULL,
    board_pass VARCHAR2(20) NOT NULL,
    board_subject VARCHAR2(100) NOT NULL,
    board_content VARCHAR2(2000) NOT NULL,
    board_file VARCHAR2(100),
    board_re_ref NUMBER NOT NULL,
    board_re_lev NUMBER NOT NULL,
    board_re_seq NUMBER NOT NULL,
    board_readcount NUMBER DEFAULT 0,
    board_date DATE,
    PRIMARY KEY(board_num)
);

-- dummy board data generator
 
DELETE board;
 
CREATE OR REPLACE PROCEDURE spring_board_dummy_gen_proc
IS
BEGIN
 
    FOR i IN 1..100 LOOP
        INSERT INTO board VALUES
        (
            board_seq.nextval,
            '글쓴이' || i,
            '123456789',
            '글쓴이의 글 제목' || i,
            '글쓴이의 글 내용' || i,
            '',
            board_seq.nextval,
            0,
            0,
            0,
            sysdate
        );
     END LOOP;
 
    COMMIT;    
END;
/
 
EXECUTE spring_board_dummy_gen_proc;