package com.infofromquel.dao;

import com.infofromquel.entity.Topic;

import java.util.List;

/**
 * Interface for API with entity {@link Topic}
 * @author Serhii Zhuravlov
 */
public interface TopicDao {

    List<Topic> findAllTopics();

    Topic createTopic(Topic topic);

    Topic updateTopic(Topic topic);

    List<Topic> findTopicsByTitle(String title);

    List<Topic> findTopicsByBody(String body);

}
