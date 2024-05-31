package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    UserService userServiceConstructor = new UserService();
    UserService userServiceSetter = new UserService();

    public UserController(UserService userService) {
        this.userServiceConstructor = userService;
    }

    public void setUserServiceConstructor(UserService userServiceConstructor) {
        this.userServiceConstructor = userServiceConstructor;
    }

    @RequestMapping("/v1/users/constructor")
    public ResponseEntity<Object> getUsersConstructor  () {
        System.out.println("Service Constructor: " + userServiceConstructor.hashCode());
        UserDto userDto = userServiceConstructor.getUser();
        return ResponseEntity.ok().body(userDto);
    }

    @RequestMapping("/v1/users/setter")
    public ResponseEntity<Object> getUsersSetter  () {
        UserDto userDto = userServiceSetter.getUser();
        System.out.println("Service Setter: " + userServiceSetter.hashCode());
        return ResponseEntity.ok().body(userDto);
    }
}
