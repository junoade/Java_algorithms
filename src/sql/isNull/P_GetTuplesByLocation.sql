-- 프로그래머스 SQL IS NULL, 경기도에 위치한 식품창고 목록 출력하기
-- 경기도에 위치한,
-- freezer_yn이 NULL인 경우 'N'으로 대체
-- 창고 id로 정렬
SELECT f.warehouse_id, f.warehouse_name, f.address, NVL(f.freezer_yn, 'N')
FROM FOOD_WAREHOUSE f
WHERE f.address like '경기도%'
ORDER BY f.warehouse_id;