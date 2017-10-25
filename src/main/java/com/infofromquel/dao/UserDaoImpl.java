package com.infofromquel.dao;

import com.infofromquel.entity.User;
import com.infofromquel.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao{

    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private String FIND_ALL_USER = "select id , name , age " +
                                   "from students " ;


    public List<User> findAll() {
        return jdbcTemplate.query(FIND_ALL_USER,new UserMapper());
    }
}
