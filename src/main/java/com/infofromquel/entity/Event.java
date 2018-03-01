package com.infofromquel.entity;


import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Event {

    public Event() {
    }

    public Event(List<User> eventUsers, String name, String date, String description, String longitude, String latitude) {
        this.eventUsers = eventUsers;
        this.name = name;
        this.date = date;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    private List<User> eventUsers;

   private String name;

   private String date;

   private String description;

   private String longitude;

   private String latitude;

    public List<User> getEventUsers() {
        return eventUsers;
    }

    public void setEventUsers(List<User> eventUsers) {
        this.eventUsers = eventUsers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Event{");
        sb.append("eventUsers=").append(eventUsers);
        sb.append(", name='").append(name).append('\'');
        sb.append(", date='").append(date).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", longitude='").append(longitude).append('\'');
        sb.append(", latitude='").append(latitude).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
