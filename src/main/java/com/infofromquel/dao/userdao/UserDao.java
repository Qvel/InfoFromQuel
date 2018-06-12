package com.infofromquel.dao.userdao;

import com.infofromquel.entity.User;
import java.util.List;

/**
 * Interface for dao functional
 * @author Serhii Zhuravlov
 */
public interface UserDao {

    List<User> findAll();

    User findUserByEmail(String email);

    User findUserById(Long id);

    User createUser(User user);

    User updateUser(User user);
}
