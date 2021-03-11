package com.example.toys_inventory;

import com.example.toys_inventory.ToyModel.Toy;
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
@RequestMapping({"/","/toy"})
public class ToyController {

    private List<Toy> toyListGames;
    private List<Toy> toyListGirls;
    private List<Toy> toyListBoys;

    @PostConstruct
    private void loadData() {
        Toy toy1 = new Toy("nt125s","lego","lego constr",100,50,10);
        Toy toy2 = new Toy("nt123lm","lego","lego constr",120,35,17);
        Toy toy3 = new Toy("mwq555g","lego","lego constr",100,25,12);
        toyListGames = new ArrayList<>();
        toyListGames.add(toy1);
        toyListGames.add(toy2);
        toyListGames.add(toy3);

        Toy toy4 = new Toy("ntppo86","barbie","doll",80,55,18);
        Toy toy5 = new Toy("nt1yt56","barbie","doll",120,45,22);
        Toy toy6 = new Toy("mwq589653vc","barbie","doll",125,30,13);
        toyListGirls = new ArrayList<>();
        toyListGirls.add(toy4);
        toyListGirls.add(toy5);
        toyListGirls.add(toy6);

        Toy toy7 = new Toy("btr4578vf","hasbro","transformer",100,50,18);
        Toy toy8 = new Toy("nhyert5","hasbro","car",130,45,20);
        Toy toy9 = new Toy("mwq5321","hasbro","train",90,40,15);
        toyListBoys = new ArrayList<>();
        toyListBoys.add(toy7);
        toyListBoys.add(toy8);
        toyListBoys.add(toy9);

    }
@GetMapping
   public String showMainPage() {
        return "main";

    }

    @PostMapping
    public String showTables(Model model, @RequestParam("action") String action) {

        if(action.equals("send_games")) {
            model.addAttribute("toys", toyListGames);
        }
        else if(action.equals("send_girls")) {
            model.addAttribute("toys", toyListGirls);
        }
        else {
            model.addAttribute("toys", toyListBoys);
        }

        return "table";
    }
}
