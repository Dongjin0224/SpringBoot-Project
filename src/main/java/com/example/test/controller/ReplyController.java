package com.example.test.controller;


import com.example.test.model.beans.vo.Criteria;
import com.example.test.model.vo.ReplyPageDTO;
import com.example.test.model.vo.ReplyVO;
import com.example.test.services.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/replies/*")
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping(value = "/new", consumes = "application/json", produces = "text/plain; charset=utf-8")
    public ResponseEntity<String> create(@RequestBody ReplyVO replyVO) throws UnsupportedEncodingException {

        int replyCount = replyService.register(replyVO);
        log.info("ReplyVO : " + replyVO);
        log.info("REPLY INSERT COUNT : " + replyCount);
        return replyCount == 1 ?
                new ResponseEntity<>(new String("댓글 등록 성공".getBytes(), "UTF-8"), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //    게시글 댓글 전체 조회
    @GetMapping("pages/{qnaNo}/{page}")
    public ReplyPageDTO getList(@PathVariable("qnaNo") Long qnaNo, @PathVariable("page") int page){
        log.info("getList............");
        Criteria criteria = new Criteria(page, 10);
        log.info(criteria.toString());
        return replyService.getList(qnaNo, criteria);
    }
    //    댓글 조회
    //    URI에 댓글 번호만 작성한다.
    //    전달받은 rno를 JSON으로 리턴한다.
    @GetMapping("{reQnaNo}")
    public ReplyVO get(@PathVariable("reQnaNo") Long reQnaNo){
        log.info("get............");
        return replyService.get(reQnaNo);
    }
    @RequestMapping(
            method={RequestMethod.PUT, RequestMethod.PATCH},
            value="{reQnaNo}", consumes = "application/json", produces = "text/plain; charset=UTF-8"
    )
    public ResponseEntity<String> modify(@RequestBody ReplyVO replyVO, @PathVariable("reQnaNo") Long reQnaNo) throws UnsupportedEncodingException{
        log.info("modify...............");
        replyVO.setReQnaNo(reQnaNo);
        return replyService.modify(replyVO) == 1 ?
                new ResponseEntity<>(new String("댓글 수정 성공".getBytes(), "UTF-8"), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //    댓글 삭제
    //    URI로 댓글 번호를 전달받은 후 성공 시 댓글 삭제 성공 전달

    @DeleteMapping(value="{reQnaNo}", produces = "text/plain; charset=utf-8")
    public ResponseEntity<String> remove(@PathVariable("reQnaNo") Long rno) throws UnsupportedEncodingException{
        log.info("remove.............");
        return replyService.remove(rno) == 1 ?
                new ResponseEntity<>(new String("댓글 삭제 성공".getBytes(), "UTF-8"), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
