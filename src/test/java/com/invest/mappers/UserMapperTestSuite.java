package com.invest.mappers;

import com.invest.domain.User;
import com.invest.dtos.UserDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTestSuite {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void shouldReturnUser() {
        //given
        UserDto userDto = new UserDto(22L, "user", "user", "user@user.com");
        //when
        User user = userMapper.mapperToDomain(userDto);
        //then
        Assert.assertNull(user.getId());
        Assert.assertEquals("user", user.getLogin());
        Assert.assertEquals("user", user.getPassword());
        Assert.assertEquals("user@user.com", user.getEmail());
    }

    @Test
    public void shouldReturnUserDto() {
        //given
        User user = new User(22L, "user", "user", "user@user.com");
        //when
        UserDto userDto = userMapper.mapperToDto(user);
        //then
        Assert.assertEquals(22L, userDto.getId().longValue());
        Assert.assertEquals("user", userDto.getLogin());
        Assert.assertEquals("user", userDto.getPassword());
        Assert.assertEquals("user@user.com", userDto.getEmail());    }

    @Test
    public void shouldReturnUserList() {
        //given
        UserDto userDto1 = new UserDto(22L, "user1", "user", "user1@user.com");
        UserDto userDto2 = new UserDto(24L, "user2", "user", "user2@user.com");
        UserDto userDto3 = new UserDto(252L, "user3", "user", "user3@user.com");
        List<UserDto> userDtoList = new ArrayList<>();
        userDtoList.add(userDto1);
        userDtoList.add(userDto2);
        userDtoList.add(userDto3);
        //when
        List<User> users = userMapper.mapperToListDomain(userDtoList);
        //then
        Assert.assertNull(users.get(0).getId());
        Assert.assertEquals("user1", users.get(0).getLogin());
        Assert.assertEquals("user", users.get(2).getPassword());
        Assert.assertEquals("user2@user.com", users.get(1).getEmail());
    }

    @Test
    public void shouldReturnUserDtoList() {
        //given
        User user1 = new User(22L, "user1", "user", "user1@user.com");
        User user2 = new User(24L, "user2", "user", "user2@user.com");
        User user3 = new User(252L, "user3", "user", "user3@user.com");
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        //when
        List<UserDto> usersDto = userMapper.mapperToListDto(userList);
        //then
        Assert.assertEquals(22L, usersDto.get(0).getId().longValue());
        Assert.assertEquals("user1", usersDto.get(0).getLogin());
        Assert.assertEquals("user", usersDto.get(2).getPassword());
        Assert.assertEquals("user2@user.com", usersDto.get(1).getEmail());
    }

}
