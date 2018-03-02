package com.infofromquel.controller;



import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.infofromquel.entity.Event;
import com.infofromquel.entity.User;
import com.infofromquel.service.facebook.FacebookService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
public class FaceBookController {


    private static final Logger LOG = Logger.getLogger(FaceBookController.class);
    @Autowired
    FacebookService facebookService;

    @Autowired
    Event event;

    @RequestMapping(value = "/facebook" , method = RequestMethod.GET)
    public ResponseEntity<List<Event>>  helloFacebook() {

        String accessToken = "EAACEdEose0cBAOK16Hbd3EZADwkAJoIM5dJRCDdggIsQKaq1P0ZBSLQuOZBxJHL7H6vaZC2wXfPTj2pMAi03AZAKl2dcolW8u5KLLwg11NDq9WbPZC4i6VWZBbU1CyBywIdIboEXeHEPkCNwAezjKiFe05C2BAuZBTJZApGOUEX7RI7eJz3sSGymzxyAktsZCik1RT8G7tUiuHhwZDZD";

        String query = "https://graph.facebook.com/search?q=Kyiv&type=event&center=50.45,30.52&distance=1000&limit=10&access_token=" + accessToken;
        JsonObject jsonObject = (new JsonParser()).parse(facebookService.getFacebookEvents(query)).getAsJsonObject();
        List<Event> events = facebookService.parseToEntity(jsonObject);
        LOG.trace(events);
        return ResponseEntity.ok(events);
    }

}
