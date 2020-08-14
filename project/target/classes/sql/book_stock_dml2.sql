update book_stock set quantity = 5;
commit;

--id=263
--name=혼자 공부하는 첫 프로그래밍 with 파이썬 1:1 과외하듯 배우는 왕초보 코딩 입문서 
--genre=IT 모바일
--bookImg=90617738.jpg
--isbn10=null
--author=문현일 
--quantity=0
--limit=5
--standLocation=A
select bs.id, bt.book_img, bt.genre, bt.name, bt.author, bt.isbn10,
bs.quantity, bs.limit, bs.stand_location
from book_stock bs, book_tbl
bt
where bs.isbn10 = bt.isbn10
order by bt.genre;


--도서 재고 업데이트
update book_stock set quantity = 5
where isbn10 =(
    select isbn10 from book_tbl
    where isbn13 = 9791196730031
    );
    
select quantity from book_stock
where isbn10 =(
    select isbn10 from book_tbl
    where isbn13 = 9791196730031
    );
    
    