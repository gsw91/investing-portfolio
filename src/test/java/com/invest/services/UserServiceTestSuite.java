package com.invest.services;

import com.invest.repositories.UserDao;
import com.invest.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTestSuite {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserDao userDao;

    @Test
    public void shouldAddNewUser() {
        //given
        User user = new User("Mockito_user", "mockito", "mock@mockito.com");
        user.setId(1032L);
        when(userDao.save(user)).thenReturn(user);
        //when
        User savedUser = userService.createUser(user);
        //then
        assertEquals(1032L, savedUser.getId().longValue());
        assertEquals("Mockito_user", savedUser.getLogin());
        assertEquals("mockito", savedUser.getPassword());
        assertEquals("mock@mockito.com", savedUser.getEmail());
        assertEquals(0, savedUser.getInstruments().size());
        assertEquals(0, savedUser.getMarketPrices().size());
    }

    @Test
    public void shouldRemoveUser() {
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
    public void shouldChangeUserLogin() {
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
        assertEquals("helenka", user.getLogin());
    }

    @Test
    public void shouldChangeUserPassword() {
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
        assertEquals("otikcom", user.getPassword());
    }

    @Test
    public void shouldFindUser() {
        //given
        User user = new User("Mockito_user", "mockito", "mock@mockito.com");
        long userId = 991L;
        when(userDao.findById(userId)).thenReturn(Optional.of(user));
        //when
        User readUser = userService.getUser(userId);
        //given
        assertEquals("Mockito_user", readUser.getLogin());
        assertEquals("mockito", readUser.getPassword());
        assertEquals("mock@mockito.com", readUser.getEmail());
    }

}
