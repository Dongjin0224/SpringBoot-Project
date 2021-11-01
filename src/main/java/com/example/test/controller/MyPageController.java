package com.example.test.controller;

import com.example.test.model.user.vo.DocVO;
import com.example.test.model.user.vo.UserVO;
import com.example.test.services.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/myPage/*")
@RequiredArgsConstructor
public class MyPageController {

    public final MyPageService myPageService;

    @GetMapping("myPageUser")
    public String myPageUser(Model model, HttpServletRequest request) {
        HttpSession session = (HttpSession)request.getSession();
        UserVO user = (UserVO) session.getAttribute("user");
        Long userNo = user.getUserNo();
        model.addAttribute("user", myPageService.viewUser(userNo));
        return "myPage/myPageUser";
    }

    /*@GetMapping("myPageUser")
    public String myPageUser(UserVO vo, Model model) {
        System.out.println("여기까지1");
        model.addAttribute("user", myPageService.viewUser(vo.getUserNo()));
        System.out.println("여기까지2");
        return "myPage/myPageUser";
    }*/


    @PostMapping("updateUser")
    public RedirectView updateUser(UserVO vo){
        log.info("-------------------------------");
        log.info("modify : " + vo.toString());
        log.info("-------------------------------");

        myPageService.updateUser(vo);

        return new RedirectView("redirect:/");
    }


    @GetMapping("myPageDoc")
    public String myPageDoc(Model model, HttpServletRequest request){
        HttpSession session = (HttpSession)request.getSession();
        DocVO doc = (DocVO) session.getAttribute("doc");
        Long docNo = doc.getDocNo();
        model.addAttribute("doc", myPageService.viewDoc(docNo));
        return "myPage/myPageDoc";
    }


    @PostMapping("updateDoc")
    public String updateDoc(DocVO vo, HttpSession session){
        log.info("-------------------------------");
        log.info("modify : " + vo.toString());
        log.info("-------------------------------");

        myPageService.updateDoc(vo);

        session.invalidate();

        return "redirect:";
    }
}
