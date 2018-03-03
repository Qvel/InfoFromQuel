
package com.infofromquel.service.facebook;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.infofromquel.entity.Event;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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

    @Autowired
    FacebookService facebookService;

    @Autowired
    private Environment env;

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
    /*
        Доделать остальные фиелды , детальнее
        https://developers.facebook.com/docs/graph-api/reference/event
     */
    @Override
    public List<Event> parseToEntity(JsonObject jsonObject) {

        LOG.trace("Start parsing");
        List<Event> listOfEvents = new ArrayList<>();
        JsonArray jarray = jsonObject.getAsJsonArray("data");
        jarray.forEach(
                jsonEvent -> {
                    event = new Event();
                    if(jsonEvent.getAsJsonObject().has("name")){
                        event.setName(jsonEvent.getAsJsonObject().get("name").getAsString());
                    }
                    if(jsonEvent.getAsJsonObject().has("description")){
                        event.setDescription(mysqlSafe(jsonEvent.getAsJsonObject().get("description").getAsString()));
                    }
                    if(jsonEvent.getAsJsonObject().has("id")){
                        event.setFaceBookId(jsonEvent.getAsJsonObject().get("id").getAsString());
                        String query = "https://graph.facebook.com/" + jsonEvent.getAsJsonObject().get("id").getAsString() + "?fields=id,name,cover&access_token=" + env.getProperty("facebook.access.token");
                        JsonObject image = (new JsonParser()).parse(facebookService.getFacebookEvents(query)).getAsJsonObject();
                        if(image.getAsJsonObject().has("cover")){
                            if(image.getAsJsonObject().get("cover").getAsJsonObject().has("source")){
                                event.setSourceImageLink(image.getAsJsonObject().get("cover").getAsJsonObject().get("source").getAsString());
                            }
                        }
                    }
                    if(jsonEvent.getAsJsonObject().has("start_time")){
                        event.setStartDate(parseDate(jsonEvent.getAsJsonObject().get("start_time").getAsString()));
                    }
                    if(jsonEvent.getAsJsonObject().has("end_time")){
                        event.setEndDate(parseDate(jsonEvent.getAsJsonObject().get("end_time").getAsString()));
                    }
                    if(jsonEvent.getAsJsonObject().has("place")){
                        if(jsonEvent.getAsJsonObject().get("place").getAsJsonObject().has("name")){
                            event.setPlace(jsonEvent.getAsJsonObject().get("place").getAsJsonObject().get("name").getAsString());
                        }
                        if(jsonEvent.getAsJsonObject().get("place").getAsJsonObject().has("location")) {
                            JsonObject location = jsonEvent.getAsJsonObject().get("place").getAsJsonObject().get("location").getAsJsonObject();
                            if (location.has("latitude")) {
                                event.setLatitude(location.get("latitude").getAsString());
                            }
                            if (location.has("longitude")) {
                                event.setLongitude(location.get("longitude").getAsString());
                            }
                            if (location.has("street")) {
                                event.setStreet(location.get("street").getAsString());
                            }
                        }
                    }

                    listOfEvents.add(event);
                }
        );

        return listOfEvents;
    }

    private String parseDate(String facebookDateFormat){
        return facebookDateFormat.split("T")[0] + "  " + facebookDateFormat.split("T")[1].substring(0,5);
    }

    private String mysqlSafe(String input) {
        if (input == null) return null;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            if (i < (input.length() - 1)) { // Emojis are two characters long in java, e.g. a rocket emoji is "\uD83D\uDE80";
                if (Character.isSurrogatePair(input.charAt(i), input.charAt(i + 1))) {
                    i += 1; //also skip the second character of the emoji
                    continue;
                }
            }
            sb.append(input.charAt(i));
        }

        return sb.toString();
    }
}

