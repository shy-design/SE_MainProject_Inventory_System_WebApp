package com.example.toys_inventory.DataModel;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;
    @BeforeEach
    void testGameSetUp() {
        game = new Game(1,"brand","game1",120,100,25.50);

    }

    @Test
    void qtyOnHand1() {
        int qtyOnHandTest = 20;
        //game.qtyOnHand();
        assertEquals(qtyOnHandTest,game.qtyOnHand());
    }

    @Test
    void qtyOnHand2() {
        assertNotEquals(25,game.qtyOnHand());

    }

    @Test
    void totalSales1() {
        assertEquals(2550,game.totalSales());
    }
}

