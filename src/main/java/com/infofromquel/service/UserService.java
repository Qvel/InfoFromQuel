package com.infofromquel.service;

import com.infofromquel.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findUserById(int id);

    void createUser(User user);
}
