package com.example.test.controller;

import com.example.test.model.beans.vo.Criteria;
import com.example.test.model.beans.vo.PageDTO;
import com.example.test.model.user.vo.DocVO;
import com.example.test.model.user.vo.UserVO;
import com.example.test.model.volunteer.vo.ApplicantsVO;
import com.example.test.services.MyPageService;
import com.example.test.services.VolunteerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/myPage/*")
@RequiredArgsConstructor
public class MyPageController {

    public final MyPageService myPageService;
    public final VolunteerService volunteerService;

    @GetMapping("myPageUser")
    public String myPageUser(Model model, HttpServletRequest request) {
        HttpSession session = (HttpSession)request.getSession();
        UserVO user = (UserVO) session.getAttribute("user");
        Long userNo = user.getUserNo();
        model.addAttribute("user", myPageService.viewUser(userNo));
        model.addAttribute("getResList", myPageService.getResList(userNo));
        return "myPage/myPageUser";
    }


    @PostMapping("updateUser")
    @ResponseBody
    public String updateUser(@RequestBody UserVO userVO, HttpServletRequest request){

        HttpSession session = (HttpSession)request.getSession();
        Long userNo = (Long) session.getAttribute("userNo");

        userVO.setUserNo(Long.parseLong(String.valueOf(userNo)));

        log.info("-------------------------------");
        log.info("userNo : " + String.valueOf(userVO.getUserNo()));
        log.info("-------------------------------");

        log.info("---------------------------");
        System.out.println("userVO : " + userVO);
        log.info("---------------------------");

        myPageService.updateUser(userVO);

        return "수정완료";
    }


    @GetMapping("myPageDoc")
    public String myPageDoc(Model model, HttpServletRequest request, Criteria criteria){
        HttpSession session = (HttpSession)request.getSession();
        Long docNo = (Long) session.getAttribute("docNo");

        model.addAttribute("doc", myPageService.viewDoc(docNo));
        model.addAttribute("getVolList", myPageService.getVolList(docNo));
        model.addAttribute("getAppList", myPageService.getAppList(docNo));
        /*model.addAttribute("pageMaker", new PageDTO(volunteerService.getTotal(criteria), 10, criteria));*/
        log.info("------------------------------------");
        log.info("docNo" + String.valueOf(docNo));
        log.info("------------------------------------");

        return "myPage/myPageDoc";
    }

    @GetMapping("applicationForm")
    public String applicationForm(@RequestParam("applicantsNo") Long applicantsNo, Model model, HttpServletRequest request){
        HttpSession session = (HttpSession)request.getSession();
        Long docNo = (Long) session.getAttribute("docNo");

        model.addAttribute("getAppContent", myPageService.getAppContent(docNo));
        /*model.addAttribute("getAppContent", myPageService.getAppContent(applicantsNo));*/
        return "myPage/applicationForm";
    }

    /*@GetMapping("volunteerContent")
    public String volunteerContent(@RequestParam("volunteerBoardNo") Long volunteerBoardNo, Criteria criteria, Model model){
        model.addAttribute("content", volunteerService.get(volunteerBoardNo));
        model.addAttribute("criteria", criteria);
        return "volunteer/volunteerContent";}*/



    @PostMapping("updateDoc")
    @ResponseBody
    public String updateDoc(@RequestBody DocVO docVO, HttpServletRequest request){

        HttpSession session = (HttpSession)request.getSession();
        Long docNo = (Long) session.getAttribute("docNo");

        docVO.setDocNo(Long.parseLong(String.valueOf(docNo)));

        log.info("------------------------------------");
        log.info("docNo : " + String.valueOf(docVO.getDocNo()));
        log.info("------------------------------------");

        log.info("---------------------------");
        System.out.println("dovVO : " + docVO.toString());
        log.info("---------------------------");

        myPageService.updateDoc(docVO);

        return "수정완료";
    }
}
