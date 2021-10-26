package com.example.test.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MapServiceTest {
    @Autowired
    private MapService service;

    @Test
    public void getSearchListTest(){
        log.info("------------------------------");
        service.getSearchList("한림대").forEach(
                list->{
                    log.info(list.getDocHospitalName());
                    log.info(list.getDocAddress());
                }
        );
        log.info("------------------------------");
    }
}
