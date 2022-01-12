DROP DATABASE IF EXISTS SuperMarket;
CREATE DATABASE IF NOT EXISTS SuperMarket;
SHOW DATABASES ;
USE SuperMarket;

DROP TABLE IF EXISTS Customer;
CREATE TABLE IF NOT EXISTS Customer(
    CustId VARCHAR(6) NOT NULL ,
    CustTitle VARCHAR (5),
    CustName VARCHAR(30) NOT NULL DEFAULT 'Unknown',
    CustAddress VARCHAR (20),
    City VARCHAR (20),
    Province VARCHAR (20),
    PostalCode VARCHAR (9),
    CONSTRAINT PRIMARY KEY (CustId)
    );
SHOW TABLES ;
DESCRIBE Customer;

DROP TABLE IF EXISTS Orders;
CREATE TABLE IF NOT EXISTS Orders(
    OrderId VARCHAR(6),
    OrderDate DATE,
    CustId VARCHAR(6),
    CONSTRAINT PRIMARY KEY (OrderId),
    CONSTRAINT FOREIGN KEY (CustId) REFERENCES Customer(CustId) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES ;
DESCRIBE Orders;

DROP TABLE IF EXISTS Item;
CREATE TABLE IF NOT EXISTS Item(
    ItemCode VARCHAR(6),
    Description VARCHAR (50),
    PackSize VARCHAR (20),
    UnitPrice DECIMAL (6,2),
    QtyOnHand INT (5),
    CONSTRAINT PRIMARY KEY (ItemCode)
    );
SHOW TABLES ;
DESCRIBE Item;

DROP TABLE IF EXISTS OrderDetail;
CREATE TABLE IF NOT EXISTS OrderDetail(
    OrderId VARCHAR(6),
    ItemCode VARCHAR(6),
    OrderQty INT (11),
    Discount DECIMAL (6,2),
    CONSTRAINT PRIMARY KEY (OrderId, ItemCode),
    CONSTRAINT FOREIGN KEY (ItemCode) REFERENCES Item(ItemCode) ON DELETE CASCADE ON UPDATE CASCADE ,
    CONSTRAINT FOREIGN KEY (OrderId) REFERENCES Orders(OrderId) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES ;
DESCRIBE OrderDetail;
