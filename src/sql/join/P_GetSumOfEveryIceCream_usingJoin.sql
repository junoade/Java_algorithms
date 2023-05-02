-- 일반적인 풀이로는
-- 한 쪽 테이블에서 다른 쪽 테이블과 조인하면서 데이터가 중복될 수 있다.
-- 예를 들어 아래와 같이 3100이 두 번 등장함
-- 1. (109, ‘strawberry’, 3100, 209, ‘strawberry’, 220)
-- 2. (109, ‘strawberry’, 3100, 109, ‘strawberry’, 520)
-- 따라서 한 쪽 price의 최댓값 + 다른 쪽 컬럼 값들을 더하도록 작성
SELECT T1.FLAVOR
FROM JULY T1,
     (SELECT T.FLAVOR, SUM(T.TOTAL_ORDER) AS TOTAL_ORDER
      FROM FIRST_HALF T
      GROUP BY T.FLAVOR) T2
WHERE T1.FLAVOR = T2.FLAVOR
GROUP BY T1.FLAVOR
ORDER BY SUM(T1.total_order) + MAX(T2.total_order) DESC
    FETCH FIRST 3 ROW ONLY;