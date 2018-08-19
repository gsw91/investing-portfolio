package com.invest.services;

import com.invest.exceptions.NoSuchUserException;
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

    public User findUserByName(String username) throws NoSuchUserException {
        List<User> usersList = userDao.findAll();
        Optional<User> isExist = usersList.stream()
                .filter(t->t.getLogin().equals(username))
                .findFirst();
        if (isExist.isPresent()) {
            return isExist.get();
        } else {
            throw new NoSuchUserException();
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

    public boolean changeUserEmail(Long userId, String email) {
        if (userDao.findById(userId).isPresent()) {
            User user = userDao.findById(userId).get();
            user.setEmail(email);
            userDao.save(user);
            LOGGER.info("Password updated successfully");
            return true;
        } else {
            LOGGER.warn("Password updating failed");
            return false;
        }
    }

}
