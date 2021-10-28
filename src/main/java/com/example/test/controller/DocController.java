package com.example.test.controller;

import com.example.test.services.DocService;

import com.example.test.user.vo.DocVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
public class DocController {

    private final DocService service;

    @GetMapping("doctorSignUp")
    public String doctorSignUp(){return "user/doctorSignUp";}

    @PostMapping("doctorSignUp")
    public String doctorSignUp(DocVO vo){
        log.info("---------------들어옴------------------");

        if(vo.getAttachList() != null){
            vo.getAttachList().forEach(attach -> log.info(attach.toString()));
        }

        service.DocSignUp(vo);


        return "user/login";
    }

}
