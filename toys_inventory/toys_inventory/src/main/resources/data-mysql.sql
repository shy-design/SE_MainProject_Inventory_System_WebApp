<!-- insert initial values into the toys table -->
INSERT INTO toys(brand, name,qtyStart, qtySold,unitPrice)
	VALUES(SELECT "barbie","doll",80,55,18
	WHERE NOT EXISTS (SELECT * FROM toys));
INSERT INTO toys(brand, name,qtyStart, qtySold,unitPrice)
    VALUES(SELECT "barbie","doll",120,45,22
    WHERE NOT EXISTS (SELECT * FROM toys));
INSERT INTO toys(brand, name,qtyStart, qtySold,unitPrice)
   	VALUES(SELECT "barbie","doll",125,30,13
   	WHERE NOT EXISTS (SELECT * FROM toys));
INSERT INTO toys(brand, name,qtyStart, qtySold,unitPrice)
	VALUES(SELECT "hasbro","transformer",100,50,18
	WHERE NOT EXISTS (SELECT * FROM toys));
INSERT INTO toys(brand, name,qtyStart, qtySold,unitPrice)
	VALUES(SELECT "hasbro","car",130,45,20
	WHERE NOT EXISTS (SELECT * FROM toys));
INSERT INTO toys(brand, name,qtyStart, qtySold,unitPrice)
	VALUES(SELECT "hasbro","train",90,40,15
	WHERE NOT EXISTS (SELECT * FROM toys));

<!-- insert initial values into the games table -->
INSERT INTO games(brand, name,qtyStart, qtySold,unitPrice)
	VALUES(SELECT "lego","lego constructor",100,50,10
	WHERE NOT EXISTS (SELECT * FROM games));
INSERT INTO games(brand, name,qtyStart, qtySold,unitPrice)
	VALUES(SELECT "lego","lego constructor",120,35,17
	WHERE NOT EXISTS (SELECT * FROM games));
INSERT INTO games(brand, name,qtyStart, qtySold,unitPrice)
	VALUES(SELECT "lego","lego constructor",100,25,12
	WHERE NOT EXISTS (SELECT * FROM games));

<!-- insert initial values into the User table -->
INSERT INTO user(emailId, password)
	VALUES("abc@gmail.com", "password");