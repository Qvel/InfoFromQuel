package com.infofromquel.dao;

import com.infofromquel.entity.User;
import java.util.List;

/**
 * Interface for dao functional
 * @author Serhii Zhuravlov
 */
public interface UserDao {

    List<User> findAll();

    User findUserByEmail(String email);

    User findUserById(int id);

    User createUser(User user);


}
