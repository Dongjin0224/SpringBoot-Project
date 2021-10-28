package com.example.test.controller;

import com.example.test.services.UserService;
import com.example.test.model.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
public class UserController {

     private final UserService service;

    @GetMapping("login")
    public String memberSignUp(){return "user/login";}

    @PostMapping("login")
    public String memberSignUp(UserVO vo){
        log.info("---------------들어옴------------------");
        service.memberSignUp(vo);
        return "user/login";
    }

}
