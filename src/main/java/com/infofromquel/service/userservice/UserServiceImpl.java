package com.infofromquel.service.userservice;

import com.infofromquel.dao.UserDao;
import com.infofromquel.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User Service implementation
 * @author Serhii Zhuravlov
 */
@Service
public class UserServiceImpl implements UserService{

    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }

    private UserDao userDao;

    /**
     * @return all user
     */
    public List<User> findAll() {
        LOG.debug("UserServiceImpl.findAll = {}");
        return userDao.findAll();
    }

    /**
     * @param id user id
     * @return user with such id
     */
    public User findUserById(int id){
        LOG.debug("UserServiceImpl.findUserById = {} "+id);
        return userDao.findUserById(id);
    }

    /**
     * @param user {@link User}
     * @return new user
     */
    public User createUser(User user){
        LOG.debug("UserServiceImpl.createUser = {} " + user);
        return userDao.createUser(user);
    }

    /**
     * @param email user email
     * @return user with such email
     */
    public boolean findUserByEmail(String email){
        LOG.debug("UserServiceImpl.findUserByEmail = {} " + email);
        User user = userDao.findUserByEmail(email);
        return user.getName() != null;
    }

}
