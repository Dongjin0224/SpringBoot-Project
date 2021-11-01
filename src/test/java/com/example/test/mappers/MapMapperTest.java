package com.example.test.mappers;

import com.example.test.model.user.vo.DocVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
@Slf4j
public class MapMapperTest {

    @Autowired
    private MapMapper mapMapper;

    @Test
    public void getListTest(){
        ArrayList<DocVO> vo = (ArrayList<DocVO>) mapMapper.getSearchList("아주대");
        mapMapper.getSearchList("아주대").forEach(search->{
            log.info("----------------------------------------------");
            log.info("--------들어옴--------------------------------------");
            log.info(search.getDocHospitalName());
            log.info(search.getDocAddress());
            log.info("----------------------------------------------");
        });
    }
}
