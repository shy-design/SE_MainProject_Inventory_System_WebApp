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
    void check_qtyOnHand1() {
        int qtyOnHandTest = 20;
        //game.qtyOnHand();
        assertEquals(qtyOnHandTest,game.qtyOnHand());
    }

    @Test
    void check_qtyOnHand2() {
        assertNotEquals(25,game.qtyOnHand());

    }

    @Test
    void check_totalSales1() {
        assertEquals(2550,game.totalSales());
    }

    @Test
    void check_totalSales2() {
        assertNotEquals(3550,game.totalSales());
    }

    @Test
    void check_getBrand() {
        assertEquals("brand",game.getBrand());
    }
    @Test
    void check_getBrand2() {
        assertNotEquals("brand123",game.getBrand());
    }

    @Test
    void check_getName() {
        assertEquals("game1",game.getName());
    }
    @Test
    void check_getName2() {
        assertNotEquals("game123",game.getBrand());
    }

    @Test
    void check_getQtyStart() {
        assertEquals(120,game.getQtyStart());
    }
    @Test
    void check_getQtyStart2() {
        assertNotEquals(150,game.getQtyStart());
    }

    @Test
    void check_getQtySold() {
        assertEquals(100,game.getQtySold());
    }
    @Test
    void check_getQtySold2() {
        assertNotEquals(150,game.getQtySold());
    }

    @Test
    void check_getUnitPrice() {
        assertEquals(25.5,game.getUnitPrice());
    }
    @Test
    void check_getUnitPrice2() {
        assertNotEquals(33.55,game.getUnitPrice());
    }
}

