package com.invest.services;

import com.invest.exceptions.UserExistsException;
import com.invest.repositories.UserDao;
import com.invest.domain.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {

    private final static Logger LOGGER = Logger.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    public User getLastUser() {
        long count = countUsers();
        return userDao.findAll().stream()
                .skip(count-1).findFirst().get();
    }

    public User createUser(User user) throws UserExistsException {
        boolean ifUsernameExists = getAllUsers().stream()
                .anyMatch(t->t.getLogin().equals(user.getLogin()));
        if (ifUsernameExists) {
            throw new UserExistsException(UserExistsException.USERNAME_EXISTS);
        } else {
            boolean ifEmailExists = getAllUsers().stream()
                    .anyMatch(t-> t.getEmail().equals(user.getEmail()));
            if (ifEmailExists) {
                throw new UserExistsException(UserExistsException.EMAIL_FORBIDDEN);
            } else {
                return userDao.save(user);
            }
        }
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public User findUserById(Long userId) throws UserExistsException {
        boolean isExisting = userDao.findById(userId).isPresent();
        if (isExisting) {
            return userDao.findById(userId).get();
        } else {
            throw new UserExistsException(UserExistsException.NO_SUCH_USER);
        }
    }

    public User findUserByName(String username) throws UserExistsException {
        List<User> usersList = userDao.findAll();
        Optional<User> isExist = usersList.stream()
                .filter(t->t.getLogin().equals(username))
                .findFirst();
        if (isExist.isPresent()) {
            return isExist.get();
        } else {
            throw new UserExistsException(UserExistsException.NO_SUCH_USER);
        }
    }

    public User findUserByEmail (String mail) {
        List<User> usersList = userDao.findAll();
        Optional<User> optionalUser = usersList.stream()
                .filter(t->t.getEmail().equals(mail))
                .findFirst();

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return new User();
        }
    }

    public boolean deleteUser(Long userId) {
        if (userDao.existsById(userId)) {
            userDao.deleteById(userId);
            LOGGER.info("User deleted successfully");
            return true;
        } else {
            LOGGER.warn("User deletion failed");
            return false;
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

    public long countUsers() {
        return userDao.count();
    }

}
