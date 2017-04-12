--Wasiq Sohail

--6.1

--a
SELECT Team.name, Team.mandate, PersonTeam.personID
FROM Team 
JOIN PersonTeam ON Team.name = PersonTeam.teamName;

--To get the leader's name,do one more join from PersonTeam to PersonTeam

--b
SELECT Team.name, Team.mandate, Person.firstname, person.lastname
FROM Team 
JOIN PersonTeam ON Team.name = PersonTeam.teamName
JOIN Person ON PersonTeam.PersonID = Person.ID;
