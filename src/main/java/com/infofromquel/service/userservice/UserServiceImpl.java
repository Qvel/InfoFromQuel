package com.infofromquel.service.userservice;

import com.infofromquel.dao.UserDao;
import com.infofromquel.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserServiceImpl implements UserService{


    @Autowired
    private UserDao userDao;

    @Autowired
    private User user;

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findUserById(int id){
        return userDao.findUserById(id);
    }

    public void createUser(User user){
        userDao.createUser(user);
    }

    public boolean findUserByEmail(String email){
        user = userDao.findUserByEmail(email);
        if(user.getName() != null){
            return true;
        }else{
            return false;
        }
    }
}
