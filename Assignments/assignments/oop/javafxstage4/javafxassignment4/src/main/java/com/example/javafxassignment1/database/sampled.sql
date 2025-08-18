-- File simply here to document creation of sql statements/input sample data as seen below

-- Sample Customer data
INSERT INTO Customer
    (customerID, email, name, age, amount)
VALUES (1, 'siobhan.murphy@example.com', 'Siobhán Murphy', 41, 112.30);

INSERT INTO Customer
    (customerID, email, name, age, amount)
VALUES (2, 'liam.oconnor@example.com', 'Liam Murphy', 29, 195.85);

INSERT INTO Customer
    (customerID, email, name, age, amount)
VALUES (3, 'aoife.reilly@example.com', 'Aoife Reilly', 35, 88.55);

INSERT INTO Customer
    (customerID, email, name, age, amount)
VALUES (4, 'eoin.doyle@example.com', 'Eoin Doyle', 48, 250.15);

INSERT INTO Customer
    (customerID, email, name, age, amount)
VALUES (5, 'niamh.byrne@example.com', 'Niamh Byrne', 26, 135.65);

INSERT INTO Customer
    (customerID, email, name, age, amount)
VALUES (6, 'ciaran.mccarthy@example.com', 'Ciarán McCarthy', 55, 320.40);
-- Sample product data

-- Sample Customer data
INSERT INTO Product(productID, name, stock, price) VALUES (1, 'NVIDIA GeForce RTX 4080', 15, 1199.00);
INSERT INTO Product(productID, name, stock, price) VALUES (2, 'AMD Radeon RX 7900 XTX', 12, 999.50);
INSERT INTO Product(productID, name, stock, price) VALUES (3, 'Intel Core i9-14900K', 25, 589.99);
INSERT INTO Product(productID, name, stock, price) VALUES (4, 'AMD Ryzen 9 7950X3D', 18, 699.00);
INSERT INTO Product(productID, name, stock, price) VALUES (5, 'Apple MacBook Air M2', 30, 1099.00);
INSERT INTO Product(productID, name, stock, price) VALUES (6, 'Microsoft Surface Laptop Go 3 (ARM)', 22, 799.99);
INSERT INTO Product(productID, name, stock, price) VALUES (7, 'NVIDIA GeForce RTX 4070', 28, 549.00);
INSERT INTO Product(productID, name, stock, price) VALUES (8, 'AMD Ryzen 7 7700X', 35, 329.00);
INSERT INTO Product(productID, name, stock, price) VALUES (9, 'Qualcomm Snapdragon 8cx Gen 3 Laptop Kit', 10, 850.00);
INSERT INTO Product(productID, name, stock, price) VALUES (10, 'Intel Core i7-14700K', 21, 409.99);
INSERT INTO Product(productID, name, stock, price) VALUES (11, 'AMD Radeon RX 7800 XT', 17, 499.00);
INSERT INTO Product(productID, name, stock, price) VALUES (12, 'Apple iPad Pro 12.9-inch (M2)', 25, 1299.00);
INSERT INTO Product(productID, name, stock, price) VALUES (13, 'Apple Macbook Air M4 512gb 16gb (Daniels Machine)', 1, 1499.00);

-- Creating customers table 

                    CREATE TABLE IF NOT EXISTS Customer(
                           customerID INTEGER PRIMARY KEY, -- Autoincrement automatically via og id situation
                           email VARCHAR(25),
                           name VARCHAR(30),
                           age INTEGER,
                           amount REAL
                       );

-- Creating purchases table 

CREATE TABLE IF NOT EXISTS Purchases (
    purchaseId INTEGER PRIMARY KEY,
    customerID INTEGER,
    total REAL,
    timeOfPurchase DATE,
);

-- Needed to prevent many to many 
CREATE TABLE IF NOT EXISTS Purchases_product (
    id INTEGER PRIMARY KEY, -- Will be auto generated
    purchaseId INTEGER,
    productId INTEGER,
    qty INTEGER
);