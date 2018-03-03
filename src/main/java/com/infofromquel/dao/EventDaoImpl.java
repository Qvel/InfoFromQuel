package com.infofromquel.dao;

import com.infofromquel.entity.Event;
import com.infofromquel.mapper.EventMapper;
import com.infofromquel.mapper.UserMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EventDaoImpl implements EventDao{

    private static final Logger LOG = Logger.getLogger(EventDaoImpl.class);

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public EventDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Autowired
    private Event injecrtedEvent;

    private String INSERT_EVENT =   "insert into event " +
                                    "(ev_name,facebookid,place,street,description,longitude,latitude,source_image,start_date,end_date) " +
                                     "values(?,?,?,?,?,?,?,?,?,?)";

    private String FIND_EVENT_BY_FACEBOOK_ID = "select id,ev_name,facebookid,place,street,description,longitude,latitude,source_image,start_date,end_date " +
                                               "from event " +
                                               "where facebookid = ? ";

    private String FIND_ALL_EVENTS = "select id,ev_name,facebookid,place,street,description,longitude,latitude,source_image,start_date,end_date " +
                                     "from event " ;

    @Override
    public Event createEvent(Event event) {
        LOG.trace(event.getFaceBookId());
        LOG.trace("Inserting Event with params");
        injecrtedEvent = getEventByFacebookId(event.getFaceBookId());
        if(injecrtedEvent == null) {
             jdbcTemplate.update(INSERT_EVENT,
                            event.getName(), event.getFaceBookId(), event.getPlace(), event.getStreet(),
                            event.getDescription(), event.getLongitude(), event.getLatitude(), event.getSourceImageLink(),
                            event.getStartDate(), event.getEndDate());
            injecrtedEvent = getEventByFacebookId(event.getFaceBookId());
        }

        return injecrtedEvent;
    }

    @Override
    public Event getEventByFacebookId(String id) {
        injecrtedEvent = null;
        LOG.debug("getEventByFacebookId");
            jdbcTemplate.query(FIND_EVENT_BY_FACEBOOK_ID,(resultSet, rowNum) ->{
            injecrtedEvent = new Event();
            injecrtedEvent.setId(resultSet.getInt("id"));
            injecrtedEvent.setName(resultSet.getString("ev_name"));
            injecrtedEvent.setFaceBookId(resultSet.getString("facebookid"));
            injecrtedEvent.setPlace(resultSet.getString("place"));
            injecrtedEvent.setStreet(resultSet.getString("street"));
            injecrtedEvent.setDescription(resultSet.getString("description"));
            injecrtedEvent.setLongitude(resultSet.getString("longitude"));
            injecrtedEvent.setLongitude(resultSet.getString("latitude"));
            injecrtedEvent.setSourceImageLink(resultSet.getString("source_image"));
            injecrtedEvent.setStartDate(resultSet.getString("start_date"));
            injecrtedEvent.setEndDate(resultSet.getString("end_date"));

            return injecrtedEvent;
        },id);
        LOG.debug("injecrtedEvent = " + injecrtedEvent);
        if(injecrtedEvent == null){
            return null;
        }
        return injecrtedEvent;
    }

    @Override
    public Event getEventById(Integer id) {
        return null;
    }

    @Override
    public List<Event> getAllEvents() {
        return jdbcTemplate.query(FIND_ALL_EVENTS,new EventMapper());
    }
}
