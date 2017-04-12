--Wasiq Sohail

--a
SELECT TRUNC(AVG(MONTHS_BETWEEN(SYSDATE, birthdate))/12) AS average_age
FROM Person;

--It does group because it excludes any NULL values if they exist


--b & c
SELECT HouseHold.id, HouseHold.phoneNumber, Temp.members
FROM HouseHold 
JOIN 
	(SELECT HouseHold.id, COUNT(Person.householdid) AS members
	FROM HouseHold
	JOIN Person ON HouseHold.id = Person.householdid
	HAVING COUNT(Person.householdid) > 1
	GROUP BY HouseHold.id) Temp ON HouseHold.id = Temp.id
ORDER BY Temp.members DESC, HouseHold.id;
