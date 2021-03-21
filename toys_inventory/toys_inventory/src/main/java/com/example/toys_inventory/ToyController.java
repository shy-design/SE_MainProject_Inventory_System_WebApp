package com.example.toys_inventory;

import com.example.toys_inventory.DataModel.Game;
import com.example.toys_inventory.DataModel.Toy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping({"/toy"})
public class ToyController {

    private List<Toy> toyList;
    private List<Game> gameList;


    @PostConstruct
    private void loadData() {
        Toy toy1 = new Toy( 1000,"barbie","doll",80,55,18);
        Toy toy2 = new Toy(1001,"barbie","doll",120,45,22);
        Toy toy3 = new Toy(1002,"barbie","doll",125,30,13);
        Toy toy4 = new Toy(1003,"hasbro","transformer",100,50,18);
        Toy toy5 = new Toy(1004,"hasbro","car",130,45,20);
        Toy toy6 = new Toy(1005,"hasbro","train",90,40,15);
        toyList = new ArrayList<>();

        toyList.add(toy1);
        toyList.add(toy2);
        toyList.add(toy3);
        toyList.add(toy4);
        toyList.add(toy5);
        toyList.add(toy6);

        Game game1= new Game(3000,"lego","lego constr",100,50,10);
        Game game2 = new Game(3001,"lego","lego constr",120,35,17);
        Game game3 = new Game(3002,"lego","lego constr",100,25,12);
        gameList = new ArrayList<>();
        
        gameList.add(game1);
        gameList.add(game2);
        gameList.add(game3);

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
<<<<<<< Updated upstream
            Game game = new Game(toy.getId(), toy.getBrand(), toy.getName(), toy.getQtyStart(), toy.getQtySold(),
                    toy.getUnitPrice());
            gameList.add(game);
            model.addAttribute("toys", gameList);
            return "table";
        } else if (action.equals("add") && category.equals("toy")){
            toyList.add(toy);
            model.addAttribute("toys", toyList);
            return "table";
=======
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

>>>>>>> Stashed changes
        } else return "add";
    }

    @RequestMapping(value="/update", method=RequestMethod.POST)
    public String updateItems(Model model,
                              @RequestParam("action") String action,
                              @RequestParam("category") String category,
                              @RequestParam("id") int id,
                              @ModelAttribute Toy toy){

<<<<<<< Updated upstream
        boolean validID = false;
=======
        boolean validID;
>>>>>>> Stashed changes
        model.addAttribute("category", category);

        // processing Update Item button
        if (action.equals("update") && category.equals("game")){
<<<<<<< Updated upstream
            Game game1 = new Game(toy.getId(), toy.getBrand(), toy.getName(), toy.getQtyStart(), toy.getQtySold(), toy.getUnitPrice());
            for (Game game: gameList) {
                if (game.getId() == id){
                    gameList.set(gameList.indexOf(game),game1);
                }
            }
            model.addAttribute("toys", gameList);
            return "table";
        } else if (action.equals("update") && category.equals("toy")){
            for (Toy toy1: toyList) {
                if (toy1.getId() == id){
                    toyList.set(toyList.indexOf(toy1),toy);
                }
            }
=======
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
>>>>>>> Stashed changes
            model.addAttribute("toys", toyList);
            return "table";
        }

        if (action.equals("delete_item")){
<<<<<<< Updated upstream
            if (category.equals("game")){
                if (!gameList.removeIf(n -> (n.getId() == id))){
                    model.addAttribute("error_delete", "No item has ID: " + id + ". Please try again.");
                }
                model.addAttribute("toys", gameList);
            } else if (category.equals("toy")){
                if (!toyList.removeIf(n -> (n.getId() == id))){
                    model.addAttribute("error_delete", "No item has ID: " + id + ". Please try again.");
                }
                model.addAttribute("toys", toyList);
            }

=======
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
>>>>>>> Stashed changes
            return "table";
        }

        if (action.equals("update_item")){
            validID = false;
            if (category.equals("game")){
<<<<<<< Updated upstream
                for (Game game: gameList) {
                    if (game.getId() == id){
                        model.addAttribute("toy", game);
                        validID = true;
                    }
                }
            } else if (category.equals("toy")){
                for (Toy toy_updated: toyList) {
                    if (toy_updated.getId() == id){
                        model.addAttribute("toy", toy_updated);
                        validID = true;
                    }
=======
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

>>>>>>> Stashed changes
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
