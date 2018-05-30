package com.infofromquel.controller;

import com.infofromquel.service.topicservice.TopicService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.infofromquel.entity.Topic;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Api for get/set/update {@link Topic}
 */
@Controller
public class TopicController {

    private static final Logger LOG = Logger.getLogger(TopicController.class);

    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @RequestMapping(value ="/getAllTopics",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Topic>> getAllTopics(){
        LOG.debug("TopicController.getAllTopics = {}");
        return ResponseEntity.ok(topicService.findAllTopics());
    }

    @RequestMapping(value ="/user/createTopic",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Topic> createTopic(@RequestBody Topic topic){
        LOG.debug("TopicController.createTopic = {} " + topic);
        return ResponseEntity.ok(topicService.createTopic(topic));
    }

    @RequestMapping(value = "/user/updateTopic",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Topic> updateTopic(@RequestBody Topic topic){
        LOG.debug("TopicController.updateTopic = {} " + topic);
        return ResponseEntity.ok(topicService.updateTopic(topic));
    }
}
