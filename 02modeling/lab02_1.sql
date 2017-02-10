--Wasiq Sohail
--2/10/17
--CS 342

@movies.sql

--2.1ai
INSERT INTO Movie VALUES (1,'Star Trek',1977,8.9);
--Error that says unique constraint violated

--2.1aii
INSERT INTO Movie VALUES (null,'Star Trek',1977,8.9);
--Error that says cannot insert NULL into the field

--2.1aii
INSERT INTO Movie VALUES (null,'Star Trek',1977,8.9);
--Error that says cannot insert NULL into the field

--2.1aiii
INSERT INTO Movie VALUES (3,'Star Trek',700,8.9);
--Error that says check constraint violated

--2.1aiv
INSERT INTO Movie VALUES (3,'Star Trek','nineteen seventy-seven',8.9);
--Error that says invalid number

--2.1av
INSERT INTO Movie VALUES (3,'Star Trek',1977,-8.9);
--No issue with storing the value -8.9 as the score, it only has to be a valid float value


--2.1bi
INSERT INTO Casting VALUES (NULL,1,'star');
--No issue with storing the data
SELECT * FROM Casting;
--Shows us in the last row that movieid is blank, but there is a performerid and status are valid

--2.1bii
INSERT INTO Casting VALUES (5,1,'star');
--Error states integrity constraint violated because of no parent key found

--2.1biii
INSERT INTO Performer VALUES (5,'sadf');
--No issue with storing the information or displaying the data
SELECT * FROM Performer;

--2.1ci
DELETE FROM Movie where id = 1;
SELECT * FROM Movie;
--No issue with the deletion from Movie
SELECT * FROM Casting;
--All of the Castings that used the Star Wars record have also been deleted

--2.1cii
DELETE FROM Casting where movieid = 2; and performerid = 1;
SELECT * FROM Casting;
--The record has been removed from the table
SELECT * FROM Movie;
--No change
SELECT * FROM Performer;
--No change

--2.1ciii
UPDATE Movie SET id = 9 where id = 2;
--Error found stating integrity constraint voilated because a child record is found

--Having something like on update cascade seems like it cause a lot of havoc when used improperly on 
--a database with multiple dependancies in unpredictable ways and as the link points out, primary keys 
--should never be changing in the first place. So while it might be useful once in a while, it is probably
--better to take the more careful route in order to preserve the database's internal relationships,