package com.invest.services;

import com.invest.repositories.UserDao;
import com.invest.domain.User;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final static Logger LOGGER = Logger.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    public User createUser(User user) {
        return userDao.save(user);
    }

    public User findUserByName(String username) {
        List<User> usersList = userDao.findAll();
        Optional<User> isExist = usersList.stream()
                .filter(t->t.getLogin().equals(username))
                .findFirst();
        if (isExist.isPresent()) {
            return isExist.get();
        } else {
            throw new IllegalArgumentException();
        }
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

    public User getUser(Long id) {
        if (userDao.findById(id).isPresent()) {
            return userDao.findById(id).get();
        } else {
            LOGGER.warn("No such user with this id");
            return new User();
        }
    }

    public boolean checkIfExists(Long id) {
        if (userDao.findById(id).isPresent()) {
            LOGGER.info("User with id " + id + " exists");
            return true;
        } else {
            LOGGER.warn("No such user with this id");
            return false;
        }
    }

    //method to add bought instrument
    // add market price
    // add statistics

    //method to remove/sell instrument
    // remove market price
    // complement(fill in) statistics

}
