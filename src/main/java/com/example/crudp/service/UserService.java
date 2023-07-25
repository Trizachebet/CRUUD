package com.example.crudp.service;

import com.example.crudp.dto.UserDto;
import com.example.crudp.entity.User;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);
    List<UserDto> getAll();
    UserDto findById(Long id);
    UserDto updateUserById(UserDto user);
    void deleteUser (Long id);

}
