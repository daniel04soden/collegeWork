USE horseClinic;

CREATE TABLE staff (
  staffNo VARCHAR(20);
  staffNo VARCHAR(20);
  fName VARCHAR(15);
  lName VARCHAR(15);
  address VARCHAR(30);
  DOB DATE;
  salary DOUBLE(10,2);

  PRIMARY KEY (staffNo);

  Foreign Key (clinicNo) REFERENCES (clinic)
);

CREATE TABLE clinic (
  clinicNo VARCHAR(20);
  
)
