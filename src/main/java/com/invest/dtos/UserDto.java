package com.invest.dtos;

public class UserDto {

    private Long id;
    private String login;
    private String password;
    private String email;

    public UserDto() {
    }

    public UserDto(Long id, String login, String password, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

}
