package com.example.springbootrestapidb.services.impl;

import com.example.springbootrestapidb.dto.UserDto;
import com.example.springbootrestapidb.entity.User;
import com.example.springbootrestapidb.exception.EmailAlreadyExistsExceptions;
import com.example.springbootrestapidb.exception.ResourceNotFoundException;
import com.example.springbootrestapidb.mapper.UserMapper;
import com.example.springbootrestapidb.repository.UserRepository;
import com.example.springbootrestapidb.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        //1. convert to Normal User from DTO object.

//        User user = UserMapper.mapToUser(userDto);        //Using the manual Mapping.

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistsExceptions("Email already exists.");
        }

        User user = modelMapper.map(userDto,User.class);    //Using the Modelmapper Class.

        //2. Get the saved object in DB from JPA.
        User savedUser =  userRepository.save(user);

        //3. Convert back to DTO object and return it Controller.
//        UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);        //Using the manual Mapping.
        UserDto savedUserDto = modelMapper.map(savedUser,UserDto.class);   //Using the Modelmapper Class.
        return savedUserDto;
    }

    @Override
    public UserDto getUserById(int id) {

        //1. Get the User from Db using JPA.
        User optionalUser= userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User","id",id)
        );

        //2. Convert the User to UserDTO object and return it to the Controller.
//      UserDto userDto= UserMapper.mapToUserDto(optionalUser.get());     //Using the manual Mapping.

        UserDto userDto= modelMapper.map(optionalUser,UserDto.class); //Using the Modelmapper Class.
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        //1. Get all the Users from the DB using JPA.
        List<User> allUsers = userRepository.findAll();

        //2. Convert each of these Users into UserDTO Objects and store in a List and return the List
        List<UserDto> allUserDto = new ArrayList<>();

        for(User user : allUsers){
//            allUserDto.add(UserMapper.mapToUserDto(user));        //Using the manual Mapping.
            allUserDto.add(modelMapper.map(user,UserDto.class));    //Using the Modelmapper Class.
        }
        return allUserDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        //1. Convert the incomming UserDTO to User Object.
//        User user = UserMapper.mapToUser(userDto);          //Using the manual Mapping.
        User user = modelMapper.map(userDto,User.class);              //Using the Modelmapper Class.

        //2. Find & Get the User from the DB using JPA.
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User","id",user.getId())
        );

        //3. Update the User Details using the
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User savedUser =  userRepository.save(existingUser);

        //4. Convert the Saved User to UserDTO and return it to Controller.
//        return UserMapper.mapToUserDto(savedUser);          //Using the manual Mapping.
        return modelMapper.map(savedUser,UserDto.class);            //Using the Modelmapper Class.
    }

    @Override
    public void deleteUser(int id) {
        //No DTO Obejct needed here.

        // Check if the User exists in the DB using JPA.
        User existingUser = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User","id",id)
        );

        userRepository.deleteById(id);
    }

}
