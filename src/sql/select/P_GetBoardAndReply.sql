-- 상품 게시판 테이블{BOARD_ID, WRITER_ID, TITLE, CONTENTS, PRICE, CREATED_DATE, STATUS, VIEWS}
-- 상품 게시판에 대한 댓글 테이블{REPLY_ID, BOARD_ID, WRITER_ID, CONTENTS, CREATED_DATE}
-- 이 있을 때, 상품 게시글 중 2022-10월에 작성된 게시글에 대해
-- 다음의 정보들을 조회하라
SELECT B.title, B.board_id, R.reply_id, R.writer_id, R.contents, TO_CHAR(R.created_date, 'YYYY-MM-DD') AS CREATED_DATE
FROM USED_GOODS_BOARD B
         INNER JOIN USED_GOODS_REPLY R
                    ON B.board_id = R.board_id
WHERE TO_CHAR(B.CREATED_DATE, 'YYYY-MM') = '2022-10'
ORDER BY R.CREATED_DATE ASC, B.TITLE ASC;