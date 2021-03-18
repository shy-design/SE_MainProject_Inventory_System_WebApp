<!-- insert new item into the toys + games table -->
INSERT INTO toys(brand, name,qtyStart, qtySold,unitPrice)
	VALUES(:brand, :name, :qtyStart, :qtySold, :unitPrice);
INSERT INTO games(brand, name,qtyStart, qtySold,unitPrice)
	VALUES(:brand, :name, :qtyStart, :qtySold, :unitPrice);

<!-- update an item in the toys + games table -->
UPDATE toys
    SET brand = :brand, name = :name, qtyStart = :qtyStart, qtySold = :qtySold, unitPrice = :unitPrice
    WHERE id = :id;
UPDATE games
    SET brand = :brand, name = :name, qtyStart = :qtyStart, qtySold = :qtySold, unitPrice = :unitPrice
    WHERE id = :id;

<!-- delete an item in the toys + games table -->
DELETE FROM toys WHERE id=:id;
DELETE FROM games WHERE id=:id;