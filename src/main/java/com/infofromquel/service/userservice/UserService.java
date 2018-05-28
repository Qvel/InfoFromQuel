package com.infofromquel.service.userservice;

import com.infofromquel.entity.User;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

/**
 * Api of user service
 * @author Serhii Zhuravlov
 */
public interface UserService {

    List<User> findAll();

    User findUserById(Long id);

    User createUser(String login,String email,String password);

    boolean findUserByEmail(String email);

    User updateAvatar(Long id,MultipartFile userLogo)throws IOException;

    User getUserByEmail(String email);

    Resource loadAsResource(String fileName)throws MalformedURLException;

    User updateUser(User user);
}
