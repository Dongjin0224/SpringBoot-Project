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

    @ResponseBody
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

    @ResponseBody
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

//    @PostMapping("/schedule")
//    public String schedulePay(Long docNo) {
//        String token = pay.getToken();
//
//        long timestamp = 0;
//        Calendar cal = Calendar.getInstance();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.KOREA);
//        cal.add(Calendar.MINUTE, +1);
//        String date = sdf.format(cal.getTime());
//
//        try {
//            Date stp = sdf.parse(date);
//            timestamp = stp.getTime()/1000;
//        } catch (ParseException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        Gson str = new Gson();
//        token = token.substring(token.indexOf("response") +10);
//        token = token.substring(0, token.length() - 1);
//        GetTokenVO vo = str.fromJson(token, GetTokenVO.class);
//        String access_token = vo.getAccess_token();
//
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(access_token);
//
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("merchant_uid", "merchant_" + timestamp);
//        jsonObject.addProperty("schedule_at", timestamp);
//        jsonObject.addProperty("name", pay.getPayList(docNo).getName());
//        jsonObject.addProperty("amount", pay.getPayList(docNo).getAmount());
//
//        JsonArray jsonArr = new JsonArray();
//
//        jsonArr.add(jsonObject); JsonObject reqJson = new JsonObject();
//
//        reqJson.addProperty("customer_uid", pay.getPayList(docNo).getCustomer_uid());
//        reqJson.add("schedules",jsonArr);
//        String json = str.toJson(reqJson);
//        System.out.println(json);
//        HttpEntity<String> entity = new HttpEntity<>(json, headers);
//
//        int code = Integer.parseInt(restTemplate.postForObject("https://api.iamport.kr/subscribe/payments/schedule", entity, String.class).split(",")[0].split(":")[1]);
//
//        log.info("---------------------");
//        log.info("예약 성공");
//        log.info("---------------------");
//        return restTemplate.postForObject("https://api.iamport.kr/subscribe/payments/schedule", entity, String.class);
//    }

    @ResponseBody
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

    @PostMapping("/stopPay")
    @ResponseBody
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
    public int test(HttpServletRequest request){
        HttpSession session = (HttpSession)request.getSession();
        Long docNo = (Long) session.getAttribute("docNo");

        if(pay.getPayList(docNo) == null){
            return 1;
        }

        return 0;
    }
}
