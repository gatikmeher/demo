package com.example.demo.controller;

import com.example.demo.dto.request.UserRequestDto;
import com.example.demo.dto.response.ErrorResponseDto;
import com.example.demo.dto.response.UserResponseDto;
import com.example.demo.service.IUserService;
import com.example.demo.validator.UserValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {


    @Autowired
    IUserService userService;

    @Autowired
    UserValidator userValidator;

    @GetMapping(path = "/v1/users/{id}")
    public ResponseEntity<Object> getUser(@PathVariable("id") Integer id) {
        UserRequestDto userRequestDto = userService.getUserById(id);
        return ResponseEntity.ok(userRequestDto);

    }

    @PutMapping(path = "/v1/users/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable("id") Integer id, @RequestBody UserRequestDto userRequestDto) {
        userRequestDto.setId(id);
        UserRequestDto userRequestDtoReturn = userService.updateUser(userRequestDto);
        return ResponseEntity.ok(userRequestDto);
    }

    @PatchMapping(path = "/v1/users/{id}")
    public ResponseEntity<Object> updatePartialUser(@PathVariable("id") Integer id, @RequestBody UserRequestDto userRequestDto) {
        userRequestDto.setId(id);
        UserRequestDto userRequestDtoReturn = userService.updatePartialUser(userRequestDto);
        return ResponseEntity.ok(userRequestDto);
    }

    @GetMapping(path = "/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllUsers() {
        List<UserRequestDto> userRequestDtoList = userService.getAllUsers();
        return ResponseEntity.ok(userRequestDtoList);

    }

    @PostMapping(path = "/v1/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        boolean isValidAge = userValidator.validateAge(userRequestDto.getDateOfBirth());
        if(!isValidAge) {
            ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                    .errorMessage("Age should be greated than 18 yrs")
                    .httStatusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
            return ResponseEntity.badRequest().body(errorResponseDto);
        }
        UserResponseDto userResponseDto = userService.createUser(userRequestDto);
        return ResponseEntity.created(URI.create("/v1/users/" + userResponseDto.getId())).body(userResponseDto);
    }

}

