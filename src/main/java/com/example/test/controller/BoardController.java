package com.example.test.controller;


import com.example.test.model.beans.vo.PageDTO;
import com.example.test.model.mainBoard.vo.AnswerVO;
import com.example.test.model.mainBoard.vo.AttachFileVO;
import com.example.test.model.mainBoard.vo.BoardVO;
import com.example.test.model.beans.vo.Criteria;
import com.example.test.model.user.vo.DocVO;
import com.example.test.services.AnswerService;
import com.example.test.services.BoardService;
import com.example.test.services.DocService;
import com.example.test.services.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/mainBoard/*")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final AnswerService answerService;
    private final DocService docService;
    private final MyPageService myPageService;

    @GetMapping("mainBoard")
    public String list(Criteria criteria,HttpServletRequest req, Model model){


        if (criteria.getKeyword() == null){
            criteria.setKeyword("");
        }
        if (criteria.getTown() == null){
            criteria.setTown("");
        }
        if (criteria.getQnaMajor() == null){
            criteria.setQnaMajor("");
        }

        log.info("-------------------------------");
        log.info("list");
        log.info("Criteria 출력" + criteria.toString());
        log.info("-------------------------------");

        HttpSession session = req.getSession();
        if(session.getAttribute("userNo")==null) {
            model.addAttribute("loginCheck", 0);
        }else{
            model.addAttribute("loginCheck",1);
        }

        model.addAttribute("like",boardService.getLikeCnt());
        model.addAttribute("reply",boardService.getReplyCnt());

        model.addAttribute("list", boardService.getSearchList(criteria));
        model.addAttribute("pageMaker", new PageDTO(boardService.getTotal(criteria), 10, criteria));
        return "mainBoard/mainBoard";
    }

//    @GetMapping("mainBoard2")
//    public String list2(Criteria criteria,HttpServletRequest req, Model model){
//        log.info("-------------------------------");
//        log.info("list");
//        log.info(criteria.toString());
//        log.info("-------------------------------");
//
//
//
//        HttpSession session = req.getSession();
//        if(session.getAttribute("userNo")==null) {
//            model.addAttribute("loginCheck", 0);
//        }else{
//            model.addAttribute("loginCheck",1);
//        }
//
//
//        model.addAttribute("like",boardService.getLikeCnt());
//        model.addAttribute("reply",boardService.getReplyCnt());
//
//        model.addAttribute("list", boardService.getSearchList(criteria));
//        model.addAttribute("pageMaker", new PageDTO(boardService.getTotal(criteria), 10, criteria));
//        return "mainBoard/mainBoard";
//    }

    @PostMapping("write2")
    public RedirectView register(BoardVO boardVO,HttpServletRequest req, RedirectAttributes rttr){

        HttpSession session = req.getSession();
        boardVO.setUserNo(Long.parseLong(session.getAttribute("userNo").toString()));
        System.out.println("AttachList : " + boardVO.getAttachList());


        /*log.info(boardVO.toString());*/
        log.info("-------------------------------");
        log.info("register : " + boardVO.toString());
        log.info("-------------------------------");

        if(boardVO.getAttachList() != null){
            log.info("첨부파일 null아님, 들어간다.");
            boardVO.getAttachList().forEach(attach -> log.info("파일 : " + attach.toString()));
        }

        boardService.register(boardVO);

