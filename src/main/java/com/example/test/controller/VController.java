package com.example.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("")
public class VController {
    //  vanny
    @GetMapping("volunteer/volunteer_register")
    public String volunteer_register(){return "volunteer/volunteer_register";}
}
