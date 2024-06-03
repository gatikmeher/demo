package com.example.demo.service;

import com.example.demo.dto.AddressDto;
import com.example.demo.dto.EmployeeDto;

import java.util.ArrayList;
import java.util.List;

public class EmployeeServicePostgres {

    public EmployeeDto getAllEmployees() {

        // Postgres Database

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("John Skuller");
        employeeDto.setDepartment("IT");
        employeeDto.setDesignation("QA Engineer");

        List<AddressDto> addressDtoList =  new ArrayList<>();
        AddressDto addressDto1 = new AddressDto();
        addressDto1.setAddressType('P');
        addressDto1.setStreet("12 Auckland St");
        addressDto1.setCity("Springfield");
        addressDto1.setState("MA");
        addressDto1.setCountry("USA");

        AddressDto addressDto2 = new AddressDto();
        addressDto2.setAddressType('C');
        addressDto2.setStreet("987 Spring St");
        addressDto2.setCity("Ohia");
        addressDto2.setState("OH");
        addressDto2.setCountry("USA");

        addressDtoList.add(addressDto1);
        addressDtoList.add(addressDto2);

        employeeDto.setAddresses(addressDtoList);

        return employeeDto;
    }
}
