package com.infofromquel.service.comentservice;


import com.infofromquel.entity.Comment;

/**
 * Service for entity {@link Comment}
 */
public interface CommentService {

    Comment addComment(Comment comment);
    Comment updateComment(Comment comment);
    void deleteComment(Comment comment);

}
