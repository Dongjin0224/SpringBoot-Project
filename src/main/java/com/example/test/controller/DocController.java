package com.example.test.controller;

import com.example.test.model.user.vo.UserVO;
import com.example.test.services.DocService;

import com.example.test.model.user.vo.DocVO;

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
public class DocController {

    private final DocService service;

    @GetMapping("docLogin")
    public String docLogin(){
        return "user/docLogin";
    }

    @GetMapping("doctorSignUp")
    public String doctorSignUp(){return "user/doctorSignUp";}

    @PostMapping("doctorSignUp")
    public String doctorSignUp(DocVO vo){
        log.info("---------------들어옴------------------");

        if(vo.getAttachList() != null){
            vo.getAttachList().forEach(attach -> log.info(attach.toString()));
        }
        if(vo.getHosattachList() != null){
            vo.getHosattachList().forEach(hosattach -> log.info(hosattach.toString()));
        }


        service.DocSignUp(vo);


        return "user/docLogin";
    }

    @PostMapping("docLogin")
    public RedirectView docLogin(DocVO vo, HttpServletRequest req, RedirectAttributes rttr) {
        HttpSession session = req.getSession();
        DocVO login = service.docLogin(vo);

        if (login == null) {
            session.setAttribute("doc", null);
            /*rttr.addFlashAttribute("msg",false);*/
        } else {
            session.setAttribute("doc", login);
            session.setAttribute("docNo", login.getDocNo());
        }
        return new RedirectView("/index");
    }

    @GetMapping("docLogout")
    public String docLogout(HttpServletRequest req) {
        HttpSession session = req.getSession(false);

        if (session != null) {
            session.invalidate();
        } return "user/docLogin";
    }

}