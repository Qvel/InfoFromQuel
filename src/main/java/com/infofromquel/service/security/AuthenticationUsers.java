package com.infofromquel.service.security;

import com.infofromquel.dao.UserDao;
import com.infofromquel.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationUsers implements UserDetailsService {

    @Autowired
    UserDao userDao;

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = userDao.findUserByName(userName);

        return new CustomUserDetails(user);
    }

}
