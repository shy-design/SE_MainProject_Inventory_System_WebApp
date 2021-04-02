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
    void qtyOnHand1() {
        int qtyOnHandTest = 45;
        assertEquals(qtyOnHandTest,toy.qtyOnHand());

    }

    @Test
    void qtyOnHand2() {
        assertNotEquals(25,toy.qtyOnHand());
    }
}