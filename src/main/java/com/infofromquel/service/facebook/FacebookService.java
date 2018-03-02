
package com.infofromquel.service.facebook;


import com.google.gson.JsonObject;
import com.infofromquel.entity.Event;

import java.util.List;

public interface FacebookService {


    String getFacebookEvents(String url);

    List<Event> parseToEntity(JsonObject jsonObject);
}

