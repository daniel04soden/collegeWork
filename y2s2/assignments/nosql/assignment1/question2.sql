DROP DATABASE firm;
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
	rank DECIMAL(5,2),
	FOREIGN KEY(employeeID) REFERENCES Employee_T(employeeID),
	FOREIGN KEY(departmentID) REFERENCES department(departmentID),
	PRIMARY KEY(departmentID,employeeID) -- Presume this shouldn't be the key... - Could it,could it not ? We can't have an employee with two diff dept
);
-- Sample insert statements

-- Insert into Employee_T
INSERT INTO Employee_T (employeeName, contact, email) VALUES
('Alice Smith', '123-456-7890', 'alice.smith@example.com'),
('Bob Johnson', '987-654-3210', 'bob.johnson@example.com'),
('Charlie Brown', '555-123-4567', 'charlie.brown@example.com'),
('David Lee', '111-222-3333', 'david.lee@example.com'),
('Eve Wilson', '444-555-6666', 'eve.wilson@example.com');

-- Insert into department
INSERT INTO department (employeeName, contact, email) VALUES
('Sales', '777-888-9999', 'sales@example.com'),
('Marketing', '222-333-4444', 'marketing@example.com'),
('Engineering', '666-777-8888', 'engineering@example.com');

-- Insert into performance
INSERT INTO performance (employeeID, departmentID, rank) VALUES
(1, 1, 1.0), -- Alice in Sales, rank 1.0
(2, 2, 2.5), -- Bob in Marketing, rank 2.5
(3, 3, 1.0), -- Charlie in Engineering, rank 1.0
(4, 1, 3.0), -- David in Sales, rank 3.0
(5, 2, 1.0), -- Eve in Marketing, rank 1.0
(1, 2, 2.0); -- Alice in Marketing, rank 2.0 (to test multiple departments for one employee)


-- Employee name table

CREATE INDEX employeeName_idx 
	USING BTREE ON Employee_T(employeeName);

-- Index on rank in performance table

CREATE INDEX rank_idx
	USING BTREE ON performance(rank);

SELECT e.employeeID,e.employeeName,p.departmentID,p.rank
FROM e Employee_T JOIN ON performance p.employeeID = e.employeeID
AND p.rank = 1.0
ORDER BY e.employeeName;
