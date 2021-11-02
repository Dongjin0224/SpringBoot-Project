package com.example.test.controller;


import com.example.test.model.beans.vo.PageDTO;
import com.example.test.model.mainBoard.vo.AttachFileVO;
import com.example.test.model.mainBoard.vo.BoardVO;
import com.example.test.model.beans.vo.Criteria;
import com.example.test.services.BoardService;
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

    @GetMapping("mainBoard")
    public String list(Criteria criteria,HttpServletRequest req, Model model){
        log.info("-------------------------------");
        log.info("list");
        log.info(criteria.toString());
        log.info("-------------------------------");



        HttpSession session = req.getSession();
        if(session.getAttribute("userNo")==null) {
            model.addAttribute("loginCheck", 0);
        }else{
            model.addAttribute("loginCheck",1);
        }


        model.addAttribute("like",boardService.getLikeCnt());
        model.addAttribute("reply",boardService.getReplyCnt());

        model.addAttribute("list", boardService.getList(criteria));
        model.addAttribute("pageMaker", new PageDTO(boardService.getTotal(criteria), 10, criteria));
        return "mainBoard/mainBoard";
    }

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
        String reqURI = request.getRequestURI();
        String reqType = reqURI.substring(reqURI.indexOf(request.getContextPath()) + 7);
        //read 요청 시 read 출력
        //modify 요청 시 modify 출력
        log.info("-------------------------------");
        log.info(reqType + " : " + qnaNo);
        log.info("-------------------------------");

        boardService.updateView(qnaNo);
        model.addAttribute("board", boardService.get(qnaNo));
        model.addAttribute("criteria", criteria);
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
}
