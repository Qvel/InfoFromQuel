package com.infofromquel.dao;

import com.infofromquel.entity.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    User findUserByEmail(String email);

    User findUserById(int id);

    void createUser(User user);


}
