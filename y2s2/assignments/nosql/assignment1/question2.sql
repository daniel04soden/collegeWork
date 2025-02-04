CREATE DATABASE firm;

CREATE TABLE employee (
	employeeID int AUTO_INCREMENT PRIMARY KEY,
	employeeName VARCHAR(30),
	contact VARCHAR(30),
	email VARCHAR(30)
);

CREATE TABLE performance (
	departmentID int AUTO_INCREMENT PRIMARY KEY,
	employeeID int,
	rank VARCHAR(5),
	FOREIGN KEY(employeeID) REFERENCES employee(employeeID)
);

