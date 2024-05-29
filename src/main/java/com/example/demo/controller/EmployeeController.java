package com.example.demo.controller;

import com.example.demo.dto.AddressDto;
import com.example.demo.dto.EmployeeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @RequestMapping("/employees")
    public ResponseEntity<Object> getEmployee() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("John Doe");
        employeeDto.setDepartment("IT");
        employeeDto.setDesignation("Software Engineer");

        AddressDto addressDto = new AddressDto();
        addressDto.setAddressType('P');
        addressDto.setStreet("1234 Main St");
        addressDto.setCity("Springfield");
        addressDto.setState("IL");
        addressDto.setCountry("USA");

        employeeDto.setAddress(addressDto);

        return ResponseEntity.ok(employeeDto);
    }

}
