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
    @GetMapping("headerLogin")
    public String headerLogin(){return "fixed/headerLogin";}
    //  vanny
    @GetMapping("terms")
    public String term(){return "term/terms";}
    //  vanny
    @GetMapping("privacyPolicy")
    public String privacy_policy(){return "term/privacyPolicy";}
    //  vanny
    @GetMapping("locationPolicy")
    public String location_policy(){return "term/locationPolicy";}
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
    //  jin
    @GetMapping("login")
    public String login(){return "user/login";}
    /*//  jin
    @GetMapping("memberSignUp")
    public String memberSignUp(){return "user/memberSignUp";}*/
    //  jin
    @GetMapping("doctorSignUp")
    public String doctorSignUp(){return "user/doctorSignUp";}
    //  jin
    @GetMapping("explanation")
    public String explanation(){return "user/explanation";}
    //  dong
    @GetMapping("footer")
    public String footer(){return "fixed/footer";}

    //  young
    @GetMapping("guide")
    public String guide(){return "guide";}

    // index test
    @GetMapping("index3")
    public String index3(){return "index3";}

    // index test
    @GetMapping("admin")
    public String admin(){return "admin/adminHome";}
}
