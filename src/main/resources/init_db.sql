-- -- DROP TABLE IF EXISTS Order_Item;
-- DROP TABLE If EXISTS Cart_Items;
-- DROP TABLE IF EXISTS Products;
-- DROP TABLE IF EXISTS Suppliers;
-- DROP TABLE IF EXISTS Categories;
-- DROP TABLE IF EXISTS Client_order;
-- DROP TABLE IF EXISTS Cart;
-- DROP TABLE IF EXISTS Users;
--
--
--
-- CREATE TABLE Suppliers (
--     Id uuid PRIMARY KEY NOT NULL,
--     Name varchar(50),
--     Description varchar(255)
-- );
--
-- CREATE TABLE Categories (
--     id uuid PRIMARY KEY NOT NULL,
--     Name varchar(50),
--     Description varchar(255)
-- );
--
-- CREATE TABLE Products (
--     Id uuid PRIMARY KEY NOT NULL,
--     Name varchar(50),
--     Description varchar(255),
--     Price decimal,
--     Img TEXT,
--     Supplier_id uuid REFERENCES Suppliers (Id) ,
--     Category_id uuid REFERENCES  Categories (id)
-- );
--
-- CREATE TABLE Users (
--     Id uuid PRIMARY KEY NOT NULL,
--     User_name VARCHAR(50),
--     Password VARCHAR(255),
--     Role VARCHAR(20)
-- );
--
-- CREATE TABLE Cart (
--     Id uuid PRIMARY KEY NOT NULL,
--     OwnerId uuid REFERENCES Users (Id)
-- );
--
-- CREATE TABLE Cart_Items (
--     Cart_id uuid REFERENCES Cart (Id),
--     Product_id uuid REFERENCES Products (Id),
--     Quantity int
-- );
--
-- Create TABLE Client_order (
--     Id uuid PRIMARY KEY not null,
--     Cart_id uuid REFERENCES Cart (Id),
--     User_id uuid REFERENCES Users (Id),
--     Address VARCHAR(255),
--     Phone_number VARCHAR(50),
--     First_name VARCHAR(50),
--     Last_name VARCHAR(50),
--     Email VARCHAR(50)
-- );
--
--
-- SELECT
--     ci.quantity products_quantity,
--     p.name product_name,
--     p.description product_description,
--     p.price product_price,
--     s.name supplier_name,
--     s.description supplier_description,
--     c.name category_name,
--     c.description category_nescription
--
-- FROM cart
--          INNER JOIN cart_items ci on cart.id = ci.cart_id
--          INNER JOIN products p on ci.product_id = p.id
--          INNER JOIN categories c on c.id = p.category_id
--          INNER JOIN suppliers s on s.id = p.supplier_id
-- WHERE cart.ownerid = 'b0eebc93-9c0b-4ef8-bb6d-6bb9bd380a15';
--
--
--
-- -- ownerid = 1
--
-- INSERT INTO users (id, user_name, password, role) VALUES (
--                                                              'b0eebc93-9c0b-4ef8-bb6d-6bb9bd380a15',
--                                                              'ADMIN',
--                                                              'ADMIN',
--                                                              'ADMIN'
--                                                          );
--
-- INSERT INTO cart (id, ownerid) VALUES (
--                                           'b0eebc92-9c0b-4ef8-bb6d-6bb9bd380a15',
--                                           'b0eebc93-9c0b-4ef8-bb6d-6bb9bd380a15'
--                                       );
--
--
--
-- INSERT INTO suppliers VALUES (
--                                  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11',
--                                  'Samsung',
--                                  'Nice brand'
--                              );
--
-- INSERT INTO suppliers VALUES (
--                                  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13',
--                                  'Apple',
--                                  'Bad brand'
--                              );
--
--
--
-- INSERT INTO categories VALUES (
--                                   'b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a15',
--                                   'Tablet',
--                                   'A bigger phone'
--                               );
--
--
-- INSERT INTO categories VALUES (
--                                   'c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a15',
--                                   'Phone',
--                                   'A smaller tablet'
--                               );
--
--
-- INSERT INTO products VALUES (
--                                 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a32',
--                                 'Galaxy 5000',
--                                 'A phone',
--                                 3000,
--                                 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11',
--                                 'c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a15'
--                             );
--
--
-- INSERT INTO products VALUES (
--                                 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a77',
--                                 'iPhone 50',
--                                 'A iPhone',
--                                 3000,
--                                 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13',
--                                 'c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a15'
--                             );
--
-- INSERT INTO cart_items (cart_id, product_id, quantity) VALUES (
--                                                                   'b0eebc92-9c0b-4ef8-bb6d-6bb9bd380a15',
--                                                                   'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a77',
--                                                                   10
--                                                               );
--


