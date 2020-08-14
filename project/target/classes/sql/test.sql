-- 이미지 업데이트 배치 작업 : 도서 아이디 최솟값(첫번째 값) 
select min(id) from book_tbl;


SELECT name, genre, book_img, author, price, eval_point FROM book_tbl
WHERE name like '%' || 'C#' || '%'
ORDER BY ID ASC;