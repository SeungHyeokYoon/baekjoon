-- 코드를 입력하세요
SELECT user_id, product_id
from online_sale
GROUP BY USER_ID, PRODUCT_ID
HAVING COUNT(*) >= 2
order by user_id, product_id desc