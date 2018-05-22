package com.infofromquel.service.userservice;

import com.infofromquel.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Api of user service
 * @author Serhii Zhuravlov
 */
public interface UserService {

    List<User> findAll();

    User findUserById(int id);

    User createUser(String login,String email,String password,MultipartFile userLogo)throws IOException;

    boolean findUserByEmail(String email);

}
