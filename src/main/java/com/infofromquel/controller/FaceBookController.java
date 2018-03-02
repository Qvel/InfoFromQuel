package com.infofromquel.controller;



import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.infofromquel.entity.EmailTemplates;
import com.infofromquel.entity.Event;
import com.infofromquel.entity.User;
import com.infofromquel.service.facebook.FacebookService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
public class FaceBookController {


    private static final Logger LOG = Logger.getLogger(FaceBookController.class);

    @Autowired
    private FacebookService facebookService;
    @Autowired
    private Environment env;

    @RequestMapping(value = "/facebook" , method = RequestMethod.GET)
    public ResponseEntity<List<Event>>  helloFacebook() {

        String query = "https://graph.facebook.com/search?q=Kyiv&type=event&center=50.45,30.52&distance=1000&limit=10&access_token=" + env.getProperty("facebook.access.token");
        JsonObject jsonObject = (new JsonParser()).parse(facebookService.getFacebookEvents(query)).getAsJsonObject();
        List<Event> events = facebookService.parseToEntity(jsonObject);
        LOG.trace(events);
        return ResponseEntity.ok(events);
    }

}
