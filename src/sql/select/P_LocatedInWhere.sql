-- 강원도에 위치한 생산공장 목록 출력하기
-- FOOD_FACTORY(FACTORY_ID, FACTORY_NAME, ADDRESS, TLNO); 가 있을 때
-- postgresql은 POSITION('target' in 'source') : 정수 반환 함수 쓸 수 있을 듯
SELECT FACTORY_ID, FACTORY_NAME, ADDRESS
FROM FOOD_FACTORY
WHERE INSTR(ADDRESS, '강원도', 1, 1) != 0
ORDER BY FACTORY_ID;