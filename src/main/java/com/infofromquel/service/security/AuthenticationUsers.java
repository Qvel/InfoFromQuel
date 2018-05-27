package com.infofromquel.service.security;

import com.infofromquel.dao.UserDao;
import com.infofromquel.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Class for overriding {@link UserDetailsService}
 * @author Serhii Zhuravlov
 */
@Component
public class AuthenticationUsers implements UserDetailsService {

     private static final Logger LOG = Logger.getLogger(AuthenticationUsers.class);
     private final UserDao userDao;

    @Autowired
    public AuthenticationUsers(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     *
     * @param userName name of user
     * @return {@link UserDetails}
     * @throws UsernameNotFoundException if user couldn't be found
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        LOG.debug("AuthenticationUsers.loadUserByUsername = {}" + userName);

        User user = userDao.findUserByEmail(userName);

        return new CustomUserDetails(user);
    }

}
