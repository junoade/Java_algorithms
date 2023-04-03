-- 프로그래머스 카테고리별 도서 판매량 집계하기
-- LV3, oracle
-- TO_CHAR 이용해서 DATE 타입의 컬럼을 'YYYY-MM'으로 비교할 수 있게 변환하기
SELECT category, sum(sales) as total_sales
FROM book_sales s
         INNER JOIN book b
                    ON s.book_id = b.book_id
WHERE TO_CHAR(s.sales_date, 'YYYY-MM') = '2022-01'
GROUP BY category
ORDER BY category;