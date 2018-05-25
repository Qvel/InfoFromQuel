package com.infofromquel.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.log4j.Logger;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Controller for returning jsp files
 * @author Serhii Zhuravlov
 */
@Controller
public class MainController {

    private static final Logger LOG = Logger.getLogger(MainController.class);

    /**
     * @param principal {@link Principal}
     * @return index page
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage(Principal principal) {
        LOG.debug("Hi");

        if(principal != null){
            LOG.debug("Welcome to page index " + principal.getName());
        }

        return "index";
    }

    /**
     * @param principal {@link Principal}
     * @return admin page
     */
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAdmin(Principal principal){
        if(principal != null){
            LOG.debug("Welcome to page index " + principal.getName() + " at : " + new Date());
        }
        return "admin";
    }

    /**
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @return logout status
     */
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){

            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        LOG.debug("Successfully logout ");
        return "redirect:/?logout";
    }

    /**
     * @return registration page
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String mainPage() {
        return "registration";
    }

    /**
     * @return permissionError page
     */
    @RequestMapping(value = "/permissionError", method = RequestMethod.GET)
    public String permissionError() {
        return "permissionError";
    }

    /**
     * @return login page
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/index";
    }


}
