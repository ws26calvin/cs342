--Wasiq Sohail
--Homework 7

DROP VIEW hw7;
DROP MATERIALIZED VIEW hw7m;

CREATE VIEW hw7
AS SELECT employee_id, first_name, last_name, email, hire_date, department_name
FROM Employees
JOIN Departments ON Employees.department_id = Departments.department_id;

--1.

--a.
SELECT *
FROM (SELECT employee_id, first_name, last_name
	FROM hw7
	WHERE department_name = 'Executive'
	ORDER BY hire_date DESC)
WHERE ROWNUM = 1;

SELECT employee_id, first_name, last_name
FROM (SELECT MAX(hire_date) AS newest_date
	FROM hw7
	WHERE department_name = 'Executive') temp1
JOIN (SELECT *
	FROM hw7
	WHERE department_name = 'Executive') temp2
ON temp1.newest_date = temp2.hire_date;

--Both work

--b. 
UPDATE hw7
SET department_name = 'Bean Counting'
WHERE department_name = 'Administration';

--This generates an error because Oracle does not update attributes that lie in
--separate tables outside of specific circumstances

--c. 
UPDATE hw7
SET first_name = 'Manuel'
WHERE first_name = 'Jose Manuel';

--Works

--d.
INSERT INTO hw7 VALUES (12345678, 'A', 'B', 'c@gmail.com', '11-APR-17', 'Sales');


--Does not work for the same reason part b did not work, attributes that are shared
--in multiple tables cannot be affected by changes in a view

--2. 

CREATE MATERIALIZED VIEW
AS SELECT employee_id, first_name, last_name, email, hire_date, department_name
FROM Employees
JOIN Departments ON Employees.department_id = Departments.department_id;

--a.
SELECT *
FROM (SELECT employee_id, first_name, last_name
	FROM hw7m
	WHERE department_name = 'Executive'
	ORDER BY hire_date DESC)
WHERE ROWNUM = 1;

--Works

--b. 
UPDATE hw7m
SET department_name = 'Bean Counting'
WHERE department_name = 'Administration';

--c. 
UPDATE hw7m
SET first_name = 'Manuel'
WHERE first_name = 'Jose Manuel';

--d.
INSERT INTO hw7m VALUES (12345678, 'A', 'B', 'c@gmail.com', '11-APR-17', 'Sales');

--b,c and d all fail because of the attempt to make a statement that would affect 
--a view made with a join, with some exceptions


--3a.

--PI_<employee_id, first_name, last_name, email, hire_date, department_name> (EMPLOYEES|X|_Employees.department_id = Departments.department_id DEPARTMENTS)
--{e.employee_id, e.first_name, e.last_name, e.email, e.hire_date, d.department_name | EMPLOYEES(e) AND DEPARTMENTS(d) AND e.department_name = d.department_name}

--3b. 
--{t.employee_id, t.first_name, t.last_name | hw7(t) AND t.department_name = 'Executive'}