//        쿼리 스트링으로 전달
//        rttr.addAttribute("bno", boardVO.getBno());
//        세션의 flash영역을 이용하여 전달
//        rttr.addFlashAttribute("qnaNo", boardVO.getQnaNo());
//        RedirectView를 사용하면 redirect방식으로 전송이 가능하다.
        return new RedirectView("mainBoard");
    }
    @GetMapping("detail")
    public void read(@RequestParam("qnaNo") Long qnaNo, Criteria criteria, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("qnaNo",qnaNo);

        /* 로그인 후에 신고 가능 하기 위해 넘김/
        Long checkUser = (Long) session.getAttribute("userNo");
        model.addAttribute("checkUser", checkUser);
        / 의사는 신고 못하게 막기 위해 넘김 */
        Long checkDoc = (Long) session.getAttribute("docNo");
        model.addAttribute("checkDoc", checkDoc);


        if(session.getAttribute("doc") != null){
            DocVO doc = (DocVO) session.getAttribute("doc");
            int reportCnt = doc.getDocReportCnt();
            model.addAttribute("reportCnt", reportCnt);
        }

        String reqURI = request.getRequestURI();
        String reqType = reqURI.substring(reqURI.indexOf(request.getContextPath()) + 7);
        //read 요청 시 read 출력
        //modify 요청 시 modify 출력
        log.info("-------------------------------");
        log.info(reqType + " : " + qnaNo);
        log.info("-------------------------------");

        if(session.getAttribute("userNo") == null && session.getAttribute("docNo") == null){
            model.addAttribute("likeCheck", 0);
        }
        if(session.getAttribute("docNo")==null) {
            model.addAttribute("loginCheck", 0);
        }else{
            model.addAttribute("loginCheck",session.getAttribute("docNo"));
        }

        model.addAttribute("qnaNo",qnaNo);
        model.addAttribute("answerList",answerService.answerList(qnaNo));
        boardService.updateView(qnaNo);
        model.addAttribute("board", boardService.get(qnaNo));
        model.addAttribute("file", boardService.getAttachList(qnaNo));
        model.addAttribute("criteria", criteria);
    }

    @ResponseBody
    @GetMapping("replyList/{qnaNo}")
    public List<AnswerVO> replyList(@PathVariable("qnaNo") Long qnaNo){
        return answerService.answerList(qnaNo);
    }


    @GetMapping("modify")
    public void modify(@RequestParam("qnaNo") Long qnaNo, Criteria criteria, Model model, HttpServletRequest request){
        String reqURI = request.getRequestURI();
        String reqType = reqURI.substring(reqURI.indexOf(request.getContextPath()) + 7);
        //read 요청 시 read 출력
        //modify 요청 시 modify 출력
        log.info("-------------------------------");
        log.info(reqType + " : " + qnaNo);
        log.info("-------------------------------");

        model.addAttribute("board", boardService.get(qnaNo));
        model.addAttribute("criteria", criteria);
    }
    @PostMapping("modify")
    public RedirectView modify(BoardVO boardVO, RedirectAttributes rttr){
        log.info("-------------------------------");
        log.info("modify : " + boardVO.toString());
        log.info("-------------------------------");

        if(boardService.modify(boardVO)){
            rttr.addAttribute("result", "success");
            rttr.addAttribute("qnaNo", boardVO.getQnaNo());
        }
        return new RedirectView("read");
    }
    @PostMapping("remove")
    public RedirectView remove(@RequestParam("qnaNo") Long qnaNo, RedirectAttributes rttr) {
        log.info("-------------------------------");
        log.info("remove : " + qnaNo);
        log.info("-------------------------------");

        List<AttachFileVO> attachList = boardService.getAttachList(qnaNo);

        if (boardService.remove(qnaNo)) {
            deleteFiles(attachList);
            rttr.addFlashAttribute("result", "success");
        } else {
            rttr.addFlashAttribute("result", "fail");
        }
        return new RedirectView("list");
    }

    private void deleteFiles(List<AttachFileVO> attachList){
        if(attachList == null || attachList.size() == 0){
            return;
        }

        log.info("delete attach files...........");
        log.info(attachList.toString());

        attachList.forEach(attach -> {
            try {
                Path file = Paths.get("C:/upload/" + attach.getUploadPath() + "/" + attach.getUuid() + "_" + attach.getFileName());
                Files.delete(file);

                if(Files.probeContentType(file).startsWith("image")){
                    Path thumbnail = Paths.get("C:/upload/" + attach.getUploadPath() + "/s_" + attach.getUuid() + "_" + attach.getFileName());
                    Files.delete(thumbnail);
                }
            } catch (Exception e) {
                log.error("delete file error " + e.getMessage());
            }
        });


    }
    @GetMapping("write")
    public String write(Model model){
        return "mainBoard/write";
    }

    //    게시글 첨부파일
//    @GetMapping(value = "getAttachList", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public List<AttachFileVO> getAttachList(Long qnaNo){
//        log.info("getAttachList " + qnaNo);
//        return boardService.getAttachList(qnaNo);
//    }

    @GetMapping("writeReport")
    public String writeReport(Long reQnaNo, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
                Long userNo = (Long) session.getAttribute("userNo");

        log.info("----------------------------------------");
        log.info("reQnaNo" + reQnaNo);
        log.info("----------------------------------------");
        model.addAttribute("report", docService.viewReport(reQnaNo));
        model.addAttribute("userNo",userNo);
        return "mainBoard/report";
    }

    @DeleteMapping("remove/{qnaNo}")
    @ResponseBody
    public void remove(@PathVariable("qnaNo") Long qnaNo){
        answerService.delete(qnaNo);
    }

}
