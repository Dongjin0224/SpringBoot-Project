package com.example.test.controller;


import com.example.test.payment.vo.PayVO;
import com.example.test.payment.vo.GetTokenVO;
import com.example.test.services.ImportPay;
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

    private final ImportPay pay;
    static int code = 0;

    public String getToken(PayVO payVO, int price) throws UnsupportedEncodingException{

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
        map.put("customer_uid", payVO.getCustomer_uid());
        map.put("merchant_uid", "merchant_" + new Date().getTime());
        map.put("amount", price);
        map.put("name", payVO.getName());
        map.put("card_number", payVO.getCard_number());
        map.put("expiry", payVO.getExpiry());
        map.put("birth", payVO.getBirth());
        map.put("pwd_2digit", payVO.getPwd_2digit());

        Gson var = new Gson();
        String json = var.toJson(map);
        System.out.println(json);

        HttpEntity<String> entity = new HttpEntity<>(json, headers);

        return restTemplate.postForObject("https://api.iamport.kr/subscribe/payments/onetime", entity, String.class);
    }


    public String schedulePay(PayVO payVO, int price) {
        String token = pay.getToken();
        long timestamp = 0;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.KOREA);
        cal.add(Calendar.MINUTE, +1);
        String date = sdf.format(cal.getTime());
        try {
            Date stp = sdf.parse(date);
            timestamp = stp.getTime()/1000;
            System.out.println(timestamp);
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
        jsonObject.addProperty("merchant_uid", timestamp);
        jsonObject.addProperty("schedule_at", timestamp);
        jsonObject.addProperty("amount", price);

        JsonArray jsonArr = new JsonArray();

        jsonArr.add(jsonObject); JsonObject reqJson = new JsonObject();

        reqJson.addProperty("customer_uid", payVO.getCustomer_uid());
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
        map.put("customer_uid", payVO.getCustomer_uid());

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
    public void a(PayVO payVO,@RequestParam int price) throws UnsupportedEncodingException{
        code = 0;
        getToken(payVO, price);
        while(true){
            if(code == 1){
                break;
            }
            schedulePay(payVO, price);
//            int code = Integer.parseInt(entity.split(",")[0].split(":")[1]);
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @PostMapping("/stopPay")
    public void stopSchedulePay(PayVO payVO){
        int i =0;
        unSchedule(payVO);
        code = 1;
    }
}
