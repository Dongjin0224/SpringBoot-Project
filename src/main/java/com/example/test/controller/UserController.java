package com.example.test.controller;

import com.example.test.services.MapService;
import com.example.test.services.UserService;
import com.example.test.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
