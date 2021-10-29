package com.example.test.controller;

import com.example.test.services.UserService;
import com.example.test.model.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    /*@PostMapping("userLogin")
    public RedirectView userLogin(UserVO vo, HttpServletRequest req, RedirectAttributes rttr) {
        HttpSession session = req.getSession();
        UserVO login = service.userLogin(vo);

        if (login == null) {
            session.setAttribute("user", null);
            *//*rttr.addFlashAttribute("msg",false);*//*
        } else {
            session.setAttribute("user", login);
        }
        return new RedirectView("/index");
    }*/


    @GetMapping("userLogout")
    public RedirectView userLogout(HttpServletRequest req) {
        HttpSession session = req.getSession(false);

        if (session != null) {
            session.invalidate();
        } return new RedirectView("redirect:/");
    }

}
