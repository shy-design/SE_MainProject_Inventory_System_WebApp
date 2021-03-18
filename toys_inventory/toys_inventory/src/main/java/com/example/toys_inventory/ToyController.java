package com.example.toys_inventory;

import com.example.toys_inventory.DataModel.Game;
import com.example.toys_inventory.DataModel.Toy;
import com.example.toys_inventory.Service.GameService;
import com.example.toys_inventory.Service.ToyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping({"/toy"})
public class ToyController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ToyService toyService;
    @Autowired
    private GameService gameService;

    private List<Toy> toyList;
    private List<Game> gameList;


    @PostConstruct
    private void loadData() {

        String sqlToys= "SELECT * FROM toys";
        toyList = new ArrayList<>();
        toyList = jdbcTemplate.query(sqlToys, new BeanPropertyRowMapper<>(Toy.class));

        String sqlGame = "SELECT * FROM games";
        gameList = jdbcTemplate.query(sqlGame, new BeanPropertyRowMapper<>(Game.class));
    }

    @GetMapping
   public String showMainPage() {
        return "main";

    }

    @PostMapping
    public String showTables(Model model, @RequestParam("action") String action) {

        if(action.equals("send_games")) {
            model.addAttribute("toys", gameList);
        }
        else if(action.equals("send_toys")) {
            model.addAttribute("toys", toyList);
        }

        return "table";
    }
}
