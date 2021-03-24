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
import org.springframework.web.bind.annotation.*;

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
            model.addAttribute("category","game");
        }
        else if(action.equals("send_toys")) {
            model.addAttribute("toys", toyList);
            model.addAttribute("category","toy");
        }

        return "table";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String addItems(Model model,
                           @RequestParam("action") String action,
                           @RequestParam("category") String category,
                           @ModelAttribute("toy") Toy toy){

        model.addAttribute("category", category);

        // processing Add Item button
        if (action.equals("add") && category.equals("game")){
            jdbcTemplate.update("INSERT INTO GAMES(BRAND, NAME, QTYSTART, QTYSOLD, UNITPRICE) VALUES(?,?,?,?,?);",
                    toy.getBrand(), toy.getName(), toy.getQtyStart(), toy.getQtySold(), toy.getUnitPrice());
            loadData();
            model.addAttribute("toys", gameList);
            return "table";

        } else if (action.equals("add") && category.equals("toy")){
            jdbcTemplate.update("INSERT INTO TOYS(BRAND, NAME, QTYSTART, QTYSOLD, UNITPRICE) VALUES(?,?,?,?,?);",
                    toy.getBrand(), toy.getName(), toy.getQtyStart(), toy.getQtySold(), toy.getUnitPrice());
            loadData();
            model.addAttribute("toys", toyList);
            return "table";

        } else return "add";
    }

    @RequestMapping(value="/update", method=RequestMethod.POST)
    public String updateItems(Model model,
                              @RequestParam("action") String action,
                              @RequestParam("category") String category,
                              @RequestParam("id") int id,
                              @ModelAttribute Toy toy){

        boolean validID = false;
        model.addAttribute("category", category);

        // processing Update Item button
        if (action.equals("update") && category.equals("game")){
            String sqlUpdateItem = "UPDATE games\n" +
                    "SET brand = ?, name = ?, qtyStart = ?, qtySold = ?, unitPrice = ?\n" +
                    "WHERE id = ?;";
            int result = jdbcTemplate.update(sqlUpdateItem, toy.getBrand(), toy.getName(), toy.getQtyStart(),
                    toy.getQtySold(), toy.getUnitPrice(), id);
            loadData();
            model.addAttribute("toys", gameList);
            return "table";
        } else if (action.equals("update") && category.equals("toy")){
            String sqlUpdateItem = "UPDATE toys\n" +
                    "SET brand = ?, name = ?, qtyStart = ?, qtySold = ?, unitPrice = ?\n" +
                    "WHERE id = ?;";
            int result = jdbcTemplate.update(sqlUpdateItem, toy.getBrand(), toy.getName(), toy.getQtyStart(),
                    toy.getQtySold(), toy.getUnitPrice(), id);
            loadData();
            model.addAttribute("toys", toyList);
            return "table";
        }

        if (action.equals("delete_item")){
            int result = 0;
            if (category.equals("game")){
                String sqlDeleteItem = "DELETE FROM games WHERE id=?;";
                result = jdbcTemplate.update(sqlDeleteItem, id);
                loadData();
                model.addAttribute("toys", gameList);
            } else if (category.equals("toy")){
                String sqlDeleteItem = "DELETE FROM toys WHERE id=?;";
                result = jdbcTemplate.update(sqlDeleteItem, id);
                loadData();
                model.addAttribute("toys", toyList);
            }
            if (result == 0){
                model.addAttribute("error_delete", "No item has ID: " + id + ". Please try again.");
            }
            return "table";
        }

        if (action.equals("update_item")){
            validID = false;
            if (category.equals("game")){
                String sqlPickGame = "SELECT * FROM games WHERE ID = ?";
                try {
                    Game game = jdbcTemplate.queryForObject(sqlPickGame, new BeanPropertyRowMapper<>(Game.class), id);
                    validID = true;
                    model.addAttribute("toy", game);
                } catch (Exception e){

                }
            } else if (category.equals("toy")){
                String sqlPickToy = "SELECT * FROM toys WHERE ID = ?";
                try {
                    Toy toy1 = jdbcTemplate.queryForObject(sqlPickToy, new BeanPropertyRowMapper<>(Toy.class), id);
                    validID = true;
                    model.addAttribute("toy", toy1);
                } catch (Exception e){

                }
            }

            if (validID){
                return "update";
            } else {
                model.addAttribute("error_update", "No item has ID: " + id + ". Please try again.");
                if (category.equals("toy")){
                    model.addAttribute("toys", toyList);
                } else if (category.equals("game")){
                    model.addAttribute("toys", gameList);
                }
                return "table";
            }
        } else {
            return "table";
        }
    }
}
