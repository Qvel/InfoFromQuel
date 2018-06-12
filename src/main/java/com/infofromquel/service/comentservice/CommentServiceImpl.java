package com.infofromquel.service.comentservice;


import com.infofromquel.dao.commentdao.CommentDao;
import com.infofromquel.entity.Comment;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for entity {@link Comment}
 * operations {create , update , delete}
 */
@Service
public class CommentServiceImpl implements CommentService{

    private static final Logger LOG = Logger.getLogger(CommentServiceImpl.class);

    private final CommentDao commentDao;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    /**
     * creates new Comment
     * @param comment {@link Comment}
     * @return {@link Comment}
     */
    @Override
    public Comment addComment(Comment comment) {
        LOG.debug("CommentServiceImpl.addComment = {} " + comment);
        return commentDao.addComment(comment);
    }

    /**
     * update Comment
     * @param comment {@link Comment}
     * @return {@link Comment}
     */
    @Override
    public Comment updateComment(Comment comment) {
        LOG.debug("CommentServiceImpl.updateComment = {} " + comment);
        return commentDao.updateComment(comment);
    }

    /**
     * delete Comment
     * @param comment {@link Comment}
     */
    @Override
    public void deleteComment(Comment comment) {
        LOG.debug("CommentServiceImpl.deleteComment = {} " + comment);
        commentDao.deleteComment(comment);
    }
}
