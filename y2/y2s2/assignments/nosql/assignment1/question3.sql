-- Crating our database (came up with name given it is not provided)
CREATE DATABASE schoolDB;

-- Use db so when executed via cli, it is seamless

use schoolDB;

-- Creating student table and ommitting email attribute

CREATE TABLE students (
  studentID INT AUTO_INCREMENT PRIMARY KEY,
  firstName VARCHAR(30),
  lastName VARCHAR(30),
  DOB DATE,
  enrollDate DATE
);

-- Indivual insert statements to permit auto increment 
INSERT INTO students (firstName, lastName,DOB,enrollDate)
VALUES('John', 'Doe', '2000-05-15', '2018-09-01'); 

INSERT INTO students (firstName, lastName,DOB,enrollDate)
VALUES('Jane', 'Smith', '1999-07-20', '2017-09-01');

INSERT INTO students (firstName, lastName,DOB,enrollDate)
VALUES('Michael', 'Johnson', '2001-03-10', '2019-09-01');

INSERT INTO students (firstName, lastName,DOB,enrollDate)
VALUES('Emily', 'Davis', '2002-11-25', '2020-09-01');
  
INSERT INTO students (firstName, lastName,DOB,enrollDate)
VALUES ('William', 'Brown', '2000-08-30', '2018-09-01');

INSERT INTO students (firstName, lastName,DOB,enrollDate)
VALUES ('Olivia', 'Wilson', '2001-12-12', '2019-09-01');

-- Adding email as an attribute, How to adjust og values...?

ALTER TABLE students
  ADD COLUMN email VARCHAR(30) UNIQUE AFTER lastName;

-- New value with email added in.
INSERT INTO students (firstName, lastName,email,DOB,enrollDate)
VALUES ('Daniel', 'Soden','dsoden09@gmail.com', '2001-12-12', '2019-09-01');

-- Can't use insert into, must use update
UPDATE students
  SET email = 'oliviaWilson@yahoo.com'
  WHERE studentID = 6;

-- Transactions

START TRANSACTION;

INSERT INTO students (firstName, lastName,email,DOB,enrollDate)
VALUES ('Sean', 'Soden','seansoden1@gmail.com', '2001-12-12', '2019-09-01');

-- Notice if you make another connection then there is none of this data present
-- Until you commit

COMMIT;


	-- Rollback

START TRANSACTION;

INSERT INTO students (firstName, lastName,email,DOB,enrollDate)
VALUES ('Aoife', 'Soden','aoifesoden@gmail.com', '2007-12-12', '2026-09-01');

UPDATE students
	SET lastName='Doe'
	WHERE firstName='Aoife';

-- Notice if you make another connection then there is none of this data present
-- Until you commit
--  And now after the rollback and commit we see that there is not trace of the insertion
-- How can this be used in practicality...
ROLLBACK;
COMMIT;

-- Save state testing

START TRANSACTION;

SAVEPOINT testpoint;

INSERT INTO students (firstName, lastName,email,DOB,enrollDate)
VALUES ('David', 'Soden','davidsoden45@gmail.com', '1965-10-03', '1977-09-01');

SELECT * from students;

ROLLBACK TO SAVEPOINT testpoint;

SELECT * from students;

COMMIT;
