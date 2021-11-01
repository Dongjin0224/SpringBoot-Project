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

    @PostMapping("/pay")
    public String pay(@RequestBody PayVO payVO) throws UnsupportedEncodingException{
        String token = pay.getToken();
        Gson str = new Gson();
        token = token.substring(token.indexOf("response") + 10);
        token = token.substring(0, token.length() - 1);

        GetTokenVO vo = str.fromJson(token, GetTokenVO.class);

        String access_token = vo.getAccess_token();
        log.info(access_token);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(access_token);

        Map<String, Object> map = new HashMap<>();
        map.put("card_number", payVO.getCard_number());
        map.put("merchant_uid", "merchant_" + new Date().getTime());
        map.put("customer_uid", payVO.getCustomer_uid());
        map.put("name", payVO.getName());
        map.put("expiry", payVO.getExpiry());
        map.put("birth", payVO.getBirth());
        map.put("amount", payVO.getAmount());
        map.put("pwd_2digit", payVO.getPwd_2digit());
        map.put("docNo", payVO.getDocNo());

        Gson var = new Gson();
        String json = var.toJson(map);
        System.out.println(json);

        pay.pay(payVO);

        HttpEntity<String> entity = new HttpEntity<>(json, headers);

        return restTemplate.postForObject("https://api.iamport.kr/subscribe/payments/onetime", entity, String.class);
    }

//    @PostMapping("/getCustomer")
//    public String getCustomer(@RequestBody PayVO payVO) throws UnsupportedEncodingException{
//        String token = pay.getToken();
//        Gson str = new Gson();
//        token = token.substring(token.indexOf("response") + 10);
//        token = token.substring(0, token.length() - 1);
//
//        GetTokenVO vo = str.fromJson(token, GetTokenVO.class);
//
//        String access_token = vo.getAccess_token();
//        log.info(access_token);
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(access_token);
//
//        Map<String, Object> map = new HashMap<>();
//        payVO.setCustomer_uid("queu_" + payVO.getCard_number().split("-")[3]);
//        map.put("card_number", payVO.getCard_number());
//        map.put("expiry", payVO.getExpiry());
//        map.put("birth", payVO.getBirth());
//        map.put("pwd_2digit", payVO.getPwd_2digit());
//        map.put("amount", payVO.getAmount());
//
//        Gson var = new Gson();
//        String json = var.toJson(map);
//        System.out.println(json);
//        log.info("---------------------");
//        System.out.println(payVO);
//        log.info("---------------------");
//
//
//        HttpEntity<String> entity = new HttpEntity<>(json, headers);
//
//        pay.updateCustomer(payVO);
//        return restTemplate.postForObject("https://api.iamport.kr/subscribe/customers/" + payVO.getCustomer_uid(), entity, String.class);
//    }

    @ResponseBody
    @PostMapping("customerTest")
    public String test(@RequestBody PayVO payVO) throws UnsupportedEncodingException {
        int result = Integer.parseInt(pay.getCustomer(payVO).split(":")[1].split(",")[0]);
        log.info("-----------------------------------");
        System.out.println(payVO);
        System.out.println("code : " + pay.getCustomer(payVO).split(":")[1].split(",")[0]);
        log.info("-----------------------------------");
        if(result == 0){
            log.info("customer 등록 성공");
            return "success";
        }else{
            log.info("customer 등록 실패");
            return "fail";
        }
    }

    @PostMapping("updateCard")
    public String updateCard(PayVO payVO) throws UnsupportedEncodingException {
        unSchedule(payVO);
        pay.getCustomer(payVO);


        return "";
    }

    @PostMapping("/schedule")
    public String schedulePay(@RequestBody PayVO payVO) {
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
        jsonObject.addProperty("name", payVO.getName());
        jsonObject.addProperty("amount", pay.getPayList(1L).getAmount());

        JsonArray jsonArr = new JsonArray();

        jsonArr.add(jsonObject); JsonObject reqJson = new JsonObject();

        log.info("----------------------------------------");
        System.out.println(payVO);
        log.info("----------------------------------------");

        reqJson.addProperty("customer_uid", pay.getPayList(1L).getCustomer_uid());
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


    public String unSchedule(PayVO payVO){
        String token = pay.getToken();
        Gson str = new Gson();
        token = token.substring(token.indexOf("response") + 10);
        token = token.substring(0, token.length() - 1);

        GetTokenVO vo = str.fromJson(token, GetTokenVO.class);

        String access_token = vo.getAccess_token();

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(access_token);


        Map<String, Object> map = new HashMap<>();
        map.put("customer_uid", pay.getPayList(1L).getCustomer_uid());

        Gson var = new Gson();
        String json = var.toJson(map);
        System.out.println(json);

        HttpEntity<String> entity = new HttpEntity<>(json, headers);

        log.info("---------------------");
        log.info("예약 취소");
        log.info("---------------------");
        return restTemplate.postForObject("https://api.iamport.kr/subscribe/payments/unschedule", entity, String.class);
    }

    @PostMapping("/startPay")
    public void startPay(PayVO payVO) throws UnsupportedEncodingException{
        code = 0;
        while(true){
            if(code == 1){
                break;
            }
            schedulePay(payVO);
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @PostMapping("/stopPay")
    public void stopSchedulePay(PayVO payVO){
        unSchedule(payVO);
        code = 1;
    }


}
