package com.example.springbootrestapidb.services;

import com.example.springbootrestapidb.dto.UserDto;
import com.example.springbootrestapidb.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    UserDto getUserById(int id);
    List<UserDto> getAllUsers();
    UserDto updateUser(UserDto user);
    void deleteUser(int id);
}
