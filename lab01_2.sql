describe dual;
--This lets us see what tables are in DUAL, and we get the table DUMMY which carries information of the type varchar
select * from dual;
--Queries for all the information in DUAL, which we know to be just in a single table
--And we get a single varchar 'X' within DUMMY, so the column contains just one element
--The schema for DUAL is just a single table called DUMMY with one column that contains one row