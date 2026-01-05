-- 코드를 입력하세요
SELECT flavor
from FIRST_HALF F join ICECREAM_INFO I using (flavor)
where F.TOTAL_ORDER > 3000 and I.INGREDIENT_TYPE = 'fruit_based'
order by F.TOTAL_ORDER desc