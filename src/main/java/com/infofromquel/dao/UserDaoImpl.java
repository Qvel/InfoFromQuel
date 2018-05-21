package com.infofromquel.dao;

import com.infofromquel.entity.Role;
import com.infofromquel.entity.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;



@Service
@Transactional
public class UserDaoImpl implements UserDao{

    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class);
    private final SessionFactory sessionFactory;
    private User injectUser;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder().createQuery(User.class);
        criteriaQuery.from(User.class);
        List<User> users = session.createQuery(criteriaQuery).getResultList();
        users.forEach(LOG::debug);
        return users;
    }

    public User findUserByEmail(String email){
        LOG.debug("UserDaoImpl.findUserByEmail " + email);
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> user = criteriaQuery.from(User.class);
        criteriaQuery.select(user).where(criteriaBuilder.equal(user.get("email"),email));
        injectUser = session.createQuery(criteriaQuery).getSingleResult();
        LOG.debug("User = {} " + injectUser);
        return injectUser;
    }

    public User findUserById(int id){
        LOG.debug("UserDaoImpl.findUserById " + id);
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> user = criteriaQuery.from(User.class);
        criteriaQuery.select(user).where(criteriaBuilder.equal(user.get("id"),id));
        injectUser = session.createQuery(criteriaQuery).getSingleResult();
        LOG.debug("User = {} "+ injectUser);
        return injectUser;
    }

    public User createUser(User user){
        LOG.debug("UserDaoImpl.createUser " + user);
        Session session = sessionFactory.getCurrentSession();
        Role role = new Role(2, "USER");
        user.setRoles(Collections.singleton(role));
        session.saveOrUpdate(user);
        LOG.debug("User = {}" + user);
        return injectUser;
    }
}
