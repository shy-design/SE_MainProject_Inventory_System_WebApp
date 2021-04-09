package com.example.toys_inventory.DataModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
private User user;

    @BeforeEach
      void userTestSetUp ()   {

        user = new User("login","password");
    }

    @Test
    void checkPassword1() {

        assertEquals("password",user.getPassword());
    }

    @Test
    void checkPassword2() {

        assertNotEquals("password12345",user.getPassword());
    }

    @Test
    void checkLogin1() {

        assertEquals("login",user.getLogin());
    }

    @Test
    void checkLogin2() {

        assertNotEquals("login12345",user.getLogin());
    }

}