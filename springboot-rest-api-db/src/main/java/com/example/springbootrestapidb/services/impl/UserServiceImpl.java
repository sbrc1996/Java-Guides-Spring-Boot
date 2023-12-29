package com.example.springbootrestapidb.services.impl;

import com.example.springbootrestapidb.dto.UserDto;
import com.example.springbootrestapidb.entity.User;
import com.example.springbootrestapidb.mapper.UserMapper;
import com.example.springbootrestapidb.repository.UserRepository;
import com.example.springbootrestapidb.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto userDto) {
        //1. convert to Normal User from DTO object.
        User user = UserMapper.mapToUser(userDto);

        //2. Get the saved object in DB from JPA.
        User savedUser =  userRepository.save(user);

        //3. Convert back to DTO object and return it Controller.
        UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
        return savedUserDto;
    }

    @Override
    public UserDto getUserById(int id) {

        //1. Get the User from Db using JPA.
        Optional<User> optionalUser= userRepository.findById(id);

        //2. Convert the User to UserDTO object and return it to the Controller.
        UserDto userDto= UserMapper.mapToUserDto(optionalUser.get());
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        //1. Get all the Users from the DB using JPA.
        List<User> allUsers = userRepository.findAll();

        //2. Convert each of these Users into UserDTO Objects and store in a List and return the List
        List<UserDto> allUserDto = new ArrayList<>();

        for(User user : allUsers){
            allUserDto.add(UserMapper.mapToUserDto(user));
        }
        return allUserDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        //1. Convert the incomming UserDTO to User Object.
        User user = UserMapper.mapToUser(userDto);

        //2. Find & Get the User from the DB using JPA.
        User existingUser = userRepository.findById(user.getId()).get();

        //3. Update the User Details using the
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User savedUser =  userRepository.save(existingUser);

        //4. Convert the Saved User to UserDTO and return it to Controller.
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public void deleteUser(int id) {
        //No DTO Obejct needed here.
        userRepository.deleteById(id);
    }

}
