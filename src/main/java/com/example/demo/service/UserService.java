package com.example.demo.service;

import com.example.demo.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserDto getUser() {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setUsername("gatimeher");
        userDto.setAge(67L);
        return userDto;
    }

}
