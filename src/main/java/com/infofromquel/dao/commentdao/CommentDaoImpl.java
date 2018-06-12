package com.infofromquel.dao.commentdao;

import com.infofromquel.entity.Comment;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

/**
 * Implementation of {@link CommentDao}
 * dao for entity {@link Comment}
 */
@Repository
@Transactional
public class CommentDaoImpl implements CommentDao {

    private static final Logger LOG = Logger.getLogger(CommentDaoImpl.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public CommentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * creates new Comment
     * @param comment {@link Comment}
     * @return {@link Comment}
     */
    @Override
    public Comment addComment(Comment comment) {

        LOG.debug("CommentDaoImpl.addComment = {} " + comment);

        Session session = sessionFactory.getCurrentSession();
        session.save(comment);

        return comment;
    }

    /**
     * update Comment
     * @param comment {@link Comment}
     * @return {@link Comment}
     */
    @Override
    public Comment updateComment(Comment comment) {
        LOG.debug("CommentDaoImpl.updateComment = {} " + comment);
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
        CriteriaUpdate<Comment> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Comment.class);
        Root<Comment> root = criteriaUpdate.from(Comment.class);
        if(comment.getBody() != null){
            criteriaUpdate.set(root.get("body"),comment.getBody());
        }
        session.createQuery(criteriaUpdate).executeUpdate();

        return comment;
    }

    /**
     * delete Comment
     * @param comment {@link Comment}
     */
    @Override
    public void deleteComment(Comment comment) {
        LOG.debug("CommentDaoImpl.deleteComment = {} " + comment);
        sessionFactory.getCurrentSession().delete(comment);
    }
}
