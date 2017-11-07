package com.infofromquel.controller;


import com.infofromquel.entity.EmailTemplates;
import com.infofromquel.entity.User;
import com.infofromquel.service.UserService;
import com.infofromquel.service.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UsersController {


    @Autowired
    private UserService userService;

    @Autowired
    private User user;

    @Autowired
    private MailService mailService;


    @RequestMapping(value = "/getUsers", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<User>> getUsers() {


        List<User> usList = userService.findAll();

        return ResponseEntity.ok(usList);

    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> getUser(
            @RequestParam int id
    ) {

        user = userService.findUserById(id);

        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/userPage" , method = RequestMethod.GET)
    public String getUserPage(){
        return "user";
    }

    @RequestMapping(value = "/createUser" ,method = RequestMethod.POST)
    public ResponseEntity<User> createUser(
             @RequestParam String login,
             @RequestParam String email,
             @RequestParam String password

    ){
     //   int hashCode;

        user.setEmail(email);
        user.setName(login);
        user.setPassword(password);
        userService.createUser(user);

       // hashCode = user.hashCode();




        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/sendEmail" , method = RequestMethod.GET)
    public String getEmail(){
        mailService.sendEmail(user);
                 return "user";
    }

    @RequestMapping(value = "/sendHtmlEmail" , method = RequestMethod.GET)
    public String getHtmlEmail(){
        int hashcode;
        user.setEmail("iamquel08@gmail.com");
        user.setName("Quel");
        user.setPassword("12312");
        hashcode = user.hashCode();
        List<String> linksForEmail = new ArrayList<>();
        linksForEmail.add("https://github.com/" + String.valueOf(hashcode));
        mailService.sendHtmlEmail(user,mailService.setLinksIntoMessage(EmailTemplates.REGESTRATION_TEMPLATE,linksForEmail));

        return "user";
    }

}
