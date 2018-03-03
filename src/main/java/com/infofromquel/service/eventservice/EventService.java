package com.infofromquel.service.eventservice;

import com.infofromquel.entity.Event;

import java.util.List;

public interface EventService {

    void createEventsFromFacebook(List<Event> events);

    List<Event> addToBdFacebookEvents(String query);

    List<Event> getAllEvents();

}