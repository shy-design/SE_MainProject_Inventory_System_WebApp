CREATE TABLE IF NOT EXISTS toys (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    brand VARCHAR(255) NOT NULL,
    name  VARCHAR(255) NOT NULL,
    qtyStart INT NOT NULL,
    qtySold INT NOT NULL,
    qtyOnHand INT as (qtyStart - qtySold) NOT NULL,
    unitPrice DOUBLE NOT NULL,
    totalSales DOUBLE as (qtySold * unitPrice) NOT NULL
);
CREATE TABLE IF NOT EXISTS games (
    id INT AUTO_INCREMENT PRIMARY KEY,
    brand VARCHAR(255) NOT NULL,
    name  VARCHAR(255) NOT NULL,
    qtyStart INT NOT NULL,
    qtySold INT NOT NULL,
    qtyOnHand INT as (qtyStart - qtySold) NOT NULL,
    unitPrice DOUBLE NOT NULL,
    totalSales DOUBLE as (qtySold * unitPrice) NOT NULL
);
CREATE TABLE IF NOT EXISTS users (
    login VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255) NOT NULL
);

