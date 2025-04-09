
-- Creating our postgres db
-- CREATE DATABASE iF NOT EXISTS compShop;


-- Creating custom Customer data types
CREATE TYPE customer_obj AS (
		-- Primary key kept in customer table, 
		name VARCHAR (255),
		email VARCHAR(255),
		age INT,
    balance REAL	
);

-- Creating custom Product data type
CREATE TYPE product_obj AS (
		-- Primary key kept in product table, 
		name VARCHAR (255),
		stock INT,
		inStock BOOLEAN,
    price REAL	
);


-- Creating custom Purchase data type
CREATE TYPE purch_obj AS (
		-- Primary key kept in product table, 
	  purchaseDate DATE,
		purchaseIds INT[],
		customerId INT,
		total REAL
);

-- CREATING OUR FIRST TABLE - Customers
 
CREATE TABLE IF NOT EXISTS Customer(
	customerID SERIAL,	
	toStr VARCHAR(50), 
	customer customer_obj, -- Custom type rather than blob - will see
	PRIMARY KEY(customerID)  
);

CREATE TABLE IF NOT EXISTS Product(
	productId SERIAL,	
	toStr VARCHAR(50), 
	product BYTEA,
	PRIMARY KEY(productId)
);

CREATE TABLE IF NOT EXISTS Purchases(
	purchaseId SERIAl,	
	toStr VARCHAR(50), 
	purchase BYTEA,
	PRIMARY KEY(purchaseId)
);

