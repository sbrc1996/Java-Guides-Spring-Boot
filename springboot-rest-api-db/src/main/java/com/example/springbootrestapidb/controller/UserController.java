package com.example.springbootrestapidb.controller;

import com.example.springbootrestapidb.dto.UserDto;
import com.example.springbootrestapidb.entity.User;
import com.example.springbootrestapidb.exception.ErrorDetails;
import com.example.springbootrestapidb.exception.ResourceNotFoundException;
import com.example.springbootrestapidb.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
    private UserService userService;

    //Build CREATE User REST API.
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto saveUserDto = userService.createUser(userDto);
        return new ResponseEntity<>(saveUserDto, HttpStatus.CREATED);
    }

    //Build GET USER BY ID Rest API.
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") int userId){
        UserDto userByIdDto = userService.getUserById(userId);
        return new ResponseEntity<>(userByIdDto,HttpStatus.OK);
    }

    //Build Get ALL Users REST API.
    @GetMapping("all")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> allUsersDto = userService.getAllUsers();
        return new ResponseEntity<>(allUsersDto,HttpStatus.OK);
    }

    //Build Update User by Id REST API.
    @PutMapping("update/{id}")
    public ResponseEntity<UserDto> updateUserById(@PathVariable("id") int userId,@RequestBody UserDto userDto){

        userDto.setId(userId);
        UserDto updatedUserDto = userService.updateUser(userDto);
        return new ResponseEntity<>(updatedUserDto,HttpStatus.OK);
    }

    //Build Delete User by Id REST API.
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") int userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User "+userId+" has been successfully deleted",HttpStatus.OK);
    }

}
