package com.example.test.controller;


import com.example.test.model.payment.vo.PayVO;
import com.example.test.model.payment.vo.GetTokenVO;
import com.example.test.services.PayService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
    public void insertCustomer(@RequestBody PayVO payVO) throws UnsupportedEncodingException {
        Long docNo = payVO.getDocNo();
        log.info("insertCustomer...........");
        log.info("docNo : " + docNo);
        pay.insertCustomer(docNo);
        pay.getCustomer(payVO);
    }

    @ResponseBody
    @PostMapping("/cardCheck")
    public String cardCheck(@RequestBody PayVO payVO) throws UnsupportedEncodingException {
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
    public String updateCard(@RequestBody PayVO payVO) throws UnsupportedEncodingException {
        pay.unSchedule(payVO);
        if(cardCheck(payVO) == "success"){
            log.info("카드 수정 성공");
            cardCheck(payVO);
        }else if(cardCheck(payVO) == "fail"){
            log.info("카드 수정 실패");
            code = 1;
            return "fail";
        }
        pay.getCustomer(payVO);

        code = 0;
        while(true){
            if(code == 1){
                break;
            }
            schedulePay(payVO.getDocNo());
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "success";
    }

    @PostMapping("/schedule")
    public String schedulePay(Long docNo) {
        String token = pay.getToken();

        long timestamp = 0;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.KOREA);
        cal.add(Calendar.MINUTE, +1);
        String date = sdf.format(cal.getTime());

        try {
            Date stp = sdf.parse(date);
            timestamp = stp.getTime()/1000;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Gson str = new Gson();
        token = token.substring(token.indexOf("response") +10);
        token = token.substring(0, token.length() - 1);
        GetTokenVO vo = str.fromJson(token, GetTokenVO.class);
        String access_token = vo.getAccess_token();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(access_token);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("merchant_uid", "merchant_" + timestamp);
        jsonObject.addProperty("schedule_at", timestamp);
        jsonObject.addProperty("name", pay.getPayList(docNo).getName());
        jsonObject.addProperty("amount", pay.getPayList(docNo).getAmount());

        JsonArray jsonArr = new JsonArray();

        jsonArr.add(jsonObject); JsonObject reqJson = new JsonObject();

        reqJson.addProperty("customer_uid", pay.getPayList(docNo).getCustomer_uid());
        reqJson.add("schedules",jsonArr);
        String json = str.toJson(reqJson);
        System.out.println(json);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);

        int code = Integer.parseInt(restTemplate.postForObject("https://api.iamport.kr/subscribe/payments/schedule", entity, String.class).split(",")[0].split(":")[1]);

        log.info("---------------------");
        log.info("예약 성공");
        log.info("---------------------");
        return restTemplate.postForObject("https://api.iamport.kr/subscribe/payments/schedule", entity, String.class);
    }

    @PostMapping("/startPay")
    public void startPay(@RequestBody PayVO payVO){
        pay.pay(payVO);
        code = 0;
        while(true){
            if(code == 1){
                break;
            }
            schedulePay(payVO.getDocNo());
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @PostMapping("/stopPay")
    public void stopSchedulePay(@RequestBody PayVO payVO){
        pay.unSchedule(payVO);
        code = 1;
    }


}
