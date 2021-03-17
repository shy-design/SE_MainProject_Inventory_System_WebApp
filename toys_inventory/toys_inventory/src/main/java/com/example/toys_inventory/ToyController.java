package com.example.toys_inventory;

import com.example.toys_inventory.DataModel.Game;
import com.example.toys_inventory.DataModel.Toy;
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
        }
        else if(action.equals("send_toys")) {
            model.addAttribute("toys", toyList);
        }

        return "table";
    }
}
