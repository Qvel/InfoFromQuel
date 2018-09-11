package com.infofromquel.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 * Entity for Topic of Forum
 */
@Entity
@Component
@Table(name="topic")
public class Topic implements Serializable {

    public Topic() {
    }

    public Topic(String title, String body, User user, Date date, Set<Comment> comments) {
        this.title = title;
        this.body = body;
        this.user = user;
        this.date = date;
        this.comments = comments;
    }

    /**
     * topic id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * topic title
     */
    @Column(name="title",nullable = false)
    private String title;
    /**
     * topic body
     */
    @Column(name="body", nullable = false)
    private String body;
    /**
     * {@link User} that posed this topic
     */
    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id",nullable = false)
    private User user;
    /**
     * {@link Date} when {@link Topic} topic was posed
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date date;
    /**
     * {@link Topic} topics comments {@link Comment}
     */
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "topic",fetch = FetchType.EAGER)
    private Set<Comment> comments = new HashSet<>();

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

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
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
                Objects.equals(getDate(), topic.getDate()) &&
                Objects.equals(getComments(), topic.getComments());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getTitle(), getBody(), getUser(), getDate(), getComments());
    }

    @Override
    public String toString() {
        return "Topic{" + "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", user=" + user +
                ", date=" + date +
                '}';
    }
}
