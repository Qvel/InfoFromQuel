package com.infofromquel.service.eventservice;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.infofromquel.dao.EventDao;
import com.infofromquel.entity.Event;
import com.infofromquel.service.facebook.FacebookService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService{

    private static final Logger LOG = Logger.getLogger(EventServiceImpl.class);

    @Autowired
    private EventDao eventDao;

    @Autowired
    private FacebookService facebookService;

    @Autowired
    private EventService eventService;

    @Override
    public void createEventsFromFacebook(List<Event> events) {

        events.forEach(
                event -> eventDao.createEvent(event)
        );


    }

    @Override
    public List<Event> addToBdFacebookEvents(String query) {

        JsonObject jsonObject = (new JsonParser()).parse(facebookService.getFacebookEvents(query)).getAsJsonObject();
        List<Event> events = facebookService.parseToEntity(jsonObject);
        LOG.trace(events);
        eventService.createEventsFromFacebook(events);


        return events;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventDao.getAllEvents();
    }
}
