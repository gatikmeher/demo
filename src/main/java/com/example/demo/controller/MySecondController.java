package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MySecondController {

    @RequestMapping("/data")
    public String getData() {
        return "My Data";
    }

    @RequestMapping("/data/new")
    public String getNewData() {
        return "My New Data";
    }
}
