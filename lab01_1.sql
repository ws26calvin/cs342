DESCRIBE DEPARTMENTS;
SELECT COUNT(EMPLOYEE_ID) FROM EMPLOYEES;
SELECT * FROM EMPLOYEES WHERE SALARY > 15000 AND PHONE_NUMBER NOT LIKE '515_' AND HIRE_DATE BETWEEN '31-DEC-01' AND '1-JAN-05';
SELECT FIRST_NAME || ' ' || LAST_NAME FROM EMPLOYEES WHERE DEPARTMENT_id = 100 ORDER BY LAST_NAME;
select locations.city, locations.state_province, countries.country_name
from countries
inner join locations on locations.country_id = countries.country_id
inner join regions on regions.region_id = countries.region_id
where region_name = 'Asia';
select * from locations where state_province is null;