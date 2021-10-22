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
    public String write(){return "write";}
    //  vanny
    @GetMapping("header")
    public String header(){return "header";}
    //  vanny
    @GetMapping("term")
    public String term(){return "term";}
    //  vanny
    @GetMapping("privacy_policy")
    public String privacy_policy(){return "privacy_policy";}
    //  vanny
    @GetMapping("location_policy")
    public String location_policy(){return "location_policy";}
    //  vanny
    @GetMapping("volunteer_register")
    public String volunteer_register(){return "volunteer_register";}
    //  hong
    @GetMapping("detail")
    public String detail(){return "detail";}
    //  hong
    @GetMapping("map")
    public String map(){return "map";}
    //  hong
    @GetMapping("search_map")
    public String searchMap(){return "searchMap";}
    //  hong
    @GetMapping("search_docs")
    public String searchDocs(){return "searchDocs";}
    //  hong
    @GetMapping("report")
    public String report(){return "report";}
    //  dong
    @GetMapping("appointment")
    public String appointment(){return "appointment";}
    //  dong
    @GetMapping("membership")
    public String membership(){return "membership";}
    //  dong
    @GetMapping("payTerm")
    public String payTerm(){return "payTerm";}
    //  lim
    @GetMapping("myPageUser")
    public String myPageUser(){return "myPageUser";}
    //  lim
    @GetMapping("myPageDoc")
    public String myPageDoc(){return "myPageDoc";}
    //  jin
    @GetMapping("login")
    public String login(){return "login";}
    //  jin
    @GetMapping("memberSignUp")
    public String memberSignUp(){return "memberSignUp";}
    //  jin
    @GetMapping("doctorSignUp")
    public String doctorSignUp(){return "doctorSignUp";}
    //  jin
    @GetMapping("medicalService")
    public String medicalService(){return "medicalService";}
    //  jin
    @GetMapping("msRead")
    public String msRead(){return "msRead";}
    //  jin
    @GetMapping("explanation")
    public String explanation(){return "explanation";}
    //  jin
    @GetMapping("notice")
    public String notice(){return "notice";}
    //  dong
    @GetMapping("footer")
    public String footer(){return "fixed/footer";}
    //  dong
    @GetMapping("category")
    public String category(){return "category";}
    //  young
    @GetMapping("guide")
    public String guide(){return "guide";}
}
