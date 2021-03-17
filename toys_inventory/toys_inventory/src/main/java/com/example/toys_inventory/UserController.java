package com.example.toys_inventory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({"/", "/login"})
public class UserController {

    @GetMapping
    public String showLoginPage() {
        return "login";

    }

    @PostMapping
    public String showMainPage(Model model, @RequestParam("email") String email, @RequestParam("password") String password) {
        if (email.equals("abc@gmail.com") && password.equals("password")) {
            return "redirect:/toy";
        }
        else{
            return "redirect:login?error";
        }
    }
}
