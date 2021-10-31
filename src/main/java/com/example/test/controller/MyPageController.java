package com.example.test.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/myPage/*")
@RequiredArgsConstructor
public class MyPageController {

    @GetMapping("myPageUser")
    public String updateUserView(){
        return "myPage/myPageUser";
    }

    @PostMapping("myPageUser")
    public String updateUser(){
        return "myPage/myPageUser";
    }

    @GetMapping("myPageDoc")
    public String updateDocView(){
        return "myPage/myPageDoc";
    }

    @PostMapping("myPageDoc")
    public String updateDoc(){
        return "myPage/myPageDoc";
    }

}
