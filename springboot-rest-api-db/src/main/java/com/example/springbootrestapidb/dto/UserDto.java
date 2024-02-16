package com.example.springbootrestapidb.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotNull(message = "Id field should not be null")
    private int id;
    @NotNull(message = "Firstname field should not be null")
    private String firstName;
    private String lastName;
    @Email
    private String email;

}
