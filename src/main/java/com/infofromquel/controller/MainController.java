package com.infofromquel.controller;

import com.infofromquel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage() {
        return "index";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers(Model model){
        model.addAttribute("users",userService.findAll());
        return "users";
    }

}
