package com.none.forum.Controllers;

import com.none.forum.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    private AuthService service;

    @Autowired
    AuthController(AuthService service) {
        this.service = service;
    }

    /*@GetMapping("/login")
    public String login(Model model) {
        return "login";
    }*/

    public static boolean isAuthenticated() {
        return !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
    }

}
