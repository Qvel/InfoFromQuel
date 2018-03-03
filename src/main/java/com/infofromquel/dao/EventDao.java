package com.infofromquel.dao;

import com.infofromquel.entity.Event;

import java.util.List;

public interface EventDao {


    Event createEvent(Event event);

    Event getEventById(Integer id);

    Event getEventByFacebookId(String id);

    List<Event> getAllEvents();



}
