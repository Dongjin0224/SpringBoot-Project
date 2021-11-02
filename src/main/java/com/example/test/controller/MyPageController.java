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
import java.util.HashMap;
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


    public int userNo(HttpServletRequest request){
        HttpSession session = (HttpSession)request.getSession();
        UserVO user = (UserVO) session.getAttribute("user");
        Long userNo = user.getUserNo();

        int result = Integer.parseInt(String.valueOf(userNo));

        return result;
    }


    @PostMapping("updateUser")
    @ResponseBody
    public String updateUser(@RequestBody UserVO userVO, HttpServletRequest request){

        int userNo = userNo(request);

        userVO.setUserNo(Long.parseLong(String.valueOf(userNo)));

        log.info("-------------------------------");
        log.info("userNo : " + String.valueOf(userVO.getUserNo()));
        log.info("-------------------------------");

        log.info("---------------------------");
        System.out.println("userVO : " + userVO);
        log.info("---------------------------");

        myPageService.updateUser(userVO);

        return "redirect:/";
    }


    @GetMapping("myPageDoc")
    public String myPageDoc(Model model, HttpServletRequest request){
        HttpSession session = (HttpSession)request.getSession();
        DocVO doc = (DocVO) session.getAttribute("doc");
        Long docNo = doc.getDocNo();
        model.addAttribute("doc", myPageService.viewDoc(docNo));

        log.info("------------------------------------");
        log.info("docNo" + String.valueOf(docNo));
        log.info("------------------------------------");

        return "myPage/myPageDoc";
    }

    public int docNo(HttpServletRequest request){
        HttpSession session = (HttpSession)request.getSession();
        DocVO doc = (DocVO) session.getAttribute("doc");
        Long docNo = doc.getDocNo();

        int result = Integer.parseInt(String.valueOf(docNo));

        return result;
    }

    @PostMapping("updateDoc")
    @ResponseBody
    public String updateDoc(@RequestBody DocVO docVO, HttpServletRequest request){

        int docNo = docNo(request);

        docVO.setDocNo(Long.parseLong(String.valueOf(docNo)));

        log.info("------------------------------------");
        log.info("docNo : " + String.valueOf(docVO.getDocNo()));
        log.info("------------------------------------");

        log.info("---------------------------");
        System.out.println("dovVO : " + docVO.toString());
        log.info("---------------------------");

        myPageService.updateDoc(docVO);

        return "myPage/myPageDoc";
    }
}
