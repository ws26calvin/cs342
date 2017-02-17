-- This command file loads a simple movies database, dropping any existing tables
-- with the same names, rebuilding the schema and loading the data fresh.
--
-- CS 342, Spring, 2015
-- kvlinden

-- Drop current database
DROP TABLE Casting;
DROP TABLE Movie;
DROP TABLE Performer;

--This is for emulating an ENUM data type
DROP TABLE Status;

--As far as I can tell, there is no explicit enumerated data type in Oracle SQL, however we can emulate
--its properties by creating a new table, and adding values to it and then using the index and value to
--refer to each other.

--Advantages: All of the advantages of enumerations, it restricts without using a check, except for...
--Disdvantages: Unlike a traditonal enumeration it can be added to and it is more mutable (the index can be
--changed for a value) and it is more trouble and does not seem to add more than a simple check would
--and it needs a table join to show the value rather than the index
CREATE TABLE Status (
	id integer,
	status varchar(6),
	PRIMARY KEY (id)
);

INSERT INTO Status VALUES(1,'star');
INSERT INTO Status VALUES(2,'extra');
INSERT INTO Status VALUES(3,'costar');

-- Create database schema
CREATE TABLE Movie (
	id integer,
	title varchar(70) NOT NULL, 
	year decimal(4), 
	score float,
	votes integer,
	PRIMARY KEY (id),
	CHECK (year > 1900)
	);

CREATE TABLE Performer (
	id integer,
	name varchar(35),
	PRIMARY KEY (id)
	);

CREATE TABLE Casting (
	movieId integer,
	performerId integer,
--	status varchar(6),
	status	NUMBER REFERENCES Status(id),
	FOREIGN KEY (movieId) REFERENCES Movie(Id) ON DELETE CASCADE,
	FOREIGN KEY (performerId) REFERENCES Performer(Id) ON DELETE SET NULL
--	CHECK (status in ('star', 'costar', 'extra'))
	);

-- Load sample data
INSERT INTO Movie VALUES (1,'Star Wars',1977,8.9, 2000);
INSERT INTO Movie VALUES (2,'Blade Runner',1982,8.6, 1500);

INSERT INTO Performer VALUES (1,'Harrison Ford');
INSERT INTO Performer VALUES (2,'Rutger Hauer');
INSERT INTO Performer VALUES (3,'The Mighty Chewbacca');
INSERT INTO Performer VALUES (4,'Rachael');

INSERT INTO Casting VALUES (1,1,1);
INSERT INTO Casting VALUES (1,3,3);
INSERT INTO Casting VALUES (2,1,1);
INSERT INTO Casting VALUES (2,2,2);
INSERT INTO Casting VALUES (2,4,2);