package com.example.demo.service.impl;

import com.example.demo.dto.UserDto;
import com.example.demo.mysql.model.User;
import com.example.demo.mysql.model.UserCredential;
import com.example.demo.repository.UserCredentailsRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.IUserService;
import com.google.common.hash.Hashing;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserCredentailsRepository userCredentialRepository;

    @Override
    public UserDto getUserById(Integer id) {
        Optional<User> userModel = userRepository.findById(id);
        UserDto userDto = new UserDto();
        if (userModel.isPresent()) {
            userDto = convertUserModelToUserDto(userModel.get());
        }
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> userList = (List<User>) userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for(User user : userList){
            UserDto userDto = convertUserModelToUserDto(user);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        Optional<User> userModelOptional = userRepository.findById(userDto.getId());
        if(userModelOptional.isEmpty()) {
            System.out.println("User data with id: " + userDto.getId() + " not found");
        } else {
            User user = convertUserDtoToUserModel(userDto, userModelOptional.get());
            user.setId(userDto.getId());
            user = userRepository.save(user);
            return convertUserModelToUserDto(user);
        }
        return userDto;
    }

    @Override
    public UserDto updatePartialUser(UserDto userDto) {
        Optional<User> userModelOptional = userRepository.findById(userDto.getId());
        if(userModelOptional.isEmpty()) {
            System.out.println("User data with id: " + userDto.getId() + " not found");
        } else {
            User user = userModelOptional.get();
            user.setFirstName(userDto.getFirstName() != null && !userDto.getFirstName().equals(user.getFirstName()) ? userDto.getFirstName() : user.getFirstName());
            user.setLastName(userDto.getLastName() != null && !userDto.getLastName().equals(user.getLastName()) ? userDto.getLastName() : user.getLastName());
            user.setEmail(userDto.getEmail() != null && !userDto.getEmail().equals(user.getEmail()) ? userDto.getEmail() : user.getEmail());
            user.setMobile(userDto.getMobile() != null && !userDto.getMobile().equals(user.getMobile()) ? userDto.getMobile() : user.getMobile());
            user.setUpdatedBy(2);
            user.setUpdatedAt(LocalDateTime.now());
            userRepository.save(user);
            return convertUserModelToUserDto(user);
        }
        return userDto;
    }

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {

        // Below code saves data in users table
        User user = new User();
        user = convertUserDtoToUserModel(userDto, user);
        user.setCreatedBy(1);
        user.setCreatedAt(LocalDateTime.now());
        user = userRepository.save(user);

        // Below code saves data in user_credentials table
        UserCredential userCredential = new UserCredential();
        String uuid = UUID.randomUUID().toString();
        System.out.println("UUID: " + uuid);
        final String computedPassword = Hashing.sha256()
                .hashString(userDto.getPassword(), StandardCharsets.UTF_8).toString() + uuid;
        userCredential.setUserId(user.getId());
        userCredential.setUsername(userDto.getUsername());
        userCredential.setPassword(computedPassword);
        userCredential.setPasswordSalt(uuid);
        userCredential.setLoginDateTime(LocalDateTime.now());
        userCredential.setCreatedBy(1);
        userCredential.setCreatedAt(LocalDateTime.now());
        userCredential.setUpdatedBy(1);
        userCredential.setUpdatedAt(LocalDateTime.now());
        userCredentialRepository.save(userCredential);
        return convertUserModelToUserDto(user);
    }

    private User convertUserDtoToUserModel(UserDto userDto, User user) {
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setMobile(userDto.getMobile());
        user.setEmail(userDto.getEmail());
        user.setUpdatedBy(1);
        user.setUpdatedAt(LocalDateTime.now());
        return user;
    }

    private UserDto convertUserModelToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setMobile(user.getMobile());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
