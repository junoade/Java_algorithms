-- 식당의 정보를 담은 REST_INFO 테이블에서
-- 음식종류별로 즐겨찾기수가 가장 많은 식당의 음식 종류, ID, 식당 이름, 즐겨찾기 수를 조회하는 SQL 작성하라
-- 풀이 1) GROUP BY로 먼저 음식 종류로 묶고, 가장 큰 즐겨찾기 수를 조회함
-- 풀이 2) REST_INFO 테이블과 음식 종류, 즐겨찾기 수를 기준으로 SELF JOIN 해서 특정 행을 결정함
-- 풀이 3) ORDER BY를 통해 문제에서 제시하는 대로 정렬 후 반환
SELECT R3.food_type, R1.rest_id, R1.rest_name, R3.favorites
FROM REST_INFO R1, ( SELECT R2.food_type, MAX(R2.favorites) AS favorites
                     FROM REST_INFO R2
                     GROUP BY R2.food_type) R3
WHERE R3.food_type = R1.food_type and R3.favorites = R1.favorites
ORDER BY R3.food_type desc;