package com.infofromquel.controller;


import com.infofromquel.entity.Role;
import com.infofromquel.entity.User;
import com.infofromquel.service.mail.MailService;
import com.infofromquel.service.userservice.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Api for User options
 * @author Serhii Zhuravlov
 */
@Controller
public class UsersController {

    private static final Logger LOG = Logger.getLogger(UsersController.class);

    private final UserService userService;

    private User user;


    @Autowired
    public UsersController(User user,UserService userService) {
        this.userService = userService;
        this.user = user;
    }

    /**
     * @return all users in the systems
     */
    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<User>> getUsers() {
        List<User> usList = userService.findAll();
        return new ResponseEntity<>(usList,HttpStatus.OK);

    }

    /**
     * @param id id of user
     * @return user with such id
     */
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> getUser(
            @RequestParam int id
    ) {

        user = userService.findUserById(id);

        return ResponseEntity.ok(user);
    }

    /**
     * Api for creation object {@link User}
     * @param login login of user
     * @param email email of user
     * @param password password of user
     * @param userLogo photo of User
     * @return new user {@link User}
     */
    @RequestMapping(value = "/createUser" ,method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(
             @RequestParam("login") String login,
             @RequestParam("email") String email,
             @RequestParam("password") String password,
             @RequestParam("file") MultipartFile userLogo

    ){
        if(userService.findUserByEmail(email)){
            LOG.debug("User Exist");
           return new ResponseEntity<>("Such User Exist in the system",HttpStatus.NOT_ACCEPTABLE);
        }
        try {
            user = userService.createUser(login, email, password, userLogo);
        }catch (IOException e){
            return new ResponseEntity<>("Invalid File",HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(user);
    }

}
