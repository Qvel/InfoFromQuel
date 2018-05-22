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

    private final MailService mailService;

    @Autowired
    public UsersController(User user,UserService userService, MailService mailService) {
        this.userService = userService;
        this.mailService = mailService;
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
             @RequestParam String login,
             @RequestParam String email,
             @RequestParam String password,
             @RequestParam("file") MultipartFile userLogo

    ){

        LOG.debug("Create user with params = {} " +
                             " login " + login +
                             ";email " + email +
                             ";password " + password);
        user.setEmail(email);
        user.setName(login);
        user.setPassword(password);
        user.setExist(false);
        user.setRoles(Collections.singleton(new Role(2,"USER")));

        /*
        Work with files
        */
        LOG.debug("Work with logo");
        LOG.debug("size = " + userLogo.getSize());
        LOG.debug("name = " + userLogo.getOriginalFilename());
        String directory = "Q:" + File.separator + "work"
                          + File.separator + "InfoFromQuel" + File.separator + "infoFromQuel" + File.separator
                          + "src" + File.separator + "main" + File.separator
                          + "webapp" + File.separator + "resources" + File.separator + "logos"
                          + File.separator + user.hashCode() + ".jpg";
        File logo = new File(directory);
        LOG.debug(logo.getAbsolutePath());
        LOG.debug(logo.getPath());
        LOG.debug(logo.getParentFile().mkdirs());
        try {
            userLogo.transferTo(logo);
        }catch (IOException ex){
            LOG.error("ERROR while upload logo " + Arrays.toString(ex.getStackTrace()));
            return new ResponseEntity<>("Invalid File",HttpStatus.BAD_REQUEST);
        }
        /*
        if(userService.findUserByEmail(email)){
            LOG.debug("User Exist");
            user.setExist(true);
           return ResponseEntity.ok(user);
        }*/

        LOG.debug("User not exist");


        user = userService.createUser(user);
        //LOG.debug(user.getId() + " "  + user.getRoles());
        //mailService.sendHtmlEmail(user,EmailTemplates.REGISTRATION_TEMPLATE,EmailTemplates.REGISTRATION_SUBJECT);

        return ResponseEntity.ok(user);
    }

}
