package com.example.test.services;


import com.example.test.controller.PayController;
import com.example.test.model.payment.dao.PayDAO;
import com.example.test.model.payment.vo.GetTokenVO;
import com.example.test.model.payment.vo.PayVO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class PayServiceImple implements PayService{

    private final PayDAO payDAO;

    public String getToken() {

        RestTemplate restTemplate = new RestTemplate();

        //서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> map = new HashMap<>();
        map.put("imp_key", "7681399020268514");
        map.put("imp_secret", "fcff66b2ffa2c27be20812e9e502f68d88d23098829f2601a77253d946b8731a40171253b96b5714");

        Gson var = new Gson();
        String json = var.toJson(map);
        //서버로 요청할 Body

        HttpEntity<String> entity = new HttpEntity<>(json,headers);
        return restTemplate.postForObject("https://api.iamport.kr/users/getToken", entity, String.class);
    }

    public String getCustomer(PayVO payVO) throws UnsupportedEncodingException {
        String token = getToken();
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

        log.info("getCustomer............imple");
        log.info(payVO.getCard_number());

        Map<String, Object> map = new HashMap<>();
        payVO.setCustomer_uid("queu_" + payVO.getCard_number().split("-")[3]);
        map.put("card_number", payVO.getCard_number());
        map.put("expiry", payVO.getExpiry());
        map.put("birth", payVO.getBirth());
        map.put("pwd_2digit", payVO.getPwd_2digit());
//        map.put("amount", payVO.getAmount());

        Gson var = new Gson();
        String json = var.toJson(map);
        log.info("---------------------");
        System.out.println("map : " + json);
        log.info("---------------------");
        System.out.println("payVO : " + payVO);
        log.info("---------------------");

        HttpEntity<String> entity = new HttpEntity<>(json, headers);

        payDAO.updateCustomer(payVO);
        return restTemplate.postForObject("https://api.iamport.kr/subscribe/customers/" + payVO.getCustomer_uid(), entity, String.class);
    }

    public String unSchedule(PayVO payVO){

        Long docNo = payVO.getDocNo();

        String token = getToken();
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
        map.put("customer_uid", getPayList(docNo).getCustomer_uid());

        Gson var = new Gson();
        String json = var.toJson(map);
        System.out.println(json);

        HttpEntity<String> entity = new HttpEntity<>(json, headers);

        log.info("---------------------");
        log.info("예약 취소");
        log.info("---------------------");
        return restTemplate.postForObject("https://api.iamport.kr/subscribe/payments/unschedule", entity, String.class);
    }

    public String schedulePay(Long docNo) {
        String token = getToken();

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
        jsonObject.addProperty("name", getPayList(docNo).getName());
        jsonObject.addProperty("amount", getPayList(docNo).getAmount());

        JsonArray jsonArr = new JsonArray();

        jsonArr.add(jsonObject); JsonObject reqJson = new JsonObject();

        reqJson.addProperty("customer_uid", getPayList(docNo).getCustomer_uid());
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

    public void insertCustomer(Long docNo){
        log.info("insert payment.........");
        payDAO.insertCustomer(docNo);
    }

//    public void updateCustomer(PayVO payVO){
//        log.info("update customer_uid.........");
//        payDAO.updateCustomer(payVO);
//    }

    //상품 명과 구매날짜가 업데이트 되는 메소드
    public void pay(PayVO payVO){
        log.info("pay................");
        payDAO.pay(payVO);
    }

    //결제 정보를 가져오는 메소드(하나만)
    public PayVO getPayList(Long docNo){
        log.info("payList................");
        return payDAO.getPayList(docNo);
    }

}
