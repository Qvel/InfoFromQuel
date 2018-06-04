package com.infofromquel.controller;

import com.infofromquel.service.topicservice.TopicService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.infofromquel.entity.Topic;
import org.springframework.web.servlet.ModelAndView;

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
    //todo: Cotroller with Path Variable and new jsp for searchResult and redirect to this controller in front-end
    @RequestMapping(value = "/findTopicByTitle",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Topic>> getTopicsWithTitle(@RequestParam("title") String title){
        LOG.debug("TopicController.getTopicsWithTitle = {} " + title);
        List<Topic> topics = topicService.findTopicsByTitle(title);
        if(topics == null){
            topics = topicService.findTopicsByBody(title);
        }
        return ResponseEntity.ok(topics);
    }

    @RequestMapping(value = "/search_{title}",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView searchPage(@PathVariable String title){
        LOG.debug("TopicController.searchPage = {} " + title);
        ModelAndView searchPage = new ModelAndView("search");
        searchPage.addObject("title",title);
        List<Topic> topics = topicService.findTopicsByTitle(title);
        if(topics == null){
            topics = topicService.findTopicsByBody(title);
            searchPage.addObject("topics",topics);
        }
        return searchPage;
    }
}
