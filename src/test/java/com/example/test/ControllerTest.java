package com.example.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ControllerTest {

    @Autowired
    private Map web;

    @Test
    public void testMap(){

        String location = "경기도 성남시 분당구";
        Float[] coords = web.geoCoding(location);

        log.info(location + ": " + coords[0] + ", " + coords[1]);
    }

}
