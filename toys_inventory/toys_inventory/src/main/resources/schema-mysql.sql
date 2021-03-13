<!-- creating the database toy_inventory -->
CREATE DATABASE IF NOT EXISTS toys_inventory

USE toys_inventory
GO
<!-- creating the table for toys -->
CREATE TABLE IF NOT EXISTS toys (
    id INT AUTO_INCREMENT = 1000 PRIMARY KEY,
    brand VARCHAR(255) NOT NULL,
    name  VARCHAR(255) NOT NULL,
    qtyStart INT NOT NULL,
    qtySold INT NOT NULL,
    qtyOnHand INT as (qtyStart - qtySold) NOT NULL,
    unitPrice DOUBLE NOT NULL,
    totalSales DOUBLE as (qtySold * unitPrice) NOT NULL
);
CREATE TABLE IF NOT EXISTS games (
    id INT AUTO_INCREMENT = 3000 PRIMARY KEY,
    brand VARCHAR(255) NOT NULL,
    name  VARCHAR(255) NOT NULL,
    qtyStart INT NOT NULL,
    qtySold INT NOT NULL,
    qtyOnHand INT as (qtyStart - qtySold) NOT NULL,
    unitPrice DOUBLE NOT NULL,
    totalSales DOUBLE as (qtySold * unitPrice) NOT NULL
);
CREATE TABLE IF NOT EXISTS user (
    emailId VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255) NOT NULL
    );
