package com.example.toys_inventory;

import com.example.toys_inventory.DataModel.Game;
import com.example.toys_inventory.DataModel.Toy;
import com.example.toys_inventory.DataModel.User;
import com.example.toys_inventory.Service.GameService;
import com.example.toys_inventory.Service.ToyService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private List<User> userList;

    private String toyOrGame = "";

    // Method to populate the tables only once.
    @PostConstruct
    private void loadTables(){

        String sqlToys= "SELECT * FROM toys";
        toyList = new ArrayList<>();
        toyList = jdbcTemplate.query(sqlToys, new BeanPropertyRowMapper<>(Toy.class));
        if(toyList.size() == 0) {
            String insertToys = "INSERT INTO toys(brand, name, qtyStart, qtySold, unitPrice)" +
                    "VALUES('Barbie','doll',80,55,18);" +
                    "INSERT INTO toys(brand, name, qtyStart, qtySold, unitPrice)" +
                    "\tVALUES('Lego','Jurassic World',110,95,60);" +
                    "INSERT INTO toys(brand, name, qtyStart, qtySold, unitPrice)\n" +
                    "    VALUES('Lego','Star Wars',150,55,28);" +
                    "INSERT INTO toys(brand, name, qtyStart, qtySold, unitPrice)\n" +
                    "    VALUES('Hot Wheels','Monster Truck',80,55,18);" +
                    "INSERT INTO toys(brand, name, qtyStart, qtySold, unitPrice)" +
                    "    VALUES('Barbie','Barbie Dreamhouse',80,25,88);";
            jdbcTemplate.execute(insertToys);
        }
        toyList = jdbcTemplate.query(sqlToys, new BeanPropertyRowMapper<>(Toy.class));


        String sqlGames = "SELECT * FROM games";
        gameList = new ArrayList<>();
        gameList = jdbcTemplate.query(sqlGames, new BeanPropertyRowMapper<>(Game.class));

        if(gameList.size() == 0){

            String insertGames = "INSERT INTO games(brand, name, qtyStart, qtySold, unitPrice)" +
            "VALUES('Warhammer','Tempest of Souls',100,50,100);" +
            "INSERT INTO games(brand, name, qtyStart, qtySold, unitPrice)" +
            "VALUES('Starling Games','A War of Whisperer',120,35,59);" +
            "INSERT INTO games(brand, name, qtyStart, qtySold, unitPrice)" +
            "VALUES('Cephalofair Games','Gloomhaven Jaws of the Lion',120,35,62);";
            jdbcTemplate.execute(insertGames);
        }
        gameList = jdbcTemplate.query(sqlGames, new BeanPropertyRowMapper<>(Game.class));


        String sqlUsers = "SELECT * FROM users";
        userList = new ArrayList<>();
        userList = jdbcTemplate.query(sqlUsers, new BeanPropertyRowMapper<>(User.class));

        if(userList.size() == 0){
            String insertUsers = "INSERT INTO users(login, password)" +
                    "VALUES('abc@gmail.com', 'password');" +
                    "INSERT INTO users(login, password)" +
                    "VALUES('kate@gmail.com', 'password1');" +
                    "INSERT INTO users(login, password)" +
                    "VALUES('andy@gmail.com', 'password2');" +
                    "INSERT INTO users(login, password)\n" +
                    "VALUES('danilo@gmail.com', 'password3');" +
                    "INSERT INTO users(login, password)" +
                    "VALUES('aakash@gmail.com', 'password4');" +
                    "INSERT INTO users(login, password)" +
                    "VALUES('reza@gmail.com', 'password5');";
            jdbcTemplate.execute(insertUsers);
        }
        userList = jdbcTemplate.query(sqlUsers, new BeanPropertyRowMapper<>(User.class));
    }


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
            toyOrGame = "games";
        }
        else if(action.equals("send_toys")) {
            model.addAttribute("toys", toyList);
            model.addAttribute("category","toy");
            toyOrGame = "toys";
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
            jdbcTemplate.update("INSERT INTO games(brand, name, qtyStart, qtySold, unitPrice) VALUES(?,?,?,?,?);",
                    toy.getBrand(), toy.getName(), toy.getQtyStart(), toy.getQtySold(), toy.getUnitPrice());
            loadData();
            model.addAttribute("toys", gameList);
            return "table";

        } else if (action.equals("add") && category.equals("toy")){
            jdbcTemplate.update("INSERT INTO toys(brand, name, qtyStart, qtySold, unitPrice) VALUES(?,?,?,?,?);",
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
                String sqlPickGame = "SELECT * FROM games WHERE id = ?";
                try {
                    Game game = jdbcTemplate.queryForObject(sqlPickGame, new BeanPropertyRowMapper<>(Game.class), id);
                    validID = true;
                    model.addAttribute("toy", game);
                } catch (Exception e){

                }
            } else if (category.equals("toy")){
                String sqlPickToy = "SELECT * FROM toys WHERE id = ?";
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

    @RequestMapping(value="/generate", method= RequestMethod.GET)
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        //To download it, and not first display the report
        //String headerKey = "Content-Disposition";
        String headerKey = " ";
        String headerValue = "attachment; filename=toys_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        //List<Toy> toyList = toyService.listAll();
        String sqlToys= "SELECT * FROM toys";
        toyList = new ArrayList<>();
        toyList = jdbcTemplate.query(sqlToys, new BeanPropertyRowMapper<>(Toy.class));

        String sqlGames = "SELECT * FROM games";
        gameList = new ArrayList<>();
        gameList = jdbcTemplate.query(sqlGames, new BeanPropertyRowMapper<>(Game.class));

        if(toyOrGame.equals("toys")){
            PdfExporterToy exporter = new PdfExporterToy(toyList);
            exporter.export(response);
        }
        else if(toyOrGame.equals("games")){
            PdfExporterGame exporter = new PdfExporterGame(gameList);
            exporter.export(response);
        }

    }
}
