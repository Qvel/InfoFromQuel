package com.infofromquel.controller;

import com.infofromquel.entity.User;
import com.infofromquel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UsersController {


    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<User>> getUsers() {


        List<User> usList = userService.findAll();

        return ResponseEntity.ok(usList);

    }
}
