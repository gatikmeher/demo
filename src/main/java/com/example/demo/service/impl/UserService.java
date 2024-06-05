package com.example.demo.service.impl;

import com.example.demo.dto.UserDto;
import com.example.demo.mysql.model.UserModel;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto getUserById(Integer id) {
        Optional<UserModel> userModel = userRepository.findById(id);
        UserDto userDto = new UserDto();
        if (userModel.isPresent()) {
            userDto = convertUserModelToUserDto(userModel.get());
        }
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<UserModel> userModelList = (List<UserModel>) userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for(UserModel userModel: userModelList){
            UserDto userDto = convertUserModelToUserDto(userModel);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        Optional<UserModel> userModelOptional = userRepository.findById(userDto.getId());
        if(userModelOptional.isEmpty()) {
            System.out.println("User data with id: " + userDto.getId() + " not found");
        } else {
            UserModel userModel = convertUserDtoToUserModel(userDto, userModelOptional.get());
            userModel.setId(userDto.getId());
            userModel = userRepository.save(userModel);
            return convertUserModelToUserDto(userModel);
        }
        return userDto;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        UserModel userModel = new UserModel();
        userModel = convertUserDtoToUserModel(userDto, userModel);
        userModel.setCreatedBy(1);
        userModel.setCreatedAt(LocalDateTime.now());
        userModel = userRepository.save(userModel);
        return convertUserModelToUserDto(userModel);
    }

    private UserModel convertUserDtoToUserModel(UserDto userDto, UserModel userModel) {
        userModel.setUsername(userDto.getUsername());
        userModel.setFirstName(userDto.getFirstName());
        userModel.setLastName(userDto.getLastName());
        userModel.setMobile(userDto.getMobile());
        userModel.setEmail(userDto.getEmail());
        userModel.setUpdatedBy(1);
        userModel.setUpdatedAt(LocalDateTime.now());
        return userModel;
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
