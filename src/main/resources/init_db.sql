DROP TABLE IF EXISTS Order_Item;
DROP TABLE IF EXISTS Products;
DROP TABLE IF EXISTS Suppliers;
DROP TABLE IF EXISTS Categories;
DROP TABLE IF EXISTS Client_order;
DROP TABLE IF EXISTS Cart;
DROP TABLE IF EXISTS Users;



CREATE TABLE Suppliers (
    Id uuid PRIMARY KEY NOT NULL,
    Name varchar(50),
    Description varchar(255)
);

CREATE TABLE Categories (
    id uuid PRIMARY KEY NOT NULL,
    Name varchar(50),
    Description varchar(255)
);

CREATE TABLE Products (
    Id uuid PRIMARY KEY NOT NULL,
    Name varchar(50),
    Description varchar(255),
    Price decimal,
    Supplier_id uuid REFERENCES Suppliers (Id) ,
    Category_id uuid REFERENCES  Categories (id)
);

CREATE TABLE Users (
    Id uuid PRIMARY KEY NOT NULL,
    User_name VARCHAR(50),
    Password VARCHAR(255),
    Role VARCHAR(20)
);

CREATE TABLE Cart (
    Id uuid PRIMARY KEY NOT NULL,
    OwnerId uuid REFERENCES Users (Id)
);

CREATE TABLE Order_Item (
    Cart_id uuid REFERENCES Cart (Id),
    Product_id uuid REFERENCES Products (Id),
    Quantity int
);

Create TABLE Client_order (
    Cart_id uuid REFERENCES Cart (Id),
    User_id uuid REFERENCES Users (Id),
    Address VARCHAR(255),
    Phone_number VARCHAR(50),
    First_name VARCHAR(50),
    Last_name VARCHAR(50),
    Email VARCHAR(50)
);