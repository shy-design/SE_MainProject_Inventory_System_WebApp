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
            Game game = new Game(toy.getId(), toy.getBrand(), toy.getName(), toy.getQtyStart(), toy.getQtySold(),
                    toy.getUnitPrice());
            gameList.add(game);
            model.addAttribute("toys", gameList);
            return "table";
        } else if (action.equals("add") && category.equals("toy")){
            toyList.add(toy);
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
            model.addAttribute("toys", toyList);
            return "table";
        }

        if (action.equals("delete_item")){
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

            return "table";
        }

        if (action.equals("update_item")){
            validID = false;
            if (category.equals("game")){
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
