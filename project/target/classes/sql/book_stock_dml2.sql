update book_stock set quantity = 5;
commit;

--id=263
--name=ȥ�� �����ϴ� ù ���α׷��� with ���̽� 1:1 �����ϵ� ���� ���ʺ� �ڵ� �Թ��� 
--genre=IT �����
--bookImg=90617738.jpg
--isbn10=null
--author=������ 
--quantity=0
--limit=5
--standLocation=A
select bs.id, bt.book_img, bt.genre, bt.name, bt.author, bt.isbn10,
bs.quantity, bs.limit, bs.stand_location
from book_stock bs, book_tbl
bt
where bs.isbn10 = bt.isbn10
order by bt.genre;


--���� ��� ������Ʈ
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
    
    