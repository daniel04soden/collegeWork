USE horseClinic;

CREATE TABLE IF NOT EXISTS staff (
  VARCHAR(20) UNIQUE staffNo,
  VARCHAR(20) clinicNo,
  VARCHAR(15) fName,
  VARCHAR(15) lName,
  VARCHAR(30) address,
  VARCHAR(20) telNo,
  DOUBLE(10,2) salary,
  DATE DOB,
  PRIMARY KEY staffNo,
  Foreign Key (clinicNo) REFERENCES (clinic)
);


CREATE TABLE IF NOT EXISTS clinic (
    VARCHAR(20) UNIQUE clinicNo,
  VARCHAR(20) staffNo,
  VARCHAR(30) address,
  VARCHAR(20) telNo,
  PRIMARY KEY clinicNo,
  Foreign Key (staffNo) REFERENCES (staff)
);


CREATE TABLE IF NOT EXISTS owner (
  VARCHAR(20) UNIQUE ownerNo,
  INT(3) qtyHorses,
  VARCHAR(15) fName,
  VARCHAR(15) lName,
  VARCHAR(30) address,
  VARCHAR(20) telNo,
  PRIMARY KEY ownerNo
);


CREATE TABLE IF NOT EXISTS horse (
  VARCHAR(20) UNIQUE horseID,
  VARCHAR(20) ownerNo,
  VARCHAR(20) clinicNo,
  VARCHAR(10) colour,
  VARCHAR(15) horseName,
  PRIMARY KEY horseID,
  Foreign Key (ownerNo) REFERENCES (owner),
  Foreign Key (clinicNo) REFERENCES (clinic)
);


CREATE TABLE IF NOT EXISTS consultation (
  VARCHAR(20) consultNo,
  VARCHAR(20) horseNo,
  PRIMARY KEY consultNo,
  Foreign Key (horseNo) REFERENCES (horse)
);

CREATE TABLE IF NOT EXISTS treatment (
  VARCHAR(20) treatmentNo,
  VARCHAR(20) consultNo,
  PRIMARY KEY treatmentNo,
  Foreign Key (consultNo) REFERENCES (consultation)
);
