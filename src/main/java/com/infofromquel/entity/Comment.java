package com.infofromquel.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entity for Comment
 * @author Serhii Zhuravlov
 */
@Component
@Entity
@Table(name = "comments")
public class Comment implements Serializable {

    public Comment() {
    }

    public Comment(Comment parent, Set<Comment> childComments, User user, Topic topic, String body, Date date) {
        this.parent = parent;
        this.childComments = childComments;
        this.user = user;
        this.topic = topic;
        this.body = body;
        this.date = date;
    }

    /**
     * Comment id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Parent {@link Comment} id
     */
    @Transient
    private Long parentId;
    /**
     * Parent {@link Comment}
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="parent_id")
    @JsonBackReference
    private Comment parent;
    /**
     * Child comments
     */
    @OneToMany(mappedBy = "parent",fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval=true)
    private Set<Comment> childComments = new HashSet<>();
    /**
     * {@link User} of comment
     */
    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id",nullable = false)
    @JsonManagedReference
    private User user;
    /**
     * {@link Topic} that has this comment
     */
    @ManyToOne
    @JoinColumn(name="topic_id",referencedColumnName = "id")
    @JsonIgnore
    private Topic topic;
    /**
     * body of comment
     */
    @Column(name = "body")
    private String body;
    /**
     * {@link Date} when comment was posted
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="dd-MM-yyyy")
    @Column(name="date")
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public Set<Comment> getChildComments() {
        return childComments;
    }

    public void setChildComments(Set<Comment> childComments) {
        this.childComments = childComments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return Objects.equals(getId(), comment.getId()) &&
                Objects.equals(getParent(), comment.getParent()) &&
                Objects.equals(getChildComments(), comment.getChildComments()) &&
                Objects.equals(getUser(), comment.getUser()) &&
                Objects.equals(getTopic(), comment.getTopic()) &&
                Objects.equals(getBody(), comment.getBody()) &&
                Objects.equals(getDate(), comment.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getParent(), getUser(), getBody(), getDate());
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id +
                ", parent=" + parent +
                ", user=" + user +
                ", topic=" + topic +
                ", body='" + body + '\'' +
                ", date=" + date +
                '}';
    }
}
