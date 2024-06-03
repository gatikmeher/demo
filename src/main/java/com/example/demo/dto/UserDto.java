package com.example.demo.dto;

<<<<<<< HEAD
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
}
