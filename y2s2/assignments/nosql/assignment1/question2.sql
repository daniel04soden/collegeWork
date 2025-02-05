CREATE DATABASE firm;

USE firm;

CREATE TABLE Employee_T (
	employeeID int AUTO_INCREMENT PRIMARY KEY,
	employeeName VARCHAR(30),
	contact VARCHAR(30),
	email VARCHAR(30)
);

-- Extra table made to make sense of department as an attribute (Shouldn't affect main table)

CREATE TABLE department (
	departmentID int AUTO_INCREMENT PRIMARY KEY,
	employeeName VARCHAR(30),
	contact VARCHAR(30),
	email VARCHAR(30)
);

CREATE TABLE performance (
	departmentID int,
	employeeID int,
	rank double,
	FOREIGN KEY(employeeID) REFERENCES employee(employeeID),
	FOREIGN KEY(departmentID) REFERENCES department(departmentID),
	PRIMARY KEY(departmentID,employeeID) -- Presume this shouldn't be the key... - Could it,could it not ? We can't have an employee with two diff dept
);

-- Index on employeeID in each table

CREATE INDEX employeeID_idx1
	USING BTREE ON Employee_T(employeeID);

CREATE INDEX employeeID_idx2
	USING BTREE ON performance(employeeID);

-- Index on rank in performance table

CREATE INDEX rank_idx
	USING BTREE ON performance(rank);



SELECT e.employeeID,e.employeeName,p.departmentID,p.rank
FROM e Employee_T JOIN ON performance p.employeeID = e.employeeID
AND p.rank = 1.0
ORDER BY e.employeeName;
