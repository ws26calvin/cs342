-- Starter code for lab 3.
--
-- CS 342, Spring, 2017
-- kvlinden

drop table Person_Team;
drop table Person;
drop table HouseHold;
drop table Team;
drop table HomeGroup;

create table HouseHold(
	ID integer PRIMARY KEY,
	street varchar(30),
	city varchar(30),
	state varchar(2),
	zipcode char(5),
	phoneNumber char(12)
	);

create table Person (
	ID integer PRIMARY KEY,
	title varchar(4),
	firstName varchar(15),
	lastName varchar(15),
	membershipStatus char(1) CHECK (membershipStatus IN ('m', 'a', 'c')),
	--Since each person can have 0 or 1 mentors, there doesn't need to be a new table
	mentor_id integer,
	--Since each person can be in a maximum of one household, no need for another table
	household_id integer,
	household_role char(20),
	homegroup_id integer,
	--If the mentor leaves the church, just set the field as NULL
	FOREIGN KEY (mentor_id) REFERENCES Person(ID) ON DELETE SET NULL,
	--If a person leaves home for ehatever reason, they can still be a member of the church
	FOREIGN KEY (household_id) REFERENCES HouseHold(ID) ON DELETE SET NULL,
	--If there is no longer a homegroup, just set the homegroup_id to null
	FOREIGN KEY (homegroup_id) REFERENCES HomeGroup(ID) ON DELETE SET NULL,
	);
	
create table Team (
	ID integer PRIMARY KEY,
	tname varchar(50)
	);

--Because Person and Team have a many-many relationship, an intermeidary relation is necessary
create table Person_Team (
	person_id integer,
	team_id integer,
	begin_date date,
	end_date date,
	role varchar(20)
	);
	
crete table HomeGroup (
	ID integer PRIMARY KEY,
	hgname varchar(40)
	);

INSERT INTO Household VALUES (0,'2347 Oxford Dr. SE','Grand Rapids','MI','49506','616-243-5680');

INSERT INTO Person VALUES (0,'mr.','Keith','VanderLinden','m',0,0,'member',0);
INSERT INTO Person VALUES (1,'ms.','Brenda','VanderLinden','m',0,0,'member',0);

INSERT INTO Person_Team VALUES(0,0,NULL,NULL,'member');
