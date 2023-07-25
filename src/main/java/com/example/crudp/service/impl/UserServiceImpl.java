package com.example.crudp.service.impl;

import com.example.crudp.dto.UserDto;
import com.example.crudp.entity.User;
import com.example.crudp.exceptions.EmailException;
import com.example.crudp.exceptions.ResourceNotFound;
import com.example.crudp.mapper.UserMapper;
import com.example.crudp.repository.UserRepository;
import com.example.crudp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if (optionalUser.isPresent()){
            throw new EmailException("Email already exists");

        }
        User user2= UserMapper.mapToUser(userDto);

      User savedUser=  userRepository.save(user2);

      UserDto savedUserDto =UserMapper.mapToUserDto(savedUser);
        return savedUserDto;
    }

    @Override
    public List<UserDto> getAll() {

        List<User> users= userRepository.findAll();
        List<UserDto>  userDtos= users.stream().map((user)->UserMapper.mapToUserDto(user)).
                collect(Collectors.toList());
        return userDtos;

    }

    @Override
    public UserDto findById(Long id) {
       User user=userRepository.findById(id).orElseThrow(
               ()-> new ResourceNotFound("User","id","id")
       );
        return UserMapper.mapToUserDto(user);

    }

    @Override
    public UserDto updateUserById(UserDto user) {
       User existingUser= userRepository.findById(user.getId()).orElseThrow(
               ()-> new ResourceNotFound("User","id","user.getId")
       );
      // existingUser.setFirstName();

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());

        existingUser.setEmail(user.getEmail());
        User updatedUser =userRepository.save(existingUser);

        return UserMapper.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser (Long id) {
        User existingUser= userRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFound("User","id","id")
        );

        userRepository.deleteById(id);
    }
}
