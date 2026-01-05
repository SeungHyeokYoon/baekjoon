-- 코드를 입력하세요
SELECT A.REST_ID, A.REST_NAME, A.FOOD_TYPE, A.FAVORITES, A.ADDRESS, ROUND(AVG(B.REVIEW_SCORE), 2) AS SCORE
from REST_INFO A join REST_REVIEW B on A.REST_ID = B.REST_ID
where A.address like '서울%'
group by A.REST_ID
order by score desc, A.favorites desc