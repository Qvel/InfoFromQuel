package com.infofromquel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Entity for User in the system
 * @author Serhii Zhuravlov
 */
@Component
@Entity
@Table(name = "users")
@DynamicUpdate
public class User implements Serializable {

    public User() {
    }

    public User(Builder builder) {
        this.name = builder.name;
        this.password = builder.password;
        this.email = builder.email;
        this.roles = builder.roles;
        this.isExist = builder.isExist;
        this.logo = builder.logo;
    }

    public User(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.roles = user.getRoles();
        this.isExist = user.isExist();
        this.logo = user.logo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login",nullable = false)
    private String name;
    @JsonIgnore
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "email",nullable = false)
    private String email;
    @ManyToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    @JoinTable(
            name="users_roles",
            joinColumns = {
                    @JoinColumn(
                            name= "user_id",
                            nullable = false
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "role_id",
                            nullable = false
                    )
            }

    )
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();
    @Column(name = "is_exist",nullable = false)
    @JsonIgnore
    private boolean isExist;
    @Column(name = "logo")
    private String logo;
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.ALL},mappedBy = "user",fetch = FetchType.EAGER)
    private Set<Topic> topics = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean exist) {
        isExist = exist;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Set<Topic> getTopics() {
        return topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                ", isExist=" + isExist +
                ", userLogo = " + logo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return isExist() == user.isExist() &&
                Objects.equals(getId(), user.getId()) &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getRoles(), user.getRoles()) &&
                Objects.equals(getLogo(), user.getLogo());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getPassword(), getEmail(), getRoles(), isExist(), getLogo());
    }

    public static class Builder{

        public Builder() {
        }

        private String name;
        private String password;
        private String email;
        private Set<Role> roles = new HashSet<>();
        private boolean isExist;
        private String logo;
        private Set<Topic> topics = new HashSet<>();


        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setRoles(Set<Role> roles) {
            this.roles = roles;
            return this;
        }

        public Builder setExist(boolean exist) {
            isExist = exist;
            return this;
        }

        public Builder setLogo(String logo) {
            this.logo = logo;
            return this;
        }

        public Builder setTopics(Set<Topic> topics){
            this.topics = topics;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }
}
