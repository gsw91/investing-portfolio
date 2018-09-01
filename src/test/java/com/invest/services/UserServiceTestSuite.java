package com.invest.services;

import com.invest.exceptions.UserExistsException;
import com.invest.repositories.UserDao;
import com.invest.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTestSuite {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserDao userDao;

    @Test
    public void testCreateUser() {
        //given
        User user = new User("Mockito_user", "mockito", "mock@mockito.com");
        user.setId(1032L);
        when(userDao.save(user)).thenReturn(user);
        //when
        try {
            User savedUser = userService.createUser(user);
            //then
            assertEquals(1032L, savedUser.getId().longValue());
            assertEquals("Mockito_user", savedUser.getLogin());
            assertEquals("mockito", savedUser.getPassword());
            assertEquals("mock@mockito.com", savedUser.getEmail());
            assertEquals(0, savedUser.getInstruments().size());
        } catch (UserExistsException e) {
        }
    }

    @Test
    public void testDeleteUser() {
        //given
        long userId = 1032L;
        doNothing().when(userDao).deleteById(userId);
        when(userDao.existsById(userId)).thenReturn(true);
        //when
        userService.deleteUser(userId);
        //then
        verify(userDao, times(1)).deleteById(userId);
    }

    @Test
    public void testUpdateUserLogin() {
        //given
        User user = new User("Mockito_user", "mockito", "mock@mockito.com");
        long userId = 991L;
        String userName = "helenka";
        when(userDao.findById(userId)).thenReturn(Optional.of(user));
        when(userDao.save(user)).thenReturn(user);
        //when
        boolean isUpdated = userService.updateUserLogin(userId, userName);
        //then
        assertTrue(isUpdated);
    }

    @Test
    public void testChangeUserPassword() {
        //given
        User user = new User("Mockito_user", "mockito", "mock@mockito.com");
        long userId = 991L;
        String password = "otikcom";
        when(userDao.findById(userId)).thenReturn(Optional.of(user));
        when(userDao.save(user)).thenReturn(user);
        //when
        boolean isUpdated = userService.changeUserPassword(userId, password);
        //then
        assertTrue(isUpdated);
    }

    @Test
    public void testFindUserByName() {
        //given
        User user = new User("Mockito_user", "mockito", "mock@mockito.com");
        List<User> users = new ArrayList<>();
        users.add(user);
        when(userDao.findAll()).thenReturn(users);
        //when & then
        try {
            User readUser = userService.findUserByName("Mockito_user");
            assertEquals("Mockito_user", readUser.getLogin());
            assertEquals("mockito", readUser.getPassword());
            assertEquals("mock@mockito.com", readUser.getEmail());
        } catch (UserExistsException e) {
        }
    }

    @Test
    public void testFindUserById() throws UserExistsException {
        //given
        User user = new User("Mockito_user", "mockito", "mock@mockito.com");
        Long userId = 6L;
        when(userDao.findById(userId)).thenReturn(Optional.of(user));
        //when
        User receivedUser = userService.findUserById(userId);
        //then
        assertEquals(user, receivedUser);

    }

    @Test
    public void testChangeUserEmail() {
        //given
        User user = new User("Mockito_user", "mockito", "mock@mockito.com");
        long userId = 991L;
        String mail = "changed@mockito.com";
        when(userDao.findById(userId)).thenReturn(Optional.of(user));
        when(userDao.save(user)).thenReturn(user);
        //when
        boolean isUpdated = userService.changeUserEmail(userId, mail);
        //then
        assertTrue(isUpdated);
    }


}
