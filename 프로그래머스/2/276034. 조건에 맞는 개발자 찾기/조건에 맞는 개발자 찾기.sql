-- 코드를 작성해주세요
select ID, EMAIL, FIRST_NAME, LAST_NAME
from DEVELOPERS
where SKILL_CODE & (
    select A.CODE
    from SKILLCODES A
    where NAME like 'C#'
    ) > 0 
    or
    SKILL_CODE & (
    select A.CODE
    from SKILLCODES A
    where NAME like 'Python'
    ) > 0 
order by ID