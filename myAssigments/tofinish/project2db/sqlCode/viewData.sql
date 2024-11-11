-- Standard data selection


SELECT * FROM clinic;

SELECT * FROM staff;

SELECT * FROM horses;

SELECT * FROM owner;

SELECT * FROM consultation;

SELECT * FROM treatment;

-- Joined coherent data selection

-- Clinic and horse information

SELECT c.clinicNo,h.horseID,c.address,h.horseName,h.colour FROM clinic 
JOIN horse ON h.clinicNo=c.clinicNo;

-- Owner to horse info 

SELECT o.ownerNo,h.horseID,CONCAT(o.fName,o.lName) as owName, h.colour FROM owner o 
JOIN horse on h.ownerNo=o.ownerNo;
