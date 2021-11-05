package com.example.test.controller;


import com.example.test.model.payment.vo.PayVO;
import com.example.test.model.payment.vo.GetTokenVO;
import com.example.test.model.user.vo.DocVO;
import com.example.test.services.PayService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/payment/*")
public class PayController {


    private final PayService pay;
    static int code = 0;

    @PostMapping("/insertCustomer")
    public void insertCustomer(@RequestBody PayVO payVO, HttpServletRequest request) throws UnsupportedEncodingException {
        HttpSession session = (HttpSession)request.getSession();
        Long docNo = (Long) session.getAttribute("docNo");

        payVO.setDocNo(docNo);


        log.info("insertCustomer...........");
        log.info("docNo : " + docNo);
        pay.getCustomer(payVO);
    }

//    @ResponseBody
    @PostMapping("/cardCheck")
    public String cardCheck(@RequestBody PayVO payVO, HttpServletRequest request) throws UnsupportedEncodingException {
        HttpSession session = (HttpSession)request.getSession();
        Long docNo = (Long) session.getAttribute("docNo");

        payVO.setDocNo(docNo);

        int result = Integer.parseInt(pay.getCustomer(payVO).split(":")[1].split(",")[0]);
        log.info("-----------------------------------");
        System.out.println(payVO);
        System.out.println("code : " + pay.getCustomer(payVO).split(":")[1].split(",")[0]);
        log.info("-----------------------------------");
        if(result == 0){
            log.info("카드 등록 성공");
            return "success";
        }else{
            log.info("카드 등록 실패");
            return "fail";
        }
    }

//    @ResponseBody
    @PostMapping("/updateCard")
    public String updateCard(@RequestBody PayVO payVO, HttpServletRequest request) throws UnsupportedEncodingException {
        HttpSession session = (HttpSession)request.getSession();
        Long docNo = (Long) session.getAttribute("docNo");

        payVO.setDocNo(docNo);

        if(cardCheck(payVO, request) == "success"){
            log.info("카드 수정 성공");
            cardCheck(payVO, request);
        }else if(cardCheck(payVO, request) == "fail"){
            log.info("카드 수정 실패");
            return "fail";
        }
        payVO = pay.getPayList(docNo);
        payVO.setCard_number(payVO.getCard_number());
        payVO.setExpiry(payVO.getExpiry());
        payVO.setBirth(payVO.getBirth());
        payVO.setPwd_2digit(payVO.getPwd_2digit());
        payVO.setCustomer_uid("");

        pay.pay(payVO);

        pay.getCustomer(payVO);

        return "success";
    }


//    @ResponseBody
    @PostMapping("/startPay")
    public void startPay(@RequestBody PayVO payVO, HttpServletRequest request){
        PayVO vo = new PayVO();
        HttpSession session = (HttpSession)request.getSession();
        Long docNo = (Long) session.getAttribute("docNo");

        vo = pay.getPayList(docNo);
        vo.setAmount(payVO.getAmount());
        vo.setPayStatus(payVO.getPayStatus());
        vo.setName(payVO.getName());
        vo.setDocNo(docNo);

        System.out.println(vo);
        if(payVO.getName() == null){
            return;
        }


        pay.pay(vo);
        log.info("여기까진 들어오니???");

        code = 0;
        while(true){
            if(code == 1){
                break;
            }
            pay.schedulePay(docNo);
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

//    @ResponseBody
    @PostMapping("/stopPay")
    public int stopSchedulePay(HttpServletRequest request){
        PayVO payVO = new PayVO();
        HttpSession session = (HttpSession)request.getSession();
        Long docNo = (Long) session.getAttribute("docNo");

        payVO = pay.getPayList(docNo);

        if(payVO.getPayStatus() == 0){
            return 1;
        }

        payVO = pay.getPayList(docNo);
        payVO.setDocNo(docNo);
        payVO.setPayStatus(0);
        payVO.setName("");
        payVO.setAmount(0);

        pay.unSchedule(payVO);
        pay.pay(payVO);
        System.out.println(payVO);
        code = 1;
        return 0;
    }

    @PostMapping("registCheck")
    public int registCheck(HttpServletRequest request){
        HttpSession session = (HttpSession)request.getSession();
        Long docNo = (Long) session.getAttribute("docNo");

        if(pay.getPayList(docNo) == null){
            return 1;
        }

        return 0;
    }
}
