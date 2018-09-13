package com.invest.controller;

import com.invest.config.AdministrationConfig;
import com.invest.domain.User;
import com.invest.dtos.UserDto;
import com.invest.exceptions.UserExistsException;
import com.invest.mailing.EmailPreparationService;
import com.invest.mappers.UserMapper;
import com.invest.services.UserService;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @MockBean
    private UserMapper mapper;

    @MockBean
    private EmailPreparationService emailPreparationService;

    @MockBean
    private AdministrationConfig administrationConfig;

    @Test
    public void shouldFindUser() throws Exception {
        //given
        User user = new User(1023L, "grzegorz", "123123", "d@d.com");
        UserDto userDto = new UserDto(1023L, "grzegorz", "123123", "d@d.com");

        when(service.findUserByName("grzegorz")).thenReturn(user);
        when(mapper.mapperToDto(user)).thenReturn(userDto);
        //when & then
        mockMvc.perform(get("/v1/user/login?name=grzegorz&password=123123").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1023)))
                .andExpect(jsonPath("$.login", is("grzegorz")))
                .andExpect(jsonPath("$.password", is("123123")))
                .andExpect(jsonPath("$.email", is("d@d.com")));
    }

    @Test
    public void shouldCreateUser() throws Exception {
        //given
        User user = new User(1023L, "grzegorz", "123123", "d@d.com");
        UserDto userDto = new UserDto(1023L, "grzegorz", "123123", "d@d.com");

        when(service.createUser(user)).thenReturn(user);
        when(mapper.mapperToDto(user)).thenReturn(userDto);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("login", "grzegorz");
        jsonObject.put("password", "123123");
        jsonObject.put("email", "d@d.com");
        String jsonContent = jsonObject.toString();
        //when & then
        mockMvc.perform(post("/v1/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

}
