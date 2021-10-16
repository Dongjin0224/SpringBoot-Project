package com.example.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("")
public class WebController {

    @GetMapping("index")
    public String index(){
        return "index";
    }

    @GetMapping("elements")
    public String elements(){
        return "elements";
    }

    @GetMapping("generic")
    public String generic(){
        return "generic";
    }

    @GetMapping("mainBoard")
    public String mainBoard(){ return "mainBoard"; }

    @GetMapping("copy")
    public String copy(){return "copy";}

    @GetMapping("detail")
    public String detail(){return "detail";}

    @GetMapping("map")
    public String map(){return "map";}

    @GetMapping("report")
    public String report(){return "report";}

    @GetMapping("appointment")
    public String appointment(){return "appointment";}

    @GetMapping("membership")
    public String membership(){return "membership";}

    @GetMapping("payTerm")
    public String payTerm(){return "payTerm";}

    @GetMapping("myPageUser")
    public String myPageUser(){return "myPageUser";}

    @GetMapping("myPageDoc")
    public String myPageDoc(){return "myPageDoc";}

    @GetMapping("test2")
    public String test2(){return "test2";}
}
