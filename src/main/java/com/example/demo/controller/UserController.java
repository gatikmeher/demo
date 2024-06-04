package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {


    @Autowired
    IUserService userService;

    @RequestMapping(value = "/v1/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUser(@PathVariable("id") Integer id) {
        UserDto userDto = userService.getUserById(id);
        System.out.println("Service Constructor: " + userService.hashCode());
        return ResponseEntity.ok(userDto);

    }

    @RequestMapping(value = "/v1/users", method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(@RequestBody UserDto userDto) {
        UserDto userDtoReturn = userService.createUser(userDto);
        return ResponseEntity.ok(userDtoReturn);
    }
}
