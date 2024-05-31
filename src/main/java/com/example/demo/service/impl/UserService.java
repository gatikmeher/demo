package com.example.demo.service.impl;

import com.example.demo.dto.UserDto;
import com.example.demo.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Override
    public UserDto getUser() {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setUsername("gatimeher");
        userDto.setAge(67L);
        return userDto;
    }

}
