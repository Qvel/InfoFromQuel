package com.infofromquel.controller;

import com.infofromquel.service.topicservice.TopicService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.infofromquel.entity.Topic;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

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
     * @return {@link HttpStatus} if success then ok with {@link Topic} , else Bad Request
     */
    @RequestMapping(value ="/user/createTopic",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity createTopic(@RequestBody Topic topic,Principal principal){
        LOG.debug("TopicController.createTopic = {} " + topic);
        if(principal != null){
            if(Objects.equals(principal.getName(),topic.getUser().getEmail())){
                return ResponseEntity.ok(topicService.createTopic(topic));
            }
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    /**
     * update topic in the system
     * @param topic {@link Topic}
     * @return {@link Topic}
     */
    @RequestMapping(value = "/user/updateTopic",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity updateTopic(@RequestBody Topic topic,Principal principal){
        LOG.debug("TopicController.updateTopic = {} " + topic);
        if(principal != null){
            if(Objects.equals(principal.getName(),topic.getUser().getEmail())){
                if(topicService.checkIsTopicPosedByUser(topic,topic.getUser())){
                    return ResponseEntity.ok(topicService.updateTopic(topic));
                }
            }
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
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

    /**
     * api for likes to topics
     * @param topic {@link Topic}
     * @return {@link HttpStatus} ok
     */
    @RequestMapping(value = "/user/like",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity likeTopic(@RequestBody Topic topic){
        LOG.debug("TopicController.likeTopic = {} " + topic);
        //todo Check User for already likes current topic
        topicService.likeTopic(topic);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * api for dislike topics
     * @param topic {@link Topic}
     * @return {@link HttpStatus} ok
     */
    @RequestMapping(value = "/user/dislikes",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity dislikeTopic(@RequestBody Topic topic){
        LOG.debug("TopicController.dislikeTopic = {} " + topic);
        //todo Check User for already dislikes topic
        topicService.dislikeTopic(topic);
        return new ResponseEntity(HttpStatus.OK);
    }
}
