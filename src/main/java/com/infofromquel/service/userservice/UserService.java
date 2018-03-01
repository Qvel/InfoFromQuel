package com.infofromquel.service.userservice;

import com.infofromquel.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findUserById(int id);

    void createUser(User user);

    boolean findUserByEmail(String email);
}
