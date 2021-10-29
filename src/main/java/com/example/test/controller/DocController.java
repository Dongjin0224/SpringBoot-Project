package com.example.test.controller;

import com.example.test.model.user.vo.UserVO;
import com.example.test.services.DocService;

import com.example.test.model.user.vo.DocVO;

import com.example.test.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
        log.info("---------------DocController...들어옴------------------");

        log.info("-----------------------------------------");
        log.info(String.valueOf(vo.getAttachList().size()));
        log.info(String.valueOf(vo.getHosattachList().size()));
        log.info("-----------------------------------------");
       if(vo.getAttachList() != null){
            vo.getAttachList().forEach(attach -> log.info(attach.toString()));
        }
        if(vo.getHosattachList() != null){
            vo.getHosattachList().forEach(hosattach -> log.info(hosattach.toString()));
        }

        int result =service.checkId(vo);
        if(result == 1) {
            return "user/doctorSignUp";
        }else if(result == 0) {
            service.DocSignUp(vo);
        }
        return "user/login";
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
        }
        return new RedirectView("/index");
    }


    @PostMapping("checkDocId")
    @ResponseBody
    public int checkId(DocVO vo){
        /*log.info("-----------------------------------");
        log.info("들어옴");
        log.info(docId);
        log.info(String.valueOf(service.checkId(docId)));
        log.info("-----------------------------------");*/

        int result= service.checkId(vo);
        return result;
        }


}
