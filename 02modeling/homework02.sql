--Wasiq Sohail
--2/17/17
--CS 342

--Cleaning up current database, starting with tables with foreign keys
DROP TABLE Order_Item;
DROP TABLE Shipment;
DROP TABLE Orde;
DROP TABLE Item;
DROP TABLE Warehouse;
DROP TABLE Customer;

--Database schema follows
CREATE TABLE Customer (
	id integer,
	cname varchar(40),
	city varchar(60),
	PRIMARY KEY (id)
);

--Order is not a valid table name so Orde instead
CREATE TABLE Orde (
	id integer,
	odate date,
	customer_id integer,
	ord_amt float,
	PRIMARY KEY (id),
	FOREIGN KEY (customer_id) REFERENCES Customer(id) ON DELETE CASCADE,
	--If the customer no longer exists, there is no need for the order to either
	CHECK (ord_amt > 0)
	--cost should be greater than 0 for any order
);

CREATE TABLE Item (
	id integer,
	iname varchar(50),
	unit_price float,
	PRIMARY KEY (id),
	CHECK (unit_price > 0)
	--unit_price should be a positive value
);


CREATE TABLE Warehouse (
	id integer,
	city varchar(60),
	--The warehouse is a physical location that needs a real address
	PRIMARY KEY (id)
);

CREATE TABLE Order_Item (
	order_id integer,
	item_id integer,
	quantity integer,
	FOREIGN KEY (order_id) REFERENCES Orde(id) ON DELETE CASCADE,
	FOREIGN KEY (item_id) REFERENCES Item(id) ON DELETE CASCADE,
	--If neither the item or the order exists, there is no need for the Order_Item to exist
	CHECK (quantity > 0)
	--Only positive amounts of items can be shipped
);


CREATE TABLE Shipment (
	order_id integer,
	warehouse_id integer,
	ship_date date,
	FOREIGN KEY (order_id) REFERENCES Orde(id) ON DELETE CASCADE,
	--If there is no item to be shipped, there is no need for a record to exist
	FOREIGN KEY (warehouse_id) REFERENCES Warehouse(id) ON DELETE SET NULL
	--Since order is still possible, but warehouse is no longer available, shipment is set to null
	--so a new value can be added to the field
--	CHECK (date > Orde(date))
);

--Populating the tables
INSERT INTO Customer VALUES (1,'John','New York');
INSERT INTO Customer VALUES (2,'Luke','Paris');
INSERT INTO Customer VALUES (3,'Matt','London');
INSERT INTO Customer VALUES (4,'Paul','Hong Kong');

INSERT INTO Orde VALUES (1,DATE '2017-02-16',1,100.02);
INSERT INTO Orde VALUES (2,DATE '2017-01-15',1,3.16);
INSERT INTO Orde VALUES (3,DATE '2015-03-15',4,19.99);
INSERT INTO Orde VALUES (4,DATE '2002-09-01',2, 1234.56);

INSERT INTO Item VALUES (1, 'Sugar', 1.00);
INSERT INTO Item VALUES (2, 'Spice', 2.20);
INSERT INTO Item VALUES (3, 'Everything Nice', 8000.00);
INSERT INTO Item VALUES (4, 'Soap', 1.00);

INSERT INTO Warehouse VALUES (1, 'Dagobah');
INSERT INTO Warehouse VALUES (2, 'Hoth');
INSERT INTO Warehouse VALUES (3, 'Tatooine');

INSERT INTO Order_Item VALUES (1,4, 99);
INSERT INTO Order_Item VALUES (2,1, 9);
INSERT INTO Order_Item VALUES (3,2, 1);
INSERT INTO Order_Item VALUES (3,4, 3);
INSERT INTO Order_Item VALUES (4,3, 3);

INSERT INTO Shipment VALUES (1,2, DATE '2017-02-20');
INSERT INTO Shipment VALUES (2,2, DATE '2017-01-16');
INSERT INTO Shipment VALUES (3,3, DATE '2015-03-20');
INSERT INTO Shipment VALUES (3,2, DATE '2015-03-17');
INSERT INTO Shipment VALUES (4,1, DATE '2004-02-20');

--2:
--a.
--A good natural key for students would require every student would have some value that is
--has the same format. Every possible candidate is either not unique (birth date) or not mandatory
--(driver id) or can be changed (names). For this reason I would advocate a surrogate key because
--it would uniquely identify each student and never changes.
--c.
--The main advantages of surrogate keys are they are guaranteed to be unique (barring user error) and
--there will never be any need to change a surrogate key. The disadvantages are they have no real
--meaning to the world and fill up space that may not be necessary.


--3a
SELECT Orde.odate, Orde.ord_amt
FROM Orde
INNER JOIN Customer ON Customer.id = Orde.customer_id
WHERE cname = 'John'
ORDER BY odate;

--3b
SELECT DISTINCT customer_id FROM Orde;

--3c
SELECT Customer.cname, Customer.id
FROM Customer
INNER JOIN Orde ON Orde.customer_id = Customer.id 
INNER JOIN Order_Item ON Order_Item.order_id = Orde.id
INNER JOIN Item ON Item.id = Order_Item.item_id
WHERE iname = 'Soap';