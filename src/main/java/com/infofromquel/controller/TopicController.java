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

    /**
     * get all topics sorted by date
     * @return list of {@link Topic}
     */
    @RequestMapping(value ="/getAllTopics",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Topic>> getAllTopics(){
        LOG.debug("TopicController.getAllTopics = {}");
        return ResponseEntity.ok(topicService.findAllTopics());
    }

    /**
     * creates topic in system
     * @param topic {@link Topic}
     * @return {@link Topic}
     */
    @RequestMapping(value ="/user/createTopic",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Topic> createTopic(@RequestBody Topic topic){
        LOG.debug("TopicController.createTopic = {} " + topic);
        return ResponseEntity.ok(topicService.createTopic(topic));
    }

    /**
     * update topic in the system
     * @param topic {@link Topic}
     * @return {@link Topic}
     */
    @RequestMapping(value = "/user/updateTopic",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Topic> updateTopic(@RequestBody Topic topic){
        LOG.debug("TopicController.updateTopic = {} " + topic);
        return ResponseEntity.ok(topicService.updateTopic(topic));
    }

    /**
     * find {@link Topic} by title and body (if list was empty)
     * @param title title of {@link Topic}
     * @return list of {@link Topic}
     */
    @RequestMapping(value = "/findTopicByTitle",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<Topic>> getTopicsWithTitle(@RequestParam String title){
        LOG.debug("TopicController.getTopicsWithTitle = {} " + title);
        List<Topic> topics = topicService.findTopicsByTitle(title);
        if(topics == null || topics.isEmpty()){
            topics = topicService.findTopicsByBody(title);
        }
        return ResponseEntity.ok(topics);
    }

    /**
     * return page and title of topics
     * @param title query for searching
     * @return search page and title as param
     */
    @RequestMapping(value = "/search_{title}",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView searchPage(@PathVariable String title){
        LOG.debug("TopicController.searchPage = {} " + title);
        ModelAndView searchPage = new ModelAndView("search");
        searchPage.addObject("title",title);
        return searchPage;
    }

    @RequestMapping(value ="/topic/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView topicPage(@PathVariable Long id){
        LOG.debug("TopicController.topicPage = {} " + id);
        ModelAndView topicPage = new ModelAndView("topic");
        topicPage.addObject("Topic",topicService.findTopicById(id));
        return topicPage;
    }
}
