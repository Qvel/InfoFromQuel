package com.infofromquel.service.topicservice;

import com.infofromquel.dao.TopicDao;
import com.infofromquel.entity.Topic;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
}
