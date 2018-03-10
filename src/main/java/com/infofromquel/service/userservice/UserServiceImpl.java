package com.infofromquel.service.userservice;

import com.infofromquel.dao.UserDao;
import com.infofromquel.entity.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserServiceImpl implements UserService{

    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    private UserDao userDao;

    @Autowired
    private User user;

    private final SessionFactory sessionFactory;

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findUserById(int id){
        return userDao.findUserById(id);
    }

    public User createUser(User user){
        user = userDao.createUser(user);
        return user;
    }

    public boolean findUserByEmail(String email){
        user = userDao.findUserByEmail(email);
        if(user.getName() != null){
            return true;
        }else{
            return false;
        }
    }

    public void checkHibernet(){
        LOG.debug("In start of Hibernate");
        Session session = sessionFactory.getCurrentSession();
        LOG.debug("After get session Hibernate");
        LOG.debug("After begin of transaction Hibernate");
        String sql = "select version()";
        String result = (String) session.createNativeQuery(sql).getSingleResult();
        LOG.debug("Check hibernate = {} " + result);

    }
}
