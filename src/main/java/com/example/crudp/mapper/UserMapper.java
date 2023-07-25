package com.example.crudp.mapper;

import com.example.crudp.dto.UserDto;
import com.example.crudp.entity.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user){
        UserDto userDto= new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()

        );
        return userDto;
    }
    //convert UserDto intgo user jpa Entity
    public static User mapToUser(UserDto userDto){
        User user= new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()

        );
        return user;
    }
}

