package com.example.test.mappers;


import com.example.test.model.vo.BoardVO;
import com.example.test.model.beans.vo.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BoardMapperTest {

    @Autowired
    private BoardMapper mapper;

    @Test
    public void testGetList(){
        Criteria cri = new Criteria();
        log.info("-------------------------------------------------");
        mapper.getList(cri).forEach(board -> log.info(board.toString()));
        log.info("-------------------------------------------------");
    }

    @Test
    public void testInsert(){
        BoardVO board = new BoardVO();
        board.setQnaTitle("새로 작성한 글 제목");
        board.setQnaContent("새로 작성한 글 내용");
        board.setQnaAge("30대");
        mapper.insert(board);
    }

    @Test
    public void testInsertSelectKey_bno(){
        BoardVO board = new BoardVO();
        board.setQnaTitle("새로 작성한 글 제목2");
        board.setQnaContent("새로 작성한 글 내용2");
        board.setQnaAge("30대");
        mapper.insertSelectKey_bno(board);
    }

    @Test
    public void testRead(){
        log.info(mapper.read(4L).toString());
    }

    @Test
    public void testUpdate(){
        if(mapper.read(100L) == null){
            log.info("***********NO SUCH BOARD***********");
        }else{
            BoardVO board = new BoardVO();
            board.setQnaNo(100L);
            board.setQnaTitle("수정된 글 제목");
            board.setQnaContent("수정된 글 내용");
            log.info("UPDATE COUNT : " + mapper.update(board));
        }
    }

    @Test
    public void testDelete(){
        if(mapper.read(4L) == null){
            log.info("***********NO SUCH BOARD***********");
        }else{
            log.info("DELETE COUNT : " + mapper.delete(4L));
        }
    }
}
