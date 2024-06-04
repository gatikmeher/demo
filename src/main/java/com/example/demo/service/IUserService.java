package com.example.demo.service;

import com.example.demo.dto.UserDto;

public interface IUserService {

    public UserDto getUserById(Integer id);

    public UserDto createUser(UserDto userDto);
}
