package com.invest.services;

import com.invest.repositories.UserDao;
import com.invest.domain.User;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final static Logger LOGGER = Logger.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    public User addUser(User user) {
        userDao.save(user);
        long userId = user.getId();
        if (userDao.existsById(userId)) {
            LOGGER.info("User created successfully with id = " + userId);
        } else {
            LOGGER.warn("User creation failed");
        }
        return user;
    }

    public void deleteUser(Long userId) {
        if (userDao.existsById(userId)) {
            userDao.deleteById(userId);
            LOGGER.info("User deleted successfully");
        } else {
            LOGGER.warn("User deletion failed");
        }
    }

    public boolean updateUserLogin(Long userId, String login) {
        if (userDao.findById(userId).isPresent()) {
            User user = userDao.findById(userId).get();
            user.setLogin(login);
            userDao.save(user);
            LOGGER.info("Login updated successfully");
            return true;
        } else {
            LOGGER.warn("Login updating failed");
            return false;
        }
    }

    public boolean changeUserPassword(Long userId, String password) {
        if (userDao.findById(userId).isPresent()) {
            User user = userDao.findById(userId).get();
            user.setPassword(password);
            userDao.save(user);
            LOGGER.info("Password updated successfully");
            return true;
        } else {
            LOGGER.warn("Password updating failed");
            return false;
        }
    }

    public User findUser(Long id) {
        if (userDao.findById(id).isPresent()) {
            return userDao.findById(id).get();
        } else {
            LOGGER.warn("No such user with this id");
            return new User();
        }
    }

    //method to add bought instrument
    // add market price
    // add statistics

    //method to remove/sell instrument
    // remove market price
    // complement(fill in) statistics

}
