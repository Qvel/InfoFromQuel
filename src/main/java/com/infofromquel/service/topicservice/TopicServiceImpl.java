package com.infofromquel.service.topicservice;

import com.infofromquel.dao.topicdao.TopicDao;
import com.infofromquel.entity.Topic;
import com.infofromquel.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Service for {@link Topic}
 */
@Service
public class TopicServiceImpl implements TopicService{

    private static final Logger LOG = Logger.getLogger(TopicServiceImpl.class);

    private final TopicDao topicDao;

    @Autowired
    public TopicServiceImpl(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    /**
     * find all topics
     * @return list of {@link Topic}
     */
    @Override
    public List<Topic> findAllTopics() {
        LOG.debug("TopicServiceImpl.findAllTopics = {}");
        return topicDao.findAllTopics();
    }

    /**
     * creates new topic
     * @param topic {@link Topic}
     * @return new {@link Topic}
     */
    @Override
    public Topic createTopic(Topic topic) {
        LOG.debug("TopicServiceImpl.createTopic = {}" + topic);
        topic.setDate(new Date());
        return topicDao.createTopic(topic);
    }

    /**
     * update topic
     * @param topic {@link Topic}
     * @return {@link Topic}
     */
    @Override
    public Topic updateTopic(Topic topic) {
        LOG.debug("TopicServiceImpl.updateTopic = {} " + topic);
        return topicDao.updateTopic(topic);
    }

    /**
     * find topics that have such title
     * @param title title of {@link Topic}
     * @return list of {@link Topic}
     */
    @Override
    public List<Topic> findTopicsByTitle(String title) {
        LOG.debug("TopicServiceImpl.findTopicsByTitle = {} " + title);
        return topicDao.findTopicsByTitle(title);
    }
    /**
     *find topics that have such body
     * @param body body of {@link Topic}
     * @return list of {@link Topic}
     */
    @Override
    public List<Topic> findTopicsByBody(String body) {
        LOG.debug("TopicServiceImpl.findTopicsByBody = {} " + body);
        return topicDao.findTopicsByBody(body);
    }

    /**
     * find topic with id
     * @param id topic id
     * @return {@link Topic}
     */
    @Override
    public Topic findTopicById(Long id) {
        LOG.debug("TopicServiceImpl.findTopicById = {}" + id);
        return topicDao.findTopicById(id);
    }

    /**
     * find topics posed by user
     * @param user {@link User}
     * @return list of {@link Topic}
     */
    @Override
    public List<Topic> findTopicsByUser(User user) {
        LOG.debug("TopicsServiceImpl = {}" + user);
        return topicDao.findTopicsByUser(user);
    }

    /**
     * check topic for author
     * @param topic {@link Topic}
     * @param user {@link User}
     * @return boolean
     */
    @Override
    public boolean checkIsTopicPosedByUser(Topic topic, User user) {
        LOG.debug("TopicServiceImpl = {} , user = {} " + user + ", topic = {} " +topic);
        List<Topic> userTopics = findTopicsByUser(user);
        boolean result = false;
        if(userTopics == null){
            return false;
        }else{
            for(Topic userTopic : userTopics){
                if(Objects.equals(userTopic.getId(),topic.getId())){
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Pagination of {@link Topic} order by date
     * @param from from what number
     * @param to to what number
     * @return list of {@link Topic}
     */
    @Override
    public List<Topic> getTopicsForLimit(Long from, Long to) {
        LOG.debug("TopicServiceImpl = {} " + from + ", " + to);
        List<Topic> topics = findAllTopics();
        //todo filter to list of topics with correct values from and to order by date
        return null;
    }

    /**
     * like Topic
     * @param topic {@link Topic}
     */
    @Override
    public void likeTopic(Topic topic) {
        LOG.debug("topicServiceImpl.likeTopic = {} " + topic);
        topicDao.likeTopic(topic);
    }

    /**
     * dislike Topic
     * @param topic {@link Topic}
     */
    @Override
    public void dislikeTopic(Topic topic) {
        LOG.debug("topicServiceImpl.dislikeTopic = {} " + topic);
        topicDao.dislikeTopic(topic);
    }
}
