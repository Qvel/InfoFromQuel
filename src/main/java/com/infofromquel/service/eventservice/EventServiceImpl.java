package com.infofromquel.service.eventservice;


import com.infofromquel.dao.EventDao;
import com.infofromquel.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService{

    @Autowired
    private EventDao eventDao;

    @Override
    public void createEventsFromFacebook(List<Event> events) {

        events.forEach(
                event -> eventDao.createEvent(event)
        );


    }
}
