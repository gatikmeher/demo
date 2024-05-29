package com.example.demo.service;

import com.example.demo.dto.AddressDto;
import com.example.demo.dto.EmployeeDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    public EmployeeDto getAllEmployees() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("John Doe");
        employeeDto.setDepartment("IT");
        employeeDto.setDesignation("Software Engineer");

        List<AddressDto> addressDtoList =  new ArrayList<>();
        AddressDto addressDto1 = new AddressDto();
        addressDto1.setAddressType('P');
        addressDto1.setStreet("1234 Main St");
        addressDto1.setCity("Springfield");
        addressDto1.setState("IL");
        addressDto1.setCountry("USA");

        AddressDto addressDto2 = new AddressDto();
        addressDto2.setAddressType('P');
        addressDto2.setStreet("1234 Main St");
        addressDto2.setCity("Springfield");
        addressDto2.setState("IL");
        addressDto2.setCountry("USA");

        addressDtoList.add(addressDto1);
        addressDtoList.add(addressDto2);

        employeeDto.setAddresses(addressDtoList);

        return employeeDto;
    }
}
