--Wasiq Sohail
--Homework 6

--a
SELECT manager_id, mfname first_name, mlname last_name, enum number_of_employees
FROM (SELECT Employees.manager_id, COUNT(Employees.manager_id) AS enum
	FROM Employees
	GROUP BY manager_id
	ORDER BY enum DESC) e
JOIN (SELECT employee_id m_id, first_name mfname, last_name mlname
FROM Employees) M
ON e.manager_id = M.m_id
WHERE ROWNUM <= 10;

--b
SELECT department_name, employees, total_salary
FROM (SELECT department_id, COUNT(employee_id) AS employees, SUM(salary) AS total_salary
	FROM Employees
	GROUP BY department_id) temp1
JOIN (SELECT department_name, department_id, country_name
	FROM Departments
	NATURAL JOIN Locations
	NATURAL JOIN Countries) temp2
ON temp1.department_id = temp2.department_id
WHERE employees < 100 AND country_name != 'United States of America';

--c
SELECT department_name, first_name, last_name, job_title
FROM Departments
LEFT JOIN Employees ON Departments.manager_id = Employees.employee_id
LEFT JOIN Jobs ON Employees.job_id  = Jobs.job_id;

--The left joins ensure that every department is preserved throughout the query

--d
SELECT department_name, total_salary
FROM (SELECT department_id, SUM(salary) AS total_salary
	FROM Employees
	GROUP BY department_id) temp1
RIGHT JOIN Departments ON temp1.department_id = Departments.department_id
ORDER BY total_salary DESC;

--The right join here assures that every department is included in the subsequent
--table, even including the departments with no salary