-- 이미지 업데이트 배치 작업 : 도서 아이디 최솟값(첫번째 값) 
select min(id) from book_tbl;


SELECT name, genre, book_img, author, price, eval_point, book_stock.stand_location
FROM book_tbl, book_stock
WHERE name like '%' || 'C#' || '%'
ORDER BY book_tbl.ID ASC;