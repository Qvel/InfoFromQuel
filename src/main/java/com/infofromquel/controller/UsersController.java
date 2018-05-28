package com.infofromquel.controller;



import com.infofromquel.entity.User;
import com.infofromquel.service.userservice.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import java.io.IOException;
import java.net.MalformedURLException;
import java.security.Principal;
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
    public ResponseEntity<User> getUserById(
            @RequestBody Long id
    ) {
        user = userService.findUserById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * @param email email of user
     * @return user with such email
     */
    @RequestMapping(value = "/getUserByEmail", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Long> getUserByEmail(
            @RequestParam String email
    ) {
        user = userService.getUserByEmail(email);
        LOG.debug("User = {} " + user);
        return ResponseEntity.ok(user.getId());
    }

    /**
     * Api for updating logo of  {@link User}
     * @param id id of user
     * @param userLogo photo of User
     * @return new user {@link User}
     */
    @RequestMapping(value = "/updateAvatar" ,method = RequestMethod.POST)
    public ResponseEntity<Object> updateUserAvatar(
             @RequestParam("id") Long id,
             @RequestParam("file") MultipartFile userLogo

    ){
        LOG.debug("UsersCotroller.updateUserAvatar = {} " + id);
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
    public ModelAndView userPage(@PathVariable Long userId,Principal principal){
        LOG.debug("UsersController.userPage = {} " + userId);
        user = userService.findUserById(userId);
        if(principal != null) {
            if (principal.getName().equals(user.getEmail())) {
                ModelAndView userView = new ModelAndView("user");
                userView.addObject("User", user);
                return userView;
            }
        }

        return new ModelAndView("permissionError");
    }

    @RequestMapping(value = "/user/getLogo",method = RequestMethod.GET)
    public ResponseEntity getUserIcon(@RequestParam("fileName") String fileName){
        try {
            Resource file = userService.loadAsResource(fileName);
            LOG.debug("Resource = " + file);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        }catch (MalformedURLException e){
            //LOG.error("MalformedURLException " + Arrays.toString(e.getStackTrace()));
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/user/updateUser" , method = RequestMethod.POST)
    public ResponseEntity<User> updateUser(@RequestBody User user){
        LOG.debug("UsersController.updateUser = {}" + user);
        userService.updateUser(user);
        return ResponseEntity.ok(user);
    }

}
