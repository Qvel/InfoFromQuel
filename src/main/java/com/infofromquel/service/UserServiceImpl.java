package com.infofromquel.service;

import com.infofromquel.dao.UserDao;
import com.infofromquel.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserServiceImpl implements UserService{


    @Autowired
    private UserDao userDao;

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findUserById(int id){
        return userDao.findUserById(id);
    }
}
