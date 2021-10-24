package com.example.test.controller;

import com.example.test.beans.vo.Criteria;
import com.example.test.services.AppointmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        /*  model.addAttribute("criteria", criteria);*/
    }
}
