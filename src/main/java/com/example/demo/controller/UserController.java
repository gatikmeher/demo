package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/v1/users")
    public ResponseEntity<Object> getUser() {
        UserDto userDto = userService.getAllUsers();
        System.out.println("Service Constructor: " + userService.hashCode());
        return ResponseEntity.ok(userDto);
    }
}
