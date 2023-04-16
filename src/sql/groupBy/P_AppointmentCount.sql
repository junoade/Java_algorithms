-- 프로그래머스 진료과 총 예약횟수, oracle
SELECT MCDP_CD AS "진료과코드" -- 주의 ""로하기
        COUNT(*) AS "5월예약건수"
FROM APPOINTMENT
WHERE TO_CHAR(A.APNT_YMD, 'YYYY-MM') = '2022-05'
GROUP BY A.MCDP_CD
ORDER BY "5월예약건수", "진료과코드";