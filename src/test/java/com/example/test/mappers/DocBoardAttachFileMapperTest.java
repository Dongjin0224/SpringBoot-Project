package com.example.test.mappers;


import com.example.test.model.user.vo.DocAttachFileVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class DocBoardAttachFileMapperTest {

    @Autowired
    DocAttachFileMapper docAttachFileMapper;

    @Test
    public void insertTest(){
        DocAttachFileVO docAttachFileVO = new DocAttachFileVO();
        docAttachFileVO.setDocNo(6L);
        docAttachFileVO.setFileName("jjjj");
        docAttachFileVO.setImage(false);
        docAttachFileVO.setUploadPath("C:/upload");
        docAttachFileVO.setUuid("j_j_hJ_");
        log.info("-----------------");
        docAttachFileMapper.insert(docAttachFileVO);
        log.info("-----------------");
    }
}
