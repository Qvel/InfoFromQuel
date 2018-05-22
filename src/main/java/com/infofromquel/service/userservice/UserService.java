package com.infofromquel.service.userservice;

import com.infofromquel.entity.User;

import java.util.List;

/**
 * Api of user service
 * @author Serhii Zhuravlov
 */
public interface UserService {

    List<User> findAll();

    User findUserById(int id);

    User createUser(User user);

    boolean findUserByEmail(String email);

}
