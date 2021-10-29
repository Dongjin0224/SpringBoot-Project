package com.example.test.controller;

import com.example.test.beans.vo.Criteria;
import com.example.test.beans.vo.PageDTO;
import com.example.test.services.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequestMapping("/notice/*")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    //  jin
    @GetMapping("noticeBoard")
    public String noticeBoard(Criteria criteria, Model model){
        log.info("-------------------------------");
        log.info("list");
        log.info("-------------------------------");
        model.addAttribute("list", noticeService.getList(criteria));
        model.addAttribute("pageMaker", new PageDTO(noticeService.getTotal(criteria), 10, criteria));
        return "notice/noticeBoard";
    }

    @GetMapping("noticeContent")
    public String noticeContent(@RequestParam("noticeNo") Long noticeNo, Criteria criteria, Model model){
        model.addAttribute("content", noticeService.get(noticeNo));
        model.addAttribute("view", noticeService.updateViews(noticeNo));
        model.addAttribute("criteria", criteria);
        return "notice/noticeContent";}
}
