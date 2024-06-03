package com.example.demo.controller;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    EmployeeService employeeServiceConstructor = new EmployeeService();
    EmployeeService employeeServiceSetter = new EmployeeService();


    public EmployeeController(EmployeeService employeeService) {
        this.employeeServiceConstructor = employeeService;
    }

    @RequestMapping("/v1/employees/constructor")
    public ResponseEntity<Object> getEmployeeFromMySqlC() {
        EmployeeDto employeeDto = employeeServiceConstructor.getAllEmployees();
        System.out.println("Employee Service: " + employeeServiceConstructor.hashCode());
        return ResponseEntity.ok(employeeDto);
    }

    @RequestMapping("/v1/employees/setter")
    public ResponseEntity<Object> getEmployeeFromMySqlS() {
        EmployeeDto employeeDto = employeeServiceSetter.getAllEmployees();
        System.out.println("Employee Service: " + employeeServiceSetter.hashCode());
        return ResponseEntity.ok(employeeDto);
    }
}
