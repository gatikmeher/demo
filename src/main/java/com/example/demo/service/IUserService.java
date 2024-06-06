package com.example.demo.service;

import com.example.demo.dto.UserDto;

import java.util.List;

public interface IUserService {

    public UserDto getUserById(Integer id);
    public List<UserDto> getAllUsers();

    public UserDto updateUser(UserDto userDto);

    public UserDto updatePartialUser(UserDto userDto);

    public UserDto createUser(UserDto userDto);
}
