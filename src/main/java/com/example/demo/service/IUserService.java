package com.example.demo.service;

import com.example.demo.dto.request.UserRequestDto;
import com.example.demo.dto.response.UserResponseDto;

import java.util.List;

public interface IUserService {

    public UserRequestDto getUserById(Integer id);
    public List<UserRequestDto> getAllUsers();

    public UserRequestDto updateUser(UserRequestDto userRequestDto);

    public UserRequestDto updatePartialUser(UserRequestDto userRequestDto);

    public UserResponseDto createUser(UserRequestDto userRequestDto);
}
