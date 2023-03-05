-- 프로그래머스 sql lv2
-- 재구매가 일어난 상품과 회원 리스트 구하기
-- GROUP BY, HAVING 절 이용하여 풀기
SELECT o.user_id, o.product_id
FROM ONLINE_SALE o
GROUP BY o.user_id, o.product_id
    HAVING count(*) >= 2
ORDER BY o.user_id ASC, o.product_id DESC;