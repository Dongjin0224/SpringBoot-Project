package com.example.test.controller;

import com.example.test.model.mainBoard.vo.AnswerVO;
import com.example.test.model.user.vo.DocVO;
import com.example.test.services.AnswerService;
import com.example.test.services.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

@Controller
@Slf4j
@RequestMapping("/detail/*")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping(value = "answer", consumes = "application/json", produces = "text/plain; charset=utf-8")
    @ResponseBody
    public String insert(@RequestBody String content1, AnswerVO answerVO, HttpServletRequest request, Model model){
        HttpSession session =(HttpSession)request.getSession();
        Long docNo = (Long)session.getAttribute("docNo");
        Long qnaNo = (Long)session.getAttribute("qnaNo");


        answerVO.setDocNo(docNo);
        answerVO.setReQnaContent(content1);
        answerVO.setQnaNo(qnaNo);

        log.info(answerVO.toString());
        answerService.insert(answerVO);
//        model.addAttribute("qnaNo",qnaNo);
        return "등록완료";
    }

  /*  @GetMapping("detail")
    public String answerList(HttpServletRequest request, Model model){
        HttpSession session = (HttpSession)request.getSession();
        Long qnaNo =(Long)session.getAttribute("qnaNo");
        log.info("-------------qnaNO-----------------------"+qnaNo);
        model.addAttribute("answerList",answerService.answerList(qnaNo));


        return "mainBoard/mainBoard";
    }
*/

}
