package com.example.test.services;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class ImportPayTest {

    @Autowired
    private ImportPay pay;

    @Test
    public void getToken(){
        log.info("---------------------");
        log.info("Token : " + pay.getToken());
        log.info("---------------------");
    }

}
