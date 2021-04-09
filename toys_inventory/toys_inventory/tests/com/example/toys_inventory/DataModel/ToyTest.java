package com.example.toys_inventory.DataModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToyTest {

    private Toy toy;
    @BeforeEach
    void testToySetUp() {

        toy = new Toy(1,"brand","toy1",145,100,25.50);
    }

    @Test
    void check_qtyOnHand1() {
        int qtyOnHandTest = 45;
        //game.qtyOnHand();
        assertEquals(qtyOnHandTest,toy.qtyOnHand());
    }

    @Test
    void check_qtyOnHand2() {
        assertNotEquals(25,toy.qtyOnHand());

    }

    @Test
    void check_totalSales1() {
        assertEquals(2550,toy.totalSales());
    }

    @Test
    void check_totalSales2() {
        assertNotEquals(3000,toy.totalSales());
    }

    @Test
    void check_getBrand() {
        assertEquals("brand",toy.getBrand());
    }
    @Test
    void check_getBrand2() {
        assertNotEquals("brand123",toy.getBrand());
    }

    @Test
    void check_getName() {
        assertEquals("toy1",toy.getName());
    }
    @Test
    void check_getName2() {
        assertNotEquals("toy123",toy.getBrand());
    }

    @Test
    void check_getQtyStart() {
        assertEquals(145,toy.getQtyStart());
    }
    @Test
    void check_getQtyStart2() {
        assertNotEquals(150,toy.getQtyStart());
    }

    @Test
    void check_getQtySold() {
        assertEquals(100,toy.getQtySold());
    }
    @Test
    void check_getQtySold2() {
        assertNotEquals(150,toy.getQtySold());
    }

    @Test
    void check_getUnitPrice() {
        assertEquals(25.5,toy.getUnitPrice());
    }
    @Test
    void check_getUnitPrice2() {
        assertNotEquals(33.55,toy.getUnitPrice());
    }
}