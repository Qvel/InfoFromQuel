package com.infofromquel.controller;



import com.infofromquel.entity.User;
import com.infofromquel.service.userservice.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
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
    public UsersController(UserService userService) {
        this.userService = userService;
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
            @RequestParam("id") Long id
    ) {
        user = userService.findUserById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * Api for updating logo of  {@link User}
     * @param id id of user
     * @param userLogo photo of User
     * @return new user {@link User}
     */
    @RequestMapping(value = "/updateAvatar" ,method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(
             @RequestParam("id") Long id,
             @RequestParam("file") MultipartFile userLogo

    ){
        try {
            user = userService.updateAvatar(id, userLogo);
        }catch (IOException e){
            return new ResponseEntity<>("Invalid File",HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(user);
    }

    /**
     * Api for creation object {@link User}
     * @return {@link HttpStatus}
     */
    @RequestMapping(value = "/registration",method = RequestMethod.POST)
    public ResponseEntity registration(
         @RequestBody User user
    ){
        if(userService.findUserByEmail(user.getEmail())){
            LOG.debug("User Exist");
            return new ResponseEntity<>("Such User Exist in the system",HttpStatus.NOT_ACCEPTABLE);
        }
        userService.createUser(user.getName(), user.getEmail(), user.getPassword());

       return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * @param userId user id
     * @return {@link ModelAndView} with {@link User} and view
     */
    @RequestMapping(value = "/user/{userId}",method = RequestMethod.GET)
    public ModelAndView userPage(@PathVariable Long userId){
        user = userService.findUserById(userId);
        ModelAndView userView = new ModelAndView("user");
        userView.addObject("User",user);
        return userView;
    }
}
