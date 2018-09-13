package com.invest.controller;

import com.invest.config.AdministrationConfig;
import com.invest.domain.Mail;
import com.invest.dtos.UserDto;
import com.invest.exceptions.UserExistsException;
import com.invest.mailing.EmailPreparationService;
import com.invest.mappers.UserMapper;
import com.invest.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/user/")
public class UserController {

    private final static Logger LOGGER = Logger.getLogger(UserController.class);

    @Autowired
    private UserService service;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private EmailPreparationService emailService;

    @Autowired
    private AdministrationConfig administrationConfig;

    @RequestMapping(method = RequestMethod.POST, value = "create", consumes = APPLICATION_JSON_VALUE)
    public String createUser(@RequestBody UserDto userDto) {
        try {
            service.createUser(mapper.mapperToDomain(userDto));
        } catch (UserExistsException e) {
            LOGGER.warn(e.getMessage());
            return e.getMessage();
        }
        Mail mail = new Mail(administrationConfig.getAdminMail(), "New user", "");
        emailService.sendInfoToAdmin(mail);
        emailService.sendWelcomeMail(mapper.mapperToDomain(userDto));
        return "User created";
    }

    @RequestMapping(method = RequestMethod.GET, params = { "name", "password" },  value = "login")
    public UserDto logUser (@RequestParam("name") String name, @RequestParam("password") String password) {
        try {
            UserDto userDto = mapper.mapperToDto(service.findUserByName(name));
            if (userDto!=null && userDto.getPassword().equals(password)) {
                return userDto;
            }
        } catch (UserExistsException e) {
            LOGGER.warn(e.getMessage());
        }
        return new UserDto();
    }

}
