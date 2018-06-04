package com.infofromquel.service.topicservice;

import com.infofromquel.entity.Topic;

import java.util.List;

/**
 * Api for operation with {@link Topic}
 */
public interface TopicService {

    List<Topic> findAllTopics();

    Topic createTopic(Topic topic);

    Topic updateTopic(Topic topic);

    List<Topic> findTopicsByTitle(String title);

    List<Topic> findTopicsByBody(String body);

    Topic findTopicById(Long id);
}
