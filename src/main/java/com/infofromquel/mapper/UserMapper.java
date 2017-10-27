package com.infofromquel.mapper;

import com.infofromquel.entity.Role;
import com.infofromquel.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserMapper implements RowMapper<User> {

    public User mapRow(ResultSet resultSet, int i) throws SQLException {

        User user = new User();
        Role role = new Role();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        List<Role> user_roles = new ArrayList<>();
        role.setId(resultSet.getInt("role_id"));
        role.setName(resultSet.getString("role_name"));
        user_roles.add(role);
        user.setRoles(user_roles);

        return user;
    }
}
