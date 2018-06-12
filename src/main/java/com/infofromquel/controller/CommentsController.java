package com.infofromquel.controller;

import com.infofromquel.entity.Comment;
import com.infofromquel.service.comentservice.CommentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Objects;

/**
 * API for entity {@link Comment}
 * operations : {create , update , delete}
 */
@Controller
public class CommentsController {

    private static final Logger LOG = Logger.getLogger(CommentsController.class);

    private final CommentService commentService;

    @Autowired
    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * add new Comment
     * @param comment {@link Comment}
     * @param principal {@link Principal}
     * @return HTTP status ok if all correct else bad request
     */
    @RequestMapping(value = "/user/addComment",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addComment(@RequestBody Comment comment, Principal principal){

        LOG.debug("CommentsController.addComment = {} " + comment);
        if(Objects.equals(principal.getName(),(comment.getUser().getEmail()))){
            commentService.addComment(comment);
            return ResponseEntity.ok(comment);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    /**
     * update Comment
     * @param comment {@link Comment}
     * @param principal {@link Principal}
     * @return {@link Comment}
     */
    @RequestMapping(value = "/user/updateComment",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity updateComment(@RequestBody Comment comment,Principal principal){
        LOG.debug("CommentsController.updateComment = {} " + comment);
        if(Objects.equals(principal.getName(),comment.getUser().getEmail())){
            commentService.updateComment(comment);
            return ResponseEntity.ok(comment);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    /**
     * delete Comment
     * @param comment {@link Comment}
     * @param principal {@link Principal}
     * @return {@link HttpStatus}
     */
    @RequestMapping(value = "/user/deleteComment",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity deleteComment(@RequestBody Comment comment,Principal principal){
        LOG.debug("CommentsController.deleteComment = {}" + comment);
        if(Objects.equals(principal.getName(),comment.getUser().getEmail())){
            commentService.deleteComment(comment);
            return ResponseEntity.ok(comment);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
