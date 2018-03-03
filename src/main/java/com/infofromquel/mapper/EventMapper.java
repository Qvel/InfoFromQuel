package com.infofromquel.mapper;

import com.infofromquel.entity.Event;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventMapper implements RowMapper<Event> {

    private static final Logger LOG = Logger.getLogger(EventMapper.class);

    @Override
    public Event mapRow(ResultSet resultSet, int i) throws SQLException {

        LOG.debug("Event mapper start");

        Event event = new Event();
        event.setId(resultSet.getInt("id"));
        event.setName(resultSet.getString("ev_name"));
        event.setFaceBookId(resultSet.getString("facebookid"));
        event.setPlace(resultSet.getString("place"));
        event.setStreet(resultSet.getString("street"));
        event.setDescription(resultSet.getString("description"));
        event.setLongitude(resultSet.getString("longitude"));
        event.setLongitude(resultSet.getString("latitude"));
        event.setSourceImageLink(resultSet.getString("source_image"));
        event.setStartDate(resultSet.getString("start_date"));
        event.setEndDate(resultSet.getString("end_date"));

        return event;
    }
}
