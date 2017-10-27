package com.infofromquel.dao;

import com.infofromquel.entity.User;
import com.infofromquel.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDaoImpl implements UserDao{

    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private String FIND_ALL_USER =  "select users.id,users.login,users.email,users.password,roles.id as role_id,roles.name as role_name " +
                                    "from users " +
                                         ",roles " +
                                         ",users_roles " +
                                    "where users.id = users_roles.user_id " +
                                    "and roles.id = users_roles.role_id ";
    private String FIND_USER_BY_EMAIL = "select users.id,users.login,users.email,users.password,roles.id as role_id,roles.name as role_name " +
                                        "from users " +
                                             ",roles "+
                                             ",users_roles "+
                                        "where users.id = users_roles.user_id "+
                                        "and roles.id = users_roles.role_id "+
                                        "and users.email = ? " ;

    private String FIND_USER_BY_ID = "select users.id,users.login,users.email,users.password,roles.id as role_id,roles.name as role_name " +
                                     "from users " +
                                     ",roles "+
                                     ",users_roles "+
                                     "where users.id = users_roles.user_id "+
                                     "and roles.id = users_roles.role_id "+
                                     "and users.id = ? " ;

    public List<User> findAll() {
        return jdbcTemplate.query(FIND_ALL_USER,new UserMapper());
    }

    public User findUserByName(String email){
        return  jdbcTemplate.queryForObject(FIND_USER_BY_EMAIL,new UserMapper(),email);
    }

    public User findUserById(int id){
        return  jdbcTemplate.queryForObject(FIND_USER_BY_ID,new UserMapper(),id);
    }
}
