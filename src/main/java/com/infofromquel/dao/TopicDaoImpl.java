package com.infofromquel.dao;

import com.infofromquel.entity.Topic;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sun.rmi.runtime.Log;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * DAO repository for {@link Topic}
 * @author Serhii Zhuravlov
 */
@Repository
@Transactional
public class TopicDaoImpl implements TopicDao{

    private static final Logger LOG = Logger.getLogger(TopicDaoImpl.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public TopicDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * find all topics
     * @return list of {@link Topic}
     */
    @Override
    public List<Topic> findAllTopics() {
        LOG.debug("TopicDaoImpl.findAllTopics = {} ");
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Topic> criteriaQuery = criteriaBuilder.createQuery(Topic.class);
        Root<Topic> root = criteriaQuery.from(Topic.class);
        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("date")));
        List<Topic> topics  = session.createQuery(criteriaQuery).getResultList();
        topics.forEach(LOG::debug);
        return topics;
    }

    /**
     * create new Topic
     * @param topic {@link Topic}
     * @return new {@link Topic}
     */
    @Override
    public Topic createTopic(Topic topic) {
        LOG.debug("TopicDaoImpl.createTopic = {} " + topic);
        Session session = sessionFactory.getCurrentSession();
        session.save(topic);
        return topic;
    }

    /**
     * update entity {@link Topic}
     * @param topic {@link Topic}
     * @return {@link Topic}
     */
    @Override
    public Topic updateTopic(Topic topic) {
        LOG.debug("TopicDaoImpl.updateTopic = {}" + topic);
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaUpdate<Topic> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Topic.class);
        Root<Topic> root = criteriaUpdate.from(Topic.class);
        if(topic.getTitle() != null){
            criteriaUpdate.set(root.get("title"),topic.getTitle());
        }
        if(topic.getBody() != null){
            criteriaUpdate.set(root.get("body"),topic.getBody());
        }
        criteriaUpdate.where(criteriaBuilder.equal(root.get("id"),topic.getId()));
        session.createQuery(criteriaUpdate).executeUpdate();
        return topic;
    }

    /**
     * find topics that have such title
     * @param title title of {@link Topic}
     * @return list of {@link Topic}
     */
    @Override
    public List<Topic> findTopicsByTitle(String title) {
        LOG.debug("TopicDaoImpl.findTopicsByTitle = {} " + title);
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Topic> criteriaQuery = criteriaBuilder.createQuery(Topic.class);
        Root<Topic> root = criteriaQuery.from(Topic.class);
        criteriaQuery.where(criteriaBuilder.like(root.get("title"),"%" + title + "%"));
        List<Topic> topics;
        try {
             topics = session.createQuery(criteriaQuery).getResultList();
        }catch (NoResultException e){
            LOG.debug("Empty list");
            return null;
        }
        topics.forEach(LOG::debug);
        return topics;
    }

    /**
     *find topics that have such body
     * @param body body of {@link Topic}
     * @return list of {@link Topic}
     */
    @Override
    public List<Topic> findTopicsByBody(String body) {
        LOG.debug("TopicDaoImpl.findTopicsByBody = {} " + body);
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Topic> criteriaQuery = criteriaBuilder.createQuery(Topic.class);
        Root<Topic> root = criteriaQuery.from(Topic.class);
        criteriaQuery.where(criteriaBuilder.like(root.get("body"),"%" + body + "%"));
        List<Topic> topics;
        try{
            topics = session.createQuery(criteriaQuery).getResultList();
        }catch (NoResultException e){
            LOG.debug("Empty list");
            return null;
        }
        return topics;
    }

    /**
     * find {@link Topic} by id
     * @param id topic id
     * @return {@link Topic}
     */
    @Override
    public Topic findTopicById(Long id) {
        LOG.debug("TopicDaoImpl.findTopicById = {} " + id);
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Topic> criteriaQuery = criteriaBuilder.createQuery(Topic.class);
        Root<Topic> root = criteriaQuery.from(Topic.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"),id));
        Topic topic = session.createQuery(criteriaQuery).getSingleResult();
        LOG.debug("result = {} " + topic);
        return topic;
    }
}
