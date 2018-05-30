package com.infofromquel.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


/**
 * Entity for Topic of Forum
 * where title is title of topic
 * body is text in the topic
 * and user - user that create this topic
 */
@Entity
@Component
@Table(name="topic")
public class Topic implements Serializable {

    public Topic() {
    }

    public Topic(String title, String body, User user) {
        this.title = title;
        this.body = body;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name="body", nullable = false)
    private String body;
    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id",nullable = false)
    private User user;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date date;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Topic)) return false;
        Topic topic = (Topic) o;
        return Objects.equals(getId(), topic.getId()) &&
                Objects.equals(getTitle(), topic.getTitle()) &&
                Objects.equals(getBody(), topic.getBody()) &&
                Objects.equals(getUser(), topic.getUser()) &&
                Objects.equals(getDate(), topic.getDate());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getTitle(), getBody(), getUser(), getDate());
    }

    @Override
    public String toString() {
        return "Topic{" + "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", user=" + user +
                ", date = " + date +
                '}';
    }
}
