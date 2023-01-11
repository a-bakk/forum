package com.none.forum.Controllers;

import com.none.forum.Models.UserDetails;
import com.none.forum.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private UserService service;

    @Autowired
    UserController(UserService userService) {
        this.service = userService;
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal UserDetails currentUser,Model model) {
        if (!AuthController.isAuthenticated()) return "redirect:/auth";
        model.addAttribute("user", currentUser);
        model.addAttribute("active", "profile");
        return "profile";
    }

    @PostMapping("/delete_user")
    public String deleteUser(@AuthenticationPrincipal UserDetails currentUser) {
        this.service.deleteUser(currentUser);
        return "redirect:/logout";
    }

}
