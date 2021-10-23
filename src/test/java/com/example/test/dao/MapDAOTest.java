package com.example.test.dao;

import com.example.test.user.dao.MapDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MapDAOTest {
    @Autowired
    private MapDAO mapDAO;

    @Test
    public void searchListTest() {
        log.info("----------------------------");
        mapDAO.getSearchList("아주대").forEach(search -> {
            log.info(search.getDocHospitalName());
            log.info(search.getDocAddress());
        });
        log.info("----------------------------");
    }

}
