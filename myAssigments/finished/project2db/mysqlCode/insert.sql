USE horseClinic;

-- Sample clinic data
 
INSERT INTO clinic VALUES ('C001','40 Crestfield manor, Glapmire, Cork' ,'0213456788' );
INSERT INTO clinic VALUES ('C002','74 Crawlawn  Avenue, Bouglas, Galway', '0213456789' );
INSERT INTO clinic VALUES ('C003','15 The grove, Central Dublin' ,'0213456710' );

-- Sample Staff data 


INSERT INTO staff VALUES ('S001','C002' ,'Daniel' ,'Soden' ,'0871628448', '14 Marwood Close, Cork', 'Team Member','2004-09-03',35000.56);
INSERT INTO staff VALUES ('S002','C002' ,'David' ,'Jones' ,'0218754356', '12 Marwood Avenue, Cork', 'Manager','1997-10-04',120000.56);
INSERT INTO staff VALUES ('S003','C003' ,'Fawaz' ,'Olatunji' ,'0876549876', '12 Silly Street, Cork', 'Cleaner','2005-05-04',10000.56);

-- Sample Horse data 

INSERT INTO horse VALUES ('H001','O001','Brown','Blitz');
INSERT INTO horse VALUES ('H002','O002','Grey','Lightning');
INSERT INTO horse VALUES ('H003','O001','Black','Shadow');

-- Sample Owner data 

INSERT INTO owner VALUES ('O001' ,'Daniel' ,'Boden' ,'0871338448', '15 Gravewood Lawn, Dublin');
INSERT INTO owner VALUES ('O002' ,'Sean' ,'Horlin' ,'0844428448', '14 Backwood Avenue, Galway');

-- Sample Treatment data 

INSERT INTO treatment VALUES ('T001',1);
INSERT INTO treatment VALUES ('T002',0);

-- Sample Consultation_treatment data

INSERT INTO consultation_treatment VALUES ('T001','C002');
INSERT INTO consultation_treatment VALUES ('T002','C001');

-- Sample Consultation data 

INSERT INTO consultation VALUES ('C002','H002','S001');
INSERT INTO consultation VALUES ('C001','H002','S001');
