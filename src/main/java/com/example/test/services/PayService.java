package com.example.test.services;

import com.example.test.model.payment.dao.PayDAO;
import com.example.test.model.payment.vo.PayVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Service
public interface PayService {


    public String getToken();

    public String getCustomer(PayVO payVO) throws UnsupportedEncodingException;

    public void insertCustomer(Long docNo);

//    public void updateCustomer(PayVO payVO);

    public void pay(PayVO payVO);

    public PayVO getPayList(Long docNo);

    public String unSchedule(PayVO payVO);
}
