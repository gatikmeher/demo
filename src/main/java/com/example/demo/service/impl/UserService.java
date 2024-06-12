package com.example.demo.service.impl;

import com.example.demo.dto.request.UserRequestDto;
import com.example.demo.dto.response.UserGetResponseDto;
import com.example.demo.dto.response.UserPostResponseDto;
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
    public UserRequestDto getUserById(Integer id) {
        Optional<User> userModel = userRepository.findById(id);
        UserRequestDto userRequestDto = new UserRequestDto();
        if (userModel.isPresent()) {
            userRequestDto = convertUserModelToUserDto(userModel.get());
        }
        return userRequestDto;
    }

    @Override
    public List<UserGetResponseDto> getAllUsers() {

        List<User> userList = (List<User>) userRepository.findAll();
        List<UserGetResponseDto> userGetResponseDtoList = new ArrayList<>();
        for (User user : userList) {
            UserGetResponseDto userGetResponseDto = convertUserModelToUserGetResponseDto(user);
            userGetResponseDtoList.add(userGetResponseDto);
        }
        return userGetResponseDtoList;
    }

    @Override
    public UserRequestDto updateUser(UserRequestDto userRequestDto) {
        Optional<User> userModelOptional = userRepository.findById(userRequestDto.getId());
        if (userModelOptional.isEmpty()) {
            System.out.println("User data with id: " + userRequestDto.getId() + " not found");
        } else {
            User user = convertUserDtoToUserModel(userRequestDto, userModelOptional.get());
            user.setId(userRequestDto.getId());
            user = userRepository.save(user);
            return convertUserModelToUserDto(user);
        }
        return userRequestDto;
    }

    @Override
    public UserRequestDto updatePartialUser(UserRequestDto userRequestDto) {
        Optional<User> userModelOptional = userRepository.findById(userRequestDto.getId());
        if (userModelOptional.isEmpty()) {
            System.out.println("User data with id: " + userRequestDto.getId() + " not found");
        } else {
            User user = userModelOptional.get();
            user.setFirstName(userRequestDto.getFirstName() != null && !userRequestDto.getFirstName().equals(user.getFirstName()) ? userRequestDto.getFirstName() : user.getFirstName());
            user.setLastName(userRequestDto.getLastName() != null && !userRequestDto.getLastName().equals(user.getLastName()) ? userRequestDto.getLastName() : user.getLastName());
            user.setEmail(userRequestDto.getEmail() != null && !userRequestDto.getEmail().equals(user.getEmail()) ? userRequestDto.getEmail() : user.getEmail());
            user.setMobile(userRequestDto.getMobile() != null && !userRequestDto.getMobile().equals(user.getMobile()) ? userRequestDto.getMobile() : user.getMobile());
            user.setUpdatedBy(2);
            user.setUpdatedAt(LocalDateTime.now());
            userRepository.save(user);
            return convertUserModelToUserDto(user);
        }
        return userRequestDto;
    }

    @Override
    @Transactional
    public UserPostResponseDto createUser(UserRequestDto userRequestDto) {

        // Below code saves data in users table
        User user = new User();
        user = convertUserDtoToUserModel(userRequestDto, user);
        user.setCreatedBy(1);
        user.setCreatedAt(LocalDateTime.now());
        user = userRepository.save(user);

        // Below code saves data in user_credentials table
        UserCredential userCredential = new UserCredential();
        String uuid = UUID.randomUUID().toString();
        System.out.println("UUID: " + uuid);
        final String computedPassword = Hashing.sha256()
                .hashString(userRequestDto.getPassword(), StandardCharsets.UTF_8).toString() + uuid;
        userCredential.setUserId(user.getId());
        userCredential.setUsername(userRequestDto.getUsername());
        userCredential.setPassword(computedPassword);
        userCredential.setPasswordSalt(uuid);
        userCredential.setLoginDateTime(LocalDateTime.now());
        userCredential.setCreatedBy(1);
        userCredential.setCreatedAt(LocalDateTime.now());
        userCredential.setUpdatedBy(1);
        userCredential.setUpdatedAt(LocalDateTime.now());
        userCredentialRepository.save(userCredential);

        UserPostResponseDto userPostResponseDto = new UserPostResponseDto();
        userPostResponseDto.setId(user.getId());
        userPostResponseDto.setFirstName(user.getFirstName());
        userPostResponseDto.setUsername(userCredential.getUsername());
        return userPostResponseDto;
    }

    private User convertUserDtoToUserModel(UserRequestDto userRequestDto, User user) {

        user = User.builder()
                .firstName(userRequestDto.getFirstName())
                .lastName(user.getLastName())
                .mobile(userRequestDto.getMobile())
                .email(userRequestDto.getEmail())
                .updatedAt(LocalDateTime.now())
                .updatedBy(1)
                .build();
        return user;
    }

    private UserGetResponseDto convertUserModelToUserGetResponseDto(User user) {
        UserGetResponseDto userGetResponseDto = new UserGetResponseDto();
        userGetResponseDto.setId(user.getId());
        userGetResponseDto.setFirstName(user.getFirstName());
        userGetResponseDto.setLastName(user.getLastName());
        userGetResponseDto.setMobile(user.getMobile());
        userGetResponseDto.setEmail(user.getEmail());
        return userGetResponseDto;
    }

    private UserRequestDto convertUserModelToUserDto(User user) {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setId(user.getId());
        userRequestDto.setFirstName(user.getFirstName());
        userRequestDto.setLastName(user.getLastName());
        userRequestDto.setMobile(user.getMobile());
        userRequestDto.setEmail(user.getEmail());
        return userRequestDto;
    }
}
