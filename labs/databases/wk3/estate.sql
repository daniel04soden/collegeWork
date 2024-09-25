-- SQL code for estateagent databases

CREATE DATABASE estateagent;

CREATE TABLE clients (
 clientNo INT,
 fName VARCHAR(20),
 lName VARCHAR(20),
 telNo VARCHAR(11),
  PRIMARY KEY(clientNo),
);

CREATE TABLE properties (
  propertyNo INT,
  street VARCHAR(20),
  city VARCHAR(15),
  postcode VARCHAR(7),
  buildingType CHAR(10),
  PRIMARY KEY(propertyNo),
);

CREATE TABLE viewings (
  viewDate DATE,
  clientNo INT,
  propertyNo INT,
  clientComment VARCHAR(50),
  PRIMARY KEY(clientNo,propertyNo)
);

CREATE TABLE owners (
  ownerNo INT,
  propertyNo INT,
  Oname VARCHAR(20),
  Ocity VARCHAR(15),
  PRIMARY KEY(ownerNo),
FOREIGN KEY(propertyNo) REFERENCES properties(propertyNo)
);
