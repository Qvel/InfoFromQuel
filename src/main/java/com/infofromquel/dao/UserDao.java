package com.infofromquel.dao;

import com.infofromquel.entity.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();
}
