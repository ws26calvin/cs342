--Wasiq Sohail
--Lab 5


--5.1a
SELECT *
FROM Person, Household;
--There are 128 records, which is the number of person records (16) multiplied by the number of household records (8)

--5.1b
SELECT birthdate
FROM Person
ORDER BY TO_CHAR(birthdate, 'DDD') ASC;


--5.2a
SELECT firstName, lastName
FROM 
	(SELECT * 
	FROM Person 
	WHERE birthdate IS NOT NULL
	ORDER BY birthdate DESC)
WHERE ROWNUM = 1;

--MIN(TRUNC(MONTHS_BETWEEN(SYSDATE, birthdate))/12)