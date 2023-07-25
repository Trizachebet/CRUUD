package com.example.crudp.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class UserDto {
    private Long id;
    @NotEmpty(message = "first name should not be empty")
    private  String firstName;
    private String lastName;
    private String email;
}
