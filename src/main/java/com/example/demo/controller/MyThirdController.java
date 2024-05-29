package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyThirdController {

    @RequestMapping("/mythirdcontroller")
    public String getMyThirdController() {
        return "Data from my third controller";
    }
}
