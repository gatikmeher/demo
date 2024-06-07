package com.example.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    private Integer id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
}
