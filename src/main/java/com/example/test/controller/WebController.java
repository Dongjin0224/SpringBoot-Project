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

    @GetMapping("guide")
    public String guide(){return "guide";}

    @GetMapping("detail")
    public String detail(){return "detail";}

    @GetMapping("report")
    public String report(){return "report";}

    @GetMapping("write")
    public String write(){return "write";}

    public String volunteer_register(){return "volunterr_register";}

    @GetMapping("appointment")
    public String appointment(){return "appointment";}

    @GetMapping("membership")
    public String membership(){return "membership";}

    @GetMapping("myPageDoc")
    public String myPageDoc(){return "myPageDoc";}

    @GetMapping("login")
    public String login(){return "login";}

    @GetMapping("memberSignUp")
    public String memberSignUp(){return "memberSignUp";}

    @GetMapping("doctorSignUp")
    public String doctorSignUp(){return "doctorSignUp";}

    @GetMapping("medicalService")
    public String medicalService(){return "medicalService";}

    @GetMapping("msRead")
    public String msRead(){return "msRead";}
}
