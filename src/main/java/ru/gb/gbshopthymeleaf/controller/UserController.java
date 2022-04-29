package ru.gb.gbshopthymeleaf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.gbshopthymeleaf.entity.Product;
import ru.gb.api.security.dto.UserDto;
import ru.gb.gbshopthymeleaf.service.UserGateway;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserGateway userGateway;

    @GetMapping("/{userId}")
    public String loginForm(Model model, @RequestParam(name = "id", required = false) Long id) {
        UserDto user;

        if (id != null) {
            // полчим пользователя
            ResponseEntity<?> getUser = userGateway.getUser(id);
            if (getUser.hasBody()) {
                user = (UserDto)userGateway.getUser(id).getBody();    
            } else {
                user = new UserDto();
            }
        } else {
            user = new UserDto();
        }
        model.addAttribute("accountUser", user);
        return "login-form";
    }

    @GetMapping("/reg")
    public String registrationForm(Model model) {
        UserDto user = new UserDto();

        model.addAttribute("accountUser", user);
        return "login-registration-form";
    }

    @PostMapping()
    public String loginUser(UserDto user) {
        ResponseEntity<?> responseEntity = userGateway.handlePost(user);
        return "redirect:/product/all";
    }

    @PostMapping("/reg")
    public String registrationFUser(UserDto user) {
        ResponseEntity<?> responseEntity = userGateway.handlePost(user);
        return "redirect:/product/all";
    }
}
