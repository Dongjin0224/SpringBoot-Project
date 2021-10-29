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
    public String mainBoard(){ return "mainBoard/mainBoard"; }

    //  vanny
    @GetMapping("write")
    public String write(){return "mainBoard/write";}
    //  vanny
    @GetMapping("header")
    public String header(){return "fixed/header";}
    //  vanny
    @GetMapping("term")
    public String term(){return "term/term";}
    //  vanny
    @GetMapping("privacy_policy")
    public String privacy_policy(){return "term/privacy_policy";}
    //  vanny
    @GetMapping("location_policy")
    public String location_policy(){return "term/location_policy";}
    //  vanny
    @GetMapping("volunteer_register")
    public String volunteer_register(){return "volunteer/volunteer_register";}
    //  hong
    @GetMapping("detail")
    public String detail(){return "mainBoard/detail";}


    //  hong
    @GetMapping("report")
    public String report(){return "mainBoard/report";}
    //  dong
    @GetMapping("appointment")
    public String appointment(){return "appointment/appointment";}
    //  dong
    @GetMapping("membership")
    public String membership(){return "payment/membership";}
    //  dong
    @GetMapping("payTerm")
    public String payTerm(){return "term/payTerm";}
    //  lim
    @GetMapping("myPageUser")
    public String myPageUser(){return "myPage/myPageUser";}
    //  lim
    @GetMapping("myPageDoc")
    public String myPageDoc(){return "myPage/myPageDoc";}
//    //  jin
//    @GetMapping("login")
//    public String login(){return "user/login";}

//    @GetMapping
//    public String docLogin(){return "user/docLogin";}
    /*//  jin
    @GetMapping("memberSignUp")
    public String memberSignUp(){return "user/memberSignUp";}*/
    //  jin
    @GetMapping("doctorSignUp")
    public String doctorSignUp(){return "user/doctorSignUp";}
    //  jin
    @GetMapping("medicalService")
    public String medicalService(){return "volunteer/medicalService";}
    //  jin
    @GetMapping("msRead")
    public String msRead(){return "volunteer/msRead";}
    //  jin
    @GetMapping("explanation")
    public String explanation(){return "user/explanation";}
    //  jin
    @GetMapping("notice")
    public String notice(){return "notice/notice";}
    //  dong
    @GetMapping("footer")
    public String footer(){return "fixed/footer";}

    //  young
    @GetMapping("guide")
    public String guide(){return "guide";}
}
