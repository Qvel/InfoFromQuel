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


    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<User>> getUsers() {
        List<User> usList = userService.findAll();
        return new ResponseEntity<>(usList,HttpStatus.OK);

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
    public ResponseEntity<Object> createUser(
             @RequestParam String login,
             @RequestParam String email,
             @RequestParam String password,
             @RequestParam("file") MultipartFile multipartFile

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
        LOG.debug("size = " + multipartFile.getSize());
        LOG.debug("name = " + multipartFile.getOriginalFilename());
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
            multipartFile.transferTo(logo);
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


        //user = userService.createUser(user);
        //LOG.debug(user.getId() + " "  + user.getRoles());
        //mailService.sendHtmlEmail(user,EmailTemplates.REGISTRATION_TEMPLATE,EmailTemplates.REGISTRATION_SUBJECT);

        return ResponseEntity.ok(user);
    }

}
