package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.IUserService;
import com.example.demo.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerAutowire {

    @Autowired
    IUserService userService;


    @RequestMapping("/v1/users/autowire")
    public ResponseEntity<Object> getUsersAutowire() {
        System.out.println("Service Autowired: " + userService.hashCode());
        UserDto userDto = userService.getUser();
        return ResponseEntity.ok().body(userDto);
    }

}
