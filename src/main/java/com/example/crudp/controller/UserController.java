package com.example.crudp.controller;

import com.example.crudp.dto.UserDto;
import com.example.crudp.entity.User;
import com.example.crudp.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor

public class UserController {
    private UserService userService;
    @PostMapping("/new")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto user){
      UserDto newUser=  userService.createUser(user);
      return  new ResponseEntity<>(newUser,HttpStatus.CREATED );

    }
    @GetMapping
    public  ResponseEntity<List<UserDto>> getAll(UserDto user){
        List<UserDto> users= userService.getAll();
        return new ResponseEntity<>(users,HttpStatus.FOUND);
    }
    @GetMapping("{id}")
    public ResponseEntity<UserDto>  findById(@PathVariable Long id){
        UserDto user= userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateByid(@PathVariable Long id,
                                              @RequestBody@Valid UserDto user){
        user.setId(id);
        UserDto updatedUser=userService.updateUserById(user);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
}
@DeleteMapping("{id}")
public void deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
}
}
