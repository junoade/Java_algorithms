-- price의 값이 최댓값인 행의 정보를 출력하기
SELECT *
FROM food_product f
WHERE f.price IN (
    SELECT max(price)
    FROM food_product
);