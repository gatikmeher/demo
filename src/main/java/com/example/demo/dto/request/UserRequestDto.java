package com.example.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequestDto {

    private Integer id;
    @NotNull(message = "'username' cannot be null")
    @Size(min = 6, max = 45, message = "'username' can be minimum 6 character and maximum 45 characters")
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
}
