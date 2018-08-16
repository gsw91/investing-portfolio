package com.invest.controller;

import com.invest.domain.User;
import com.invest.dtos.UserDto;
import com.invest.mappers.UserMapper;
import com.invest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/user/")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private UserMapper mapper;

    @RequestMapping(method = RequestMethod.POST, value = "create", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        User user = service.createUser(mapper.mapperToDomain(userDto));
        long userId = user.getId();
        service.checkIfExists(userId);
    }

    @RequestMapping(method = RequestMethod.GET, params = { "name", "password" },  value = "login")
    public boolean logUser (@RequestParam("name") String name, @RequestParam("password") String password) {
        UserDto userDto = mapper.mapperToDto(service.findUserByName(name));
        if (userDto.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

}
