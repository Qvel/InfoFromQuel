package com.infofromquel.dao;

import com.infofromquel.entity.Role;
import com.infofromquel.entity.User;
import com.infofromquel.mapper.UserMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDaoImpl implements UserDao{

    private final JdbcTemplate jdbcTemplate;
    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class);

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Autowired
    User injectUser;

    @Autowired
    Role role;

    private String FIND_ALL_USER =  "select users.id,users.login,users.email,users.password,roles.id as role_id,roles.name as role_name " +
                                    "from users " +
                                         ",roles " +
                                         ",users_roles " +
                                    "where users.id = users_roles.user_id " +
                                    "and roles.id = users_roles.role_id ";
    private String FIND_USER_BY_EMAIL = "select users.id,users.login,users.email,users.password,roles.id as role_id,roles.name as role_name " +
                                        "from users " +
                                             "left join users_roles " +
                                                    "on users.id  = users_roles.user_id " +
                                                    "left join roles " +
                                                         "on roles.id = users_roles.role_id " +
                                        "where users.email = ? " ;

    private String FIND_USER_BY_ID = "select users.id,users.login,users.email,users.password,roles.id as role_id,roles.name as role_name " +
                                     "from users " +
                                     ",roles "+
                                     ",users_roles "+
                                     "where users.id = users_roles.user_id "+
                                     "and roles.id = users_roles.role_id "+
                                     "and users.id = ? " ;

    private String INSERT_USER_TABLE = "insert into users " +
                                      "(login,password,email) " +
                                      "values(?,?,?) ";
    private String INSERT_INTO_USER_ROLE_TABLE =  "insert into users_roles " +
                                                  "(user_id,role_id) " +
                                                  "values (?,2)" ;

    public List<User> findAll() {
        return jdbcTemplate.query(FIND_ALL_USER,new UserMapper());
    }

    public User findUserByEmail(String email){
        LOG.debug("Hi in findUserByEmail with email + " + email);
        jdbcTemplate.query(FIND_USER_BY_EMAIL, (resultSet, rowNum) -> {
            injectUser = new User();
            role = new Role();
            injectUser.setId(resultSet.getInt("id"));
            injectUser.setName(resultSet.getString("login"));
            injectUser.setPassword(resultSet.getString("password"));
            injectUser.setEmail(resultSet.getString("email"));
            List<Role> user_roles = new ArrayList<>();
            role.setId(resultSet.getInt("role_id"));
            role.setName(resultSet.getString("role_name"));
            user_roles.add(role);
            injectUser.setRoles(user_roles);
            return injectUser;
        },email);
        LOG.debug("injectUser = " + injectUser);
        if(injectUser == null){
            return null;
        }
        return  injectUser;
    }

    public User findUserById(int id){
        return  jdbcTemplate.queryForObject(FIND_USER_BY_ID,new UserMapper(),id);
    }

    public User createUser(User user){
        jdbcTemplate.update(INSERT_USER_TABLE,user.getName(),user.getPassword(),user.getEmail());
        injectUser = findUserByEmail(user.getEmail());
        LOG.debug("Id of user = "  + injectUser.getId());
        jdbcTemplate.update(INSERT_INTO_USER_ROLE_TABLE,injectUser.getId());
        return injectUser;
    }
}
