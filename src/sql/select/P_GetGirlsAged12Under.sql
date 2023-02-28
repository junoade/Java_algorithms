-- oracle based sql, lv1
-- https://school.programmers.co.kr/learn/courses/30/lessons/132201?language=oracle
SELECT T.PT_NAME, T.PT_NO, T.GEND_CD, T.AGE, NVL(TLNO, 'NONE') AS TLNO
from PATIENT T
WHERE T.GEND_CD = 'W' AND T.AGE <= 12
ORDER BY T.AGE DESC, T.PT_NAME ASC;