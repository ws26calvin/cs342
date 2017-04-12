--Wasiq Sohail
DROP VIEW cpdbv;

CREATE VIEW cpdbv
AS SELECT firstName, LastName, TRUNC(MONTHS_BETWEEN(SYSDATE, birthdate)/12) AS age, birthdate
FROM Person;

--a.

SELECT *
FROM CPDBV
WHERE age <= 56 AND age >= 41;

--4 people show up in the list

--b.
UPDATE Person
SET birthdate = '01-JAN-70'
WHERE firstName = 'Kai';
--Adding birthdate to Kai

SELECT *
FROM CPDBV
WHERE age <= 56 AND age >= 41;

--Kai is in the list now

--c.
INSERT INTO cpdbv VALUES ('A', 'B', 20, '01-JAN-97');

--Error message is returned stating virtual column not allowed here
--In order to insert the values correctly we would need to not add to
--a derived column and also have permission to insert keys as necessary.