package com.none.forum.Controllers;

import com.none.forum.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForumController {

    private UserService userService;

    @Autowired
    ForumController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/index")
    public String home(Model model) {
        model.addAttribute("active", "index");
        return "index";
    }

}
