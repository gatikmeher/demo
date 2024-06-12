package com.example.demo.validator;

import com.example.demo.exception.BusinessException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
public class UserValidator {

    public boolean validateAge(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            throw new BusinessException("Date of birth cannot be null!!!!");
        } else {
            LocalDate currentDate = LocalDate.now();
            int age = currentDate.getYear() - dateOfBirth.getYear();
            if (age < 18) {
                return false;
            } else {
                return true;
            }
        }

    }
}
