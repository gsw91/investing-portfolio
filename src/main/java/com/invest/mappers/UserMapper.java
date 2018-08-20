package com.invest.mappers;

import com.invest.domain.User;
import com.invest.dtos.UserDto;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper implements BasicMapper<User, UserDto> {

    @Override
    public void accept(Object o) {

    }

    @Override
    public User mapperToDomain(UserDto userDto) {
        return new User(
                userDto.getLogin(),
                userDto.getPassword(),
                userDto.getEmail()
        );
    }

    @Override
    public List<User> mapperToListDomain(List<UserDto> listDto) {
       return listDto.stream()
                .map(this::mapperToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto mapperToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                user.getEmail()
        );
    }

    @Override
    public List<UserDto> mapperToListDto(List<User> listDomain) {
        return listDomain.stream()
                .map(this::mapperToDto)
                .collect(Collectors.toList());
    }
}
