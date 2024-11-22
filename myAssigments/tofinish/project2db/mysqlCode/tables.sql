CREATE TABLE clinic (
  clinicNo VARCHAR(20) PRIMARY KEY,
  clinicAddress VARCHAR(30),
  telNo VARCHAR(15),
);

CREATE TABLE staff (
  staffNo VARCHAR(20) NOT NULL PRIMARY KEY ,
  clinicNo VARCHAR(20) NOT NULL,
  fName VARCHAR(15),
  lName VARCHAR(15),
  staffAddress VARCHAR(30),
  position VARCHAR(20) NOT NULL,
  DOB DATE NOT NULL, -- Date Of Birth
  salary DOUBLE(10,2) NOT NULL,

  FOREIGN KEY (clinicNo) REFERENCES clinic(clinicNo)
);


CREATE TABLE owner (
  ownerNo VARCHAR(20) PRIMARY KEY NOT NULL,
  fName VARCHAR(15),
  lName VARCHAR(15),
  telNo VARCHAR(15)
);

CREATE TABLE horse (
  horseNo VARCHAR(20) PRIMARY KEY NOT NULL,
  ownerNo VARCHAR(20) NOT NULL,
	clinicNo VARCHAR(20)NOT NULL,
  telNo VARCHAR(15),
  color VARCHAR(20),
  horseName VARCHAR(20),
  Foreign Key (ownerNo) REFERENCES owner(ownerNo),
  Foreign Key (clinicNo) REFERENCES clinic(clinicNo)
);

CREATE TABLE consultation(
  consultNo VARCHAR(20) PRIMARY KEY,
  horseNo VARCHAR(20),
  staffNo VARCHAR(20),
  Foreign Key (horseNo) REFERENCES horse(horseNo),
  Foreign Key (staffNo) REFERENCES staff(staffNo)
);

CREATE TABLE treatment(
  treatmentNo VARCHAR(20) PRIMARY KEY,
  treatmentSuccessful BOOLEAN
);

CREATE TABLE consultation_treatment(
  consultNo VARCHAR(20) PRIMARY KEY,
  treatmentNo VARCHAR(20) PRIMARY KEY,
  Foreign Key (consultNo) REFERENCES consultation(consultNo)
  Foreign Key (treatmentNo) REFERENCES treatment(treatmentNo)
);


