-- P_SQL 주문량이 많은 아이스크림들 조회하기
-- LV4 조인
-- UNION ALL을 이용해서 조인 조건이 조금 복잡한 문제에 대해
-- 서로 같은 타입의 컬럼을 갖는 두 테이블을 합침
-- 그리고 FLAVOR로 그룹핑 후 가격별로 내림차순 정렬 후
-- 판매 순위 3위까지 출력
SELECT FLAVOR
FROM
    (SELECT T1.SHIPMENT_ID, T1.FLAVOR, T1.TOTAL_ORDER
     FROM FIRST_HALF T1
     UNION ALL
     SELECT T2.SHIPMENT_ID, T2.FLAVOR, T2.TOTAL_ORDER
     FROM JULY T2
    )
GROUP BY FLAVOR
ORDER BY SUM(TOTAL_ORDER) DESC
    FETCH FIRST 3 ROWS ONLY;
-- 오라클에선 limit이 없다