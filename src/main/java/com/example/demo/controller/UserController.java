package com.example.demo.controller;

import com.example.demo.dto.UserDto;
<<<<<<< HEAD
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
=======
import com.example.demo.service.impl.UserService;
>>>>>>> 24453afa63e6293dbb1c22166e2d627c365834a5
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

<<<<<<< HEAD
    @Autowired
    UserService userService;

    @RequestMapping("/v1/users")
    public ResponseEntity<Object> getUser() {
        UserDto userDto = userService.getAllUsers();
        System.out.println("Service Constructor: " + userService.hashCode());
        return ResponseEntity.ok(userDto);
=======
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
>>>>>>> 24453afa63e6293dbb1c22166e2d627c365834a5
    }
}
