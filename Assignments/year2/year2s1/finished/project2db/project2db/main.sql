CREATE DATABASE horseClinic;

USE horseClinic;


CREATE TABLE clinic (
  clinicNo VARCHAR(20) PRIMARY KEY,
  clinicAddress VARCHAR(50),
  telNo VARCHAR(15)
);

CREATE TABLE staff (
  staffNo VARCHAR(20) NOT NULL PRIMARY KEY,
  clinicNo VARCHAR(20) NOT NULL,
  fName VARCHAR(15),
  lName VARCHAR(15),
  staffAddress VARCHAR(50),
  position VARCHAR(20) NOT NULL,
  DOB DATE NOT NULL, -- Date Of Birth
  salary DOUBLE(10,2) NOT NULL,

  FOREIGN KEY (clinicNo) REFERENCES clinic(clinicNo)
);

CREATE TABLE owner (
  ownerNo VARCHAR(20) PRIMARY KEY NOT NULL,
  fName VARCHAR(15),
  lName VARCHAR(15),
  telNo VARCHAR(15),
	address VARCHAR(50)
);

CREATE TABLE horse (
  horseNo VARCHAR(20) PRIMARY KEY NOT NULL,
  ownerNo VARCHAR(20) NOT NULL,
  color VARCHAR(20),
  horseName VARCHAR(20),
  Foreign Key (ownerNo) REFERENCES owner(ownerNo)
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

CREATE TABLE consultation_treatment (
    consultNo VARCHAR(20),
    treatmentNo VARCHAR(20),
    PRIMARY KEY (consultNo, treatmentNo),
    FOREIGN KEY (treatmentNo) REFERENCES treatment(treatmentNo),
    FOREIGN KEY (consultNo) REFERENCES consultation(consultNo)
);

-- Sample clinic data

INSERT INTO clinic VALUES ('C001','40 Crestfield manor, Glapmire, Cork' ,'0213456788' );
INSERT INTO clinic VALUES ('C002','74 Crawlawn  Avenue, Bouglas, Galway', '0213456789' );
INSERT INTO clinic VALUES ('C003','15 The grove, Central Dublin' ,'0213456710' );

-- Sample Staff data


INSERT INTO staff VALUES ('S001','C002' ,'Daniel' ,'Soden' , '14 Marwood Close, Cork', 'Team Member','2004-09-03',35000.56);
INSERT INTO staff VALUES ('S002','C002' ,'David' ,'Jones' ,'12 Marwood Avenue, Cork', 'Manager','1997-10-04',120000.56);
INSERT INTO staff VALUES ('S003','C003' ,'Fawaz' ,'Olatunji' , '12 Silly Street, Cork', 'Cleaner','2005-05-04',10000.56);

-- Sample Owner data

INSERT INTO owner VALUES ('O001' ,'Daniel' ,'Boden' ,'0871338448', '15 Gravewood Lawn, Dublin');
INSERT INTO owner VALUES ('O002' ,'Sean' ,'Horlin' ,'0844428448', '14 Backwood Avenue, Galway');

-- Sample Horse data

INSERT INTO horse VALUES ('H001','O002','Brown','Blitz');
INSERT INTO horse VALUES ('H002','O001','Grey','Lightning');


-- Sample Treatment data

INSERT INTO treatment VALUES ('T001',1);
INSERT INTO treatment VALUES ('T002',0);

-- Sample Consultation data

INSERT INTO consultation VALUES ('C002','H002','S001');
INSERT INTO consultation VALUES ('C001','H002','S001');

-- Sample Consultation_treatment data

INSERT INTO consultation_treatment VALUES ('C001','T001');
INSERT INTO consultation_treatment VALUES ('C002','T002');

