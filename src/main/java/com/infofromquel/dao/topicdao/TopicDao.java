package com.infofromquel.dao.topicdao;

import com.infofromquel.entity.Topic;
import com.infofromquel.entity.User;

import java.util.List;

/**
 * Interface for API with entity {@link Topic}
 * @author Serhii Zhuravlov
 */
public interface TopicDao {

    List<Topic> findAllTopics();

    Topic findTopicById(Long id);

    Topic createTopic(Topic topic);

    Topic updateTopic(Topic topic);

    List<Topic> findTopicsByTitle(String title);

    List<Topic> findTopicsByBody(String body);

    List<Topic> findTopicsByUser(User user);

    void likeTopic(Topic topic);

    void dislikeTopic(Topic topic);
}
