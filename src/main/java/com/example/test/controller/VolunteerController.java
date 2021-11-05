package com.example.test.controller;

import com.example.test.model.beans.vo.Criteria;
import com.example.test.model.beans.vo.PageDTO;
import com.example.test.services.VolunteerService;
import com.example.test.model.volunteer.vo.ApplicantsVO;
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
@RequestMapping("/volunteer/*")
@RequiredArgsConstructor
public class VolunteerController {

    private final VolunteerService volunteerService;

    //  vanny
    @GetMapping("volunteerForm")
    public String volunteerForm(@RequestParam("boardNo") Long bno, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        model.addAttribute("bno",bno);
        /* 로그인 후에 신청 가능 하기 위해 넘김*/
        Long checkDoc = (Long) session.getAttribute("docNo");
        model.addAttribute("checkDoc", checkDoc);

        int checkCnt = volunteerService.checkCnt(checkDoc, bno);
        model.addAttribute("checkCnt", checkCnt);


        log.info("-------------------------------------------------");
        log.info(bno.toString());
        log.info(volunteerService.getTitle(bno));
        log.info("-------------------------------------------------");
        model.addAttribute("title",volunteerService.getTitle(bno));
        model.addAttribute("vo",volunteerService.get(bno));
        return "volunteer/volunteerForm";
    }

    //  jin
    @GetMapping("volunteerBoard")
    public String volunteerBoard(Criteria criteria, Model model) {
        log.info("-------------------------------");
        log.info("list");
        log.info("-------------------------------");
        model.addAttribute("list", volunteerService.getList(criteria));
        model.addAttribute("pageMaker", new PageDTO(volunteerService.getTotal(criteria), 10, criteria));
        return "volunteer/volunteerBoard";
    }

    @PostMapping("volunteerBoard")
    public String insert(Criteria criteria,ApplicantsVO applicantsVO, Model model, HttpServletRequest request){
        HttpSession session = (HttpSession)request.getSession();
        applicantsVO.setDocNo(Long.parseLong(session.getAttribute("docNo").toString()));

        log.info("-----------------------------------------------------");
        log.info(applicantsVO.toString());
        log.info("-----------------------------------------------------");

        volunteerService.insert(applicantsVO);
        volunteerService.update(applicantsVO.getVolunteerBoardNo());
        model.addAttribute("list", volunteerService.getList(criteria));
        model.addAttribute("pageMaker", new PageDTO(volunteerService.getTotal(criteria), 10, criteria));
        return "volunteer/volunteerBoard";
    }

    //  jin
    @GetMapping("volunteerContent")
    public String volunteerContent(@RequestParam("volunteerBoardNo") Long volunteerBoardNo, Criteria criteria, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();

        /* 일반 회원 신청 불가 */
        Long checkUser = (Long) session.getAttribute("userNo");
        model.addAttribute("checkUser", checkUser);
        /* 로그인 후에 신청 가능 하기 위해 넘김*/
        Long checkDoc = (Long) session.getAttribute("docNo");
        model.addAttribute("checkDoc", checkDoc);

        model.addAttribute("file",volunteerService.findByBno(volunteerBoardNo));
        model.addAttribute("content", volunteerService.get(volunteerBoardNo));
        model.addAttribute("criteria", criteria);
        return "volunteer/volunteerContent";}
}
