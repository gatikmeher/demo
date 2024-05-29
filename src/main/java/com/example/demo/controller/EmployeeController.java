package com.example.demo.controller;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    EmployeeService employeeServiceSetter = new EmployeeService();

    EmployeeService employeeServiceConstructor = new EmployeeService();

    public EmployeeController(EmployeeService employeeService) {
        this.employeeServiceConstructor = employeeService;
    }

    @RequestMapping("/employees")
    public ResponseEntity<Object> getEmployee() {

        EmployeeService employeeServiceNormal = new EmployeeService();

        System.out.println("Normal EMP Service: " + employeeServiceNormal.hashCode());
        EmployeeDto employeeDto3 = employeeServiceNormal.getAllEmployees();

        System.out.println("Setter EMP Service: " + employeeServiceSetter.hashCode());
        EmployeeDto employeeDto1 = employeeServiceSetter.getAllEmployees();

        System.out.println("Constructor EMP Service: " + employeeServiceConstructor.hashCode());
        EmployeeDto employeeDto2 = employeeServiceConstructor.getAllEmployees();

        return ResponseEntity.ok(employeeDto2);
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeServiceSetter = employeeService;
    }
}
