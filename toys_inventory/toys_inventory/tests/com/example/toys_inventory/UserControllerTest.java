package com.example.toys_inventory;

import com.example.toys_inventory.DataModel.Toy;
import com.example.toys_inventory.DataModel.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {

    private JdbcTemplate jdbcTemplate;
    private UserController controllerTest;

    @Autowired
    public UserControllerTest(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        controllerTest = new UserController(jdbcTemplate);
    }

    @Test
    public void test_ShouldReturnUsersList(){

        List<User> userList = controllerTest.loadData();

        //assertEquals(6, userList.size());
        assertEquals(userList, controllerTest.userList);

    }
}