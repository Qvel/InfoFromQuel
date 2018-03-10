package com.infofromquel.controller;


import com.infofromquel.entity.EmailTemplates;
import com.infofromquel.entity.Filter;
import com.infofromquel.entity.User;
import com.infofromquel.service.mail.MailService;
import com.infofromquel.service.userservice.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UsersController {

    private static final Logger LOG = Logger.getLogger(UsersController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private User user;

    @Autowired
    private MailService mailService;


    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<User>> getUsers() {


        List<User> usList = userService.findAll();
       // Filter filter = new Filter();
       // filter.setName("Footbool");
       // userService.checkHibernet(filter);
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

        LOG.debug("Create user with params = {} " +
                             " login " + login +
                             ";email " + email +
                             ";password " + password);


        /*
        if(userService.findUserByEmail(email)){
            LOG.debug("User Exist");
            user.setExist(true);
           return ResponseEntity.ok(user);
        }*/

        LOG.debug("User not exist");

        user.setEmail(email);
        user.setName(login);
        user.setPassword(password);
        user = userService.createUser(user);
        LOG.debug(user.getId() + " "  + user.getRoles());
        //mailService.sendHtmlEmail(user,EmailTemplates.REGISTRATION_TEMPLATE,EmailTemplates.REGISTRATION_SUBJECT);

        return ResponseEntity.ok(user);
    }

}
