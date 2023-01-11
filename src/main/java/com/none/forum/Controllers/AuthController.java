package com.none.forum.Controllers;

import com.none.forum.Models.UserCreate;
import com.none.forum.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;
import java.util.regex.Pattern;

@Controller
public class AuthController {

    private AuthService service;

    @Autowired
    AuthController(AuthService service) {
        this.service = service;
    }

    @GetMapping("/auth")
    public String authentication(Model model) {
        model.addAttribute("newUser", new UserCreate());
        return "auth";
    }

    @PostMapping("/register")
    public String register(UserCreate newUser, RedirectAttributes redirectAttributes) {
        if (this.service.findUserByEmail(newUser.getEmail()) != null) {
            redirectAttributes.addFlashAttribute("fail", "Another user with this email address already exists!");
            return "redirect:/auth";
        }
        if (isValidEmail(newUser.getEmail()) && isValidPassword(newUser.getPassword()) && Objects.equals(newUser.getPassword(), newUser.getPasswordCheck())) {
            service.register(newUser);
            redirectAttributes.addFlashAttribute("success", "Registration successful! You can now sign in.");
            return "redirect:/auth";
        }
        redirectAttributes.addFlashAttribute("fail", "There has been an issue during registration. Please try again!");
        return "redirect:/auth";
    }

    public static boolean isAuthenticated() {
        return !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,}$";
        Pattern pat = Pattern.compile(passwordRegex);
        if (password == null)
            return false;
        return pat.matcher(password).matches();
    }

}
