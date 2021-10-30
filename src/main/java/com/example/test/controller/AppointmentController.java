package com.example.test.controller;

import com.example.test.model.appointment.vo.ReserveDocVO;
import com.example.test.model.appointment.vo.ReserveUserVO;
import com.example.test.model.appointment.vo.ReserveVO;
import com.example.test.model.beans.vo.Criteria;
import com.example.test.services.AppointmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequestMapping("/appointment/*")
@RequiredArgsConstructor
public class AppointmentController {

    public final AppointmentService appointmentService;

    @GetMapping("appointment")
    public void read(@RequestParam("docNo") Long docNo, Criteria criteria, Model model, HttpServletRequest request){
        /*
        String reqURI = request.getRequestURI();
        String reqType = reqURI.substring(reqURI.indexOf(request.getContextPath()) + 7);
        */

        model.addAttribute("appointment", appointmentService.get(docNo));
        model.addAttribute("file", appointmentService.getFile(docNo));
        /*  model.addAttribute("criteria", criteria);*/
    }

    @GetMapping("reserve")
    public void reserve(@RequestParam("docNo") Long docNo, ReserveVO reserveVO){

        appointmentService.reserve(reserveVO);

        ReserveUserVO user = appointmentService.getUserPhone(reserveVO.getUserNo());
        ReserveDocVO doc = appointmentService.getDocPhone(reserveVO.getDocNo());
        String userName = user.getUserName();
        String docName = doc.getDocName();
        String userPhoneNum = user.getUserPhoneNum();
        String docPhoneNum = doc.getDocPhoneNum();

        log.info("------------------------");
        log.info(user.getUserName());
        log.info(user.getUserPhoneNum());
        log.info("------------------------");
        log.info(doc.getDocName());
        log.info(doc.getDocPhoneNum());
        log.info("------------------------");

        String[] name = {userName, docName};
        String[] phoneNum = {userPhoneNum, docPhoneNum};

    }


}