DROP TABLE If EXISTS Cart_Items;
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
                          Img TEXT,
                          Supplier_id uuid REFERENCES Suppliers (Id) ,
                          Category_id uuid REFERENCES  Categories (id)
);

CREATE TABLE Users (
                       Id uuid PRIMARY KEY NOT NULL,
                       User_name VARCHAR(50),
                       Password VARCHAR(2555),
                       Email VARCHAR(255),
                       Role VARCHAR(20)
);

CREATE TABLE Cart (
                      Id uuid PRIMARY KEY NOT NULL,
                      OwnerId uuid REFERENCES Users (Id)
);

CREATE TABLE Cart_Items (
                            Cart_id uuid REFERENCES Cart (Id),
                            Product_id uuid REFERENCES Products (Id),
                            Quantity int
);

Create TABLE Client_order (
                              Id uuid PRIMARY KEY not null,
                              Cart_id uuid REFERENCES Cart (Id),
                              User_id uuid REFERENCES Users (Id),
                              Address VARCHAR(255),
                              Phone_number VARCHAR(50),
                              First_name VARCHAR(50),
                              Last_name VARCHAR(50),
                              Email VARCHAR(50)
);


SELECT
    ci.quantity products_quantity,
    p.name product_name,
    p.description product_description,
    p.price product_price,
    s.name supplier_name,
    s.description supplier_description,
    c.name category_name,
    c.description category_nescription

FROM cart
         INNER JOIN cart_items ci on cart.id = ci.cart_id
         INNER JOIN products p on ci.product_id = p.id
         INNER JOIN categories c on c.id = p.category_id
         INNER JOIN suppliers s on s.id = p.supplier_id
WHERE cart.ownerid = 'b0eebc93-9c0b-4ef8-bb6d-6bb9bd380a15';



-- ownerid = 1

INSERT INTO users (id, user_name, password, role) VALUES (
                                                             'b0eebc93-9c0b-4ef8-bb6d-6bb9bd380a15',
                                                             'ADMIN',
                                                             'ADMIN',
                                                             'ADMIN'
                                                         );

INSERT INTO cart (id, ownerid) VALUES (
                                          'b0eebc92-9c0b-4ef8-bb6d-6bb9bd380a15',
                                          'b0eebc93-9c0b-4ef8-bb6d-6bb9bd380a15'
                                      );



INSERT INTO suppliers VALUES (
                                 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11',
                                 'Samsung',
                                 'Nice brand'
                             );

INSERT INTO suppliers VALUES (
                                 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13',
                                 'Apple',
                                 'Bad brand'
                             );



INSERT INTO categories VALUES (
                                  'b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a15',
                                  'Tablet',
                                  'A bigger phone'
                              );


INSERT INTO categories VALUES (
                                  'c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a15',
                                  'Phone',
                                  'A smaller tablet'
                              );


INSERT INTO products VALUES (
                                'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a32',
                                'Galaxy 5000',
                                'A phone',
                                3000,
                                'https://lcdn.altex.ro/resize/media/catalog/product/T/e/2bd48d28d1c32adea0e55139a4e6434a/Telefon_SAMSUNG_Galaxy_A03_32GB_3GB_RAM_Dual_SIM_Black_5_.jpg',
                                'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11',
                                'c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a15'
                            );


INSERT INTO products VALUES (
                                'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a77',
                                'iPhone 50',
                                'A iPhone',
                                3000,
                                'https://storage.googleapis.com/flip-global/device-images/apple_iphone-6s_space-grey_sell_mip@_1000.jpg',
                                'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13',
                                'c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a15'
                            );

INSERT INTO cart_items (cart_id, product_id, quantity) VALUES (
                                                                  'b0eebc92-9c0b-4ef8-bb6d-6bb9bd380a15',
                                                                  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a77',
                                                                  10
                                                              );