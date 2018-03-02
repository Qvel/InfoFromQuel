
package com.infofromquel.service.facebook;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.infofromquel.entity.Event;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class FacebookServiceImpl implements FacebookService{

    private static final Logger LOG = Logger.getLogger(FacebookServiceImpl.class);

    @Autowired
    Event event;

    @Override
    public String getFacebookEvents(String query) {

        LOG.trace("In facebook method");
        String allResponse = "";

        try {
            URL obj = new URL(query);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "text/html; charset=utf-8");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            allResponse = response.toString();
        }catch(Exception e){
            System.out.println("Have exeption in url method getBitok");
        }

        return allResponse;
    }

    @Override
    public List<Event> parseToEntity(JsonObject jsonObject) {

        LOG.trace("Start parsing");
        List<Event> listOfEvents = new ArrayList<>();
        JsonArray jarray = jsonObject.getAsJsonArray("data");
        jarray.forEach(
                jsonEvent -> {
                    event = new Event();
                    event.setName(jsonEvent.getAsJsonObject().get("name").getAsString());
                    event.setDescription(jsonEvent.getAsJsonObject().get("description").getAsString());
                    event.setFaceBookId(jsonEvent.getAsJsonObject().get("id").getAsString());
                    listOfEvents.add(event);
                }
        );

        return listOfEvents;
    }
}

