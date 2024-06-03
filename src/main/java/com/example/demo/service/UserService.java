package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.mysql.model.UserModel;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserDto getAllUsers() {
        Optional<UserModel> userModel = userRepository.findById(4);
        UserDto userDto = new UserDto();
        if(userModel.isPresent()) {
            userDto =  convertUserModelToUserDto(userModel.get());
        }
        return userDto;
    }

    private UserDto convertUserModelToUserDto(UserModel userModel) {
        UserDto userDto = new UserDto();
        userDto.setId(userModel.getId());
        userDto.setUsername(userModel.getUsername());
        userDto.setFirstName(userModel.getFirstName());
        userDto.setLastName(userModel.getLastName());
        userDto.setMobile(userModel.getMobile());
        userDto.setEmail(userModel.getEmail());
        return userDto;
    }
}
