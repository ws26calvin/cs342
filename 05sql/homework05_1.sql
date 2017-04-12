--Wasiq Sohail
--Homework 5

--a.
SELECT first_name, last_name, end_date
FROM Employees
JOIN Job_History ON Employees.employee_id = Job_History.employee_id
WHERE end_date < SYSDATE;

--b.
SELECT employee_id, first_name, last_name, m_id, mfname, mlname
FROM Employees
JOIN (SELECT employee_id m_id, first_name mfname, last_name mlname, hire_date mhiredate, department_id mdeptid
	FROM Employees) M
ON Employees.manager_id = M.m_id
WHERE hire_date > mhiredate AND department_id = mdeptid;

--c
SELECT country_name
FROM Countries
NATURAL JOIN Locations
NATURAL JOIN Departments
WHERE department_id = 80;

SELECT country_name
FROM Countries
WHERE country_id = (SELECT country_id
					FROM Locations
					WHERE location_id = (SELECT location_id
										FROM Departments
										WHERE department_id = 80));
										
--Joins are better because they take up less space and are easier to keep track of
--although operations like update cannot use joins.