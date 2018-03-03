package com.infofromquel.controller;


import com.infofromquel.entity.Event;
import com.infofromquel.service.eventservice.EventService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class EventController {


    private static final Logger LOG = Logger.getLogger(UsersController.class);

    @Autowired
    private Environment env;
    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/addEventsToBd" , method = RequestMethod.GET)
    public ResponseEntity<List<Event>>  helloFacebook() {

        LOG.debug("adding events to bd");

        String query = "https://graph.facebook.com/search?q=Kyiv&type=event&center=50.45,30.52&distance=1000&limit=10&access_token=" + env.getProperty("facebook.access.token");
        List<Event> events = eventService.addToBdFacebookEvents(query);

        return ResponseEntity.ok(events);
    }

    @RequestMapping(value = "/facebook" , method = RequestMethod.GET)
    public ResponseEntity<List<Event>>  getEvents() {

        List<Event> events = eventService.getAllEvents();

        return ResponseEntity.ok(events);
    }
}
