package com.example.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequestDto {

    private Integer id;
    @NotNull(message = "'username' cannot be null")
    @Size(min = 6, max = 45, message = "'username' can be minimum 6 character and maximum 45 characters")
    private String username;
    private String password;

    @Pattern(regexp = "[a-zA-Z]+", message = "'firstName' can only contain alphabets")
    private String firstName;
    private String lastName;

    private LocalDate dateOfBirth;

    private String mobile;
    private String email;
}
