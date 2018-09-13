package com.invest.services;

import com.invest.exceptions.UserExistsException;
import com.invest.repositories.UserDao;
import com.invest.domain.User;
import org.junit.Assert;
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
    public void testGetLastUser() {
        //given
        List<User> userList = new ArrayList<>();
        userList.add(new User(41L, "test1", "test1", "test1@test.com"));
        userList.add(new User(43L, "test2", "test2", "test2@test.com"));
        when(userDao.count()).thenReturn(2L);
        when(userDao.findAll()).thenReturn(userList);
        //when
        User user = userService.getLastUser();
        //then
        assertEquals(43L, user.getId().longValue());
        assertEquals("test2", user.getLogin());
        assertEquals("test2", user.getPassword());
        assertEquals("test2@test.com", user.getEmail());
    }

    @Test
    public void testCreateUser() throws UserExistsException {
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
    }

    @Test(expected = UserExistsException.class)
    public void testCreateUserAndThrowExceptionOfName() throws UserExistsException {
        //given
        User user = new User("Mockito_user", "mockito", "mock@mockito.com");
        List<User> users = new ArrayList<>();
        users.add(user);

        User userToSave = new User("Mockito_user", "mockito", "hello999@mockito.com");
        when(userDao.findAll()).thenReturn(users);
        //when&&then
        userService.createUser(userToSave);
    }

    @Test(expected = UserExistsException.class)
    public void testCreateUserAndThrowExceptionOfEmail() throws UserExistsException {
        //given
        User user = new User("Mockito_user", "mockito", "mock@mockito.com");
        List<User> users = new ArrayList<>();
        users.add(user);

        User userToSave = new User("Hello999", "mockito", "mock@mockito.com");
        when(userDao.findAll()).thenReturn(users);
        //when&&then
        userService.createUser(userToSave);
    }

    @Test
    public void testGetAllUsers() {
        //given
        List<User> userList = new ArrayList<>();
        userList.add(new User(41L, "test1", "test1", "test1@test.com"));
        userList.add(new User(43L, "test2", "test2", "test2@test.com"));
        userList.add(new User(44L, "test3", "test3", "test3@test.com"));
        userList.add(new User(49L, "test4", "test4", "test4@test.com"));
        when(userDao.findAll()).thenReturn(userList);
        //when
        List<User> list = userService.getAllUsers();
        //then
        assertEquals(4, list.size());
        assertEquals("test1", list.get(0).getLogin());
        assertEquals("test2", list.get(1).getLogin());
        assertEquals("test3", list.get(2).getLogin());
        assertEquals("test4", list.get(3).getLogin());
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

    @Test(expected = UserExistsException.class)
    public void testFindUserByIdAndThrowException() throws UserExistsException {
        //given
        Optional<User> userOptional = Optional.empty();
        Long userId = 6L;
        when(userDao.findById(userId)).thenReturn(userOptional);
        //when&&then
        userService.findUserById(userId);

    }

    @Test
    public void testFindUserByName() throws UserExistsException {
        //given
        User user = new User("Mockito_user", "mockito", "mock@mockito.com");
        List<User> users = new ArrayList<>();
        users.add(user);
        when(userDao.findAll()).thenReturn(users);
        //when & then
        User readUser = userService.findUserByName("Mockito_user");
        assertEquals("Mockito_user", readUser.getLogin());
        assertEquals("mockito", readUser.getPassword());
        assertEquals("mock@mockito.com", readUser.getEmail());
    }

    @Test(expected = UserExistsException.class)
    public void testFindUserByNameAndThrowException() throws UserExistsException {
        //given
        String userName = "danny";
        when(userDao.findAll()).thenReturn(new ArrayList<>());
        //when&&then
        userService.findUserByName(userName);
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

    @Test
    public void testCountUsers() {
        //given
        when(userDao.count()).thenReturn(17L);
        //when
        long quantityOfUsers = userService.countUsers();
        //then
        Assert.assertEquals(17L, quantityOfUsers);
    }

}
