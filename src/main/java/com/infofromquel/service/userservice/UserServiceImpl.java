package com.infofromquel.service.userservice;

import com.infofromquel.dao.UserDao;
import com.infofromquel.entity.EmailTemplates;
import com.infofromquel.entity.Role;
import com.infofromquel.entity.User;
import com.infofromquel.service.mail.MailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * User Service implementation
 * @author Serhii Zhuravlov
 */
@Service
public class UserServiceImpl implements UserService{

    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserDao userDao, MailService mailService, PasswordEncoder passwordEncoder){
        this.userDao = userDao;
        this.mailService = mailService;
        this.passwordEncoder = passwordEncoder;
    }

    private UserDao userDao;
    private MailService mailService;
    private final PasswordEncoder passwordEncoder;

    /**
     * @return all user
     */
    public List<User> findAll() {
        LOG.debug("UserServiceImpl.findAll = {}");
        return userDao.findAll();
    }

    /**
     * @param id user id
     * @return user with such id
     */
    public User findUserById(Long id){
        LOG.debug("UserServiceImpl.findUserById = {} "+id);
        return userDao.findUserById(id);
    }

    /**
     * @param login login of user
     * @param email email of user
     * @param password password of user
     * @return new user
     */
    @Override
    public User createUser(String login,String email,String password){
        LOG.debug("UserServiceImpl.createUser = {} ");
        LOG.debug("Create user with params = {} " +
                " login " + login +
                ";email " + email +
                ";password " + password);
        User user = new User.Builder()
                .setEmail(email)
                .setName(login)
                .setPassword(passwordEncoder.encode(password))
                .setExist(false)
                .setRoles(Collections.singleton(new Role(2L,"USER")))
                .build();

        user = userDao.createUser(user);
        //mailService.sendHtmlEmail(user,EmailTemplates.REGISTRATION_TEMPLATE,EmailTemplates.REGISTRATION_SUBJECT);
        return user;
    }

    /**
     * @param email user email
     * @return status of user, if true user exist , else doesn't exist
     */
    public boolean findUserByEmail(String email){
        LOG.debug("UserServiceImpl.findUserByEmail = {} " + email);
        User user = userDao.findUserByEmail(email);
        return user != null;
    }

    /**
     *
     * @param email user email
     * @return user {@link User} with such email
     */
    @Override
    public User getUserByEmail(String email) {
        LOG.debug("UserServiceImpl.getUserByEmail = {} " + email);
        return userDao.findUserByEmail(email);
    }

    @Override
    public User updateAvatar(Long id, MultipartFile userLogo) throws IOException{
         User user  = userDao.findUserById(id);
         LOG.debug("size = " + userLogo.getSize());
         LOG.debug("name = " + userLogo.getOriginalFilename());
         String directory = "Q:" + File.separator + "work"
                + File.separator + "InfoFromQuel" + File.separator + "infoFromQuel" + File.separator
                + "src" + File.separator + "main" + File.separator
                + "webapp" + File.separator + "resources" + File.separator + "logos"
                + File.separator + user.hashCode() + ".jpg";
         File logo = new File(directory);
         LOG.debug(logo.getAbsolutePath());
         LOG.debug(logo.getPath());
         LOG.debug(logo.getParentFile().mkdirs());
         LOG.debug("name of new logo " + logo.getName());
         try {
            userLogo.transferTo(logo);
            user.setLogo(logo.getName());
         }catch (IOException ex){
            LOG.error("ERROR while upload logo " + Arrays.toString(ex.getStackTrace()));
            throw new IOException();
         }
         user = userDao.updateUser(user);
         return user;
    }

}
