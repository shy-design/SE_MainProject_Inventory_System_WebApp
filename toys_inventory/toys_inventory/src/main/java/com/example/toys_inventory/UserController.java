package com.example.toys_inventory;

import com.example.toys_inventory.DataModel.User;
import com.example.toys_inventory.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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
@RequestMapping({"/", "/login"})
public class UserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserService userService;

    private List<User> userList;


    @PostConstruct
    private void loadData() {

        String sqlUser= "SELECT * FROM users";
        userList = new ArrayList<>();
        userList = jdbcTemplate.query(sqlUser, new BeanPropertyRowMapper<>(User.class));

    }

    @GetMapping
    public String showLoginPage() {
        return "login";

    }

    @PostMapping
    public String showMainPage(Model model, @RequestParam("email") String email, @RequestParam("password") String password) {
        String value = "";
        for(int i=0;i<userList.size()-1;i++){
            if (email.equals(userList.get(i).getLogin()) && password.equals(userList.get(i).getPassword())) {
                value = "redirect:/toy";
                break;
            }
            else{
                value = "redirect:login?error";
            }
        }
        return value;

    }
}
