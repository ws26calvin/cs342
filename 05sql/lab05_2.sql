--Wasiq Sohail

--5.2a
SELECT firstName, lastName
FROM 
	(SELECT * 
	FROM Person 
	WHERE birthdate IS NOT NULL
	ORDER BY birthdate DESC)
WHERE ROWNUM = 1;

--5.2b
SELECT id, firstName, lastName
FROM (Person NATURAL JOIN
	(SELECT firstName
	FROM Person
	GROUP BY firstName
	HAVING COUNT(firstName) > 1))
ORDER BY id;

--Nothing unusual occurs if a name appears 3 times

--5.2c
SELECT firstname, lastName
FROM Person
WHERE
	Person.id in (SELECT personID
	FROM PersonTeam
	WHERE teamName = 'Music')
	AND homeGroupID != 0;