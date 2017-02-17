--Wasiq Sohail
--2/17/17
--CS 342

--Clean up previous sequence
DROP SEQUENCE seq;

@movies.sql

--Sequence here:
CREATE SEQUENCE seq
--Adding after what is already in movies.sql, but if this was used in movies.sql it would start at 1
START WITH 3
INCREMENT BY 1;
	
-- Load sample data
INSERT INTO Movie VALUES (seq.nextval,'Star Trek',1977,8.9, 2000);
INSERT INTO Movie VALUES (seq.nextval,'The Matrix',1982,8.6, 1500);

--a.
--I would need a seperate sequence for Performer because it would need to increment correctly for its
--primary keys, if the tow relations shared the same sequence, one would be skipping whatever integer
--the other relation received

--b.
--The biggest problem with the sequence that I can think of is that it is not useful in a multiuser
--scenario as the sequence is only being kept track of on a single machine. Additionally, The lack of
--a record of what id is attached to what movie could be problematic if the table is somehow misentered.