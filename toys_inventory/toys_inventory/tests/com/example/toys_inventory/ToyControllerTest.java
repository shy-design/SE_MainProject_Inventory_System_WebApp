package com.example.toys_inventory;

import com.example.toys_inventory.DataModel.Game;
import com.example.toys_inventory.DataModel.Toy;
import com.example.toys_inventory.DataModel.User;
import com.example.toys_inventory.Service.ToyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@JdbcTest
//@Sql({"schema.sql"})
@SpringBootTest
public class ToyControllerTest {

    private JdbcTemplate jdbcTemplate;
    private ToyController controllerTest;

    @Autowired
    public ToyControllerTest(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        controllerTest = new ToyController(jdbcTemplate);
    }

    @Test
    public void loadToys_ShouldReturnToysList(){

        List<Toy> toyList = controllerTest.loadToys();

        //assertEquals(12, toyList.size());
       // assertEquals(toyList, controllerTest.toyList.toString());
        assertEquals(toyList, controllerTest.toyList);

    }

    @Test
    public void loadGames_ShouldReturnGamesList(){

        List<Game> gamesList = controllerTest.loadGames();

        //assertEquals(10, gamesList.size());
        assertEquals(gamesList, controllerTest.gameList);
    }
}
