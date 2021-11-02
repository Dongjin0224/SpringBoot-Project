package com.example.test.controller;

import com.example.test.model.mainBoard.vo.AnswerVO;
import com.example.test.model.user.vo.DocVO;
import com.example.test.services.AnswerService;
import com.example.test.services.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping("/detail/*")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping("answer")
    public String insert(AnswerVO answerVO, HttpServletRequest request){
        HttpSession session =(HttpSession)request.getSession();
        Long docNo = (Long)session.getAttribute("docNo");
        Long qnaNo = (Long)session.getAttribute("qnaNo");

        answerVO.setDocNo(docNo);
        answerVO.setQnaNo(qnaNo);
        answerService.insert(answerVO);
        return "mainBoard/mainBoard";
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
