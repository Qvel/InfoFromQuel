package com.infofromquel.dao.userdao;

import com.infofromquel.entity.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * DAO repository for {@link User}
 * @author Serhii Zhuravlov
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao{

    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class);
    private final SessionFactory sessionFactory;
    private User injectUser;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * @return all users
     */
    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder().createQuery(User.class);
        criteriaQuery.from(User.class);
        List<User> users = session.createQuery(criteriaQuery).getResultList();
        users.forEach(LOG::debug);
        return users;
    }

    /**
     * @param email email of user
     * @return user with such email
     */
    public User findUserByEmail(String email){
        LOG.debug("UserDaoImpl.findUserByEmail " + email);
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> user = criteriaQuery.from(User.class);
        criteriaQuery.select(user).where(criteriaBuilder.equal(user.get("email"),email));
        try {
            injectUser = session.createQuery(criteriaQuery).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
        LOG.debug("User = {} " + injectUser);
        return injectUser;
    }

    /**
     * @param id id of user
     * @return user with such id
     */
    public User findUserById(Long id){
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

    /**
     * @param user {@link User}
     * @return new user
     */
    public User createUser(User user){
        LOG.debug("UserDaoImpl.createUser " + user);
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
        LOG.debug("User = {}" + user);
        return user;
    }

    /**
     * @param user {@link User}
     * @return {@link User}
     */
    @Override
    public User updateUser(User user) {
        LOG.debug("UserDaoImpl.updateUser " + user);
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaUpdate<User> criteriaQuery = criteriaBuilder.createCriteriaUpdate(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        if(user.getName() != null){
            criteriaQuery.set(root.get("name"),user.getName());
        }
        if(user.getEmail() != null){
            criteriaQuery.set(root.get("email"),user.getEmail());
        }
        if(user.getPassword() != null){
            criteriaQuery.set(root.get("password"),user.getPassword());
        }
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"),user.getId()));
        session.createQuery(criteriaQuery).executeUpdate();
        LOG.debug("User = {}" + user);
        return user;
    }
}
