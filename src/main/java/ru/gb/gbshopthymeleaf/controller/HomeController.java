package ru.gb.gbshopthymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String home() {
        return "redirect:/product/all";
    }

    @GetMapping("/logininfo")
    public String getLoginInfo(Model model) {
        return "login-info";
    }
}
