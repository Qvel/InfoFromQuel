package com.infofromquel.dao.commentdao;

import com.infofromquel.entity.Comment;

/**
 * Dao for functional with entity {@link Comment}
 */
public interface CommentDao {

    Comment addComment(Comment comment);
    Comment updateComment(Comment comment);
    void deleteComment(Comment comment);

}
