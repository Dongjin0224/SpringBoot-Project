package com.example.test.controller;

import com.example.test.model.user.vo.UserVO;
import com.example.test.services.DocService;

import com.example.test.model.user.vo.DocVO;

import com.example.test.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Random;

@Slf4j
@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
public class DocController {

    private final DocService service;


    @GetMapping("docLogin")
    public String docLogin(){
        return "user/docLogin";
    }

    @GetMapping("doctorSignUp")
    public String doctorSignUp(){return "user/doctorSignUp";}

    @PostMapping("doctorSignUp")
    public String doctorSignUp(DocVO vo){
        log.info("---------------DocController...들어옴------------------");

        int result =service.checkId(vo);

        log.info("docNo : " + String.valueOf(vo.getDocNo()));
        log.info("-----------------------------------------");
        log.info(String.valueOf(vo.getAttachList().size()));
        log.info(String.valueOf(vo.getHosattachList().size()));
        log.info("-----------------------------------------");
       if(vo.getAttachList() != null){
            vo.getAttachList().forEach(attach -> log.info(attach.toString()));
        }
        if(vo.getHosattachList() != null){
            vo.getHosattachList().forEach(hosattach -> log.info(hosattach.toString()));
        }


        if(result == 1) {
            return "user/doctorSignUp";
        }else if(result == 0) {
            service.DocSignUp(vo);
        }
        return "user/login";
    }

    @PostMapping("docLogin")
    public RedirectView docLogin(DocVO vo, HttpServletRequest req, RedirectAttributes rttr) {
        HttpSession session = req.getSession();
        DocVO login = service.docLogin(vo);

        if (login == null) {
            session.setAttribute("doc", null);
            /*rttr.addFlashAttribute("msg",false);*/
        } else {
            session.setAttribute("doc", login);
            session.setAttribute("docNo", login.getDocNo());
        }
        return new RedirectView("/index");
    }


    @PostMapping("checkDocId")
    @ResponseBody
    public int checkId(DocVO vo){
        int result= service.checkId(vo);
        return result;
        }

    @GetMapping("docLogout")
    public String docLogout(HttpServletRequest req) {
        HttpSession session = req.getSession(false);

        if (session != null) {
            session.invalidate();
        } return "user/docLogin";
    }

    @PostMapping("sendSms")
    @ResponseBody
    public String sendSms(HttpServletRequest request){


        Random rand  = new Random();
        String numStr = "";
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr+=ran;
        }

        String api_key = "NCSS8MCEL5SR6BNU"; //위에서 받은 api key를 추가
        String api_secret = "1888USAI95MUT5FAREIKZ9NYWQ0TIWTJ";  //위에서 받은 api secret를 추가

        com.example.test.controller.Coolsms coolsms = new com.example.test.controller.Coolsms(api_key, api_secret);
        //이 부분은 홈페이지에서 받은 자바파일을 추가한다음 그 클래스를 import해야 쓸 수 있는 클래스이다.


        HashMap<String, String> set = new HashMap<String, String>();
        log.info("------------------sendSms들어옴----------------");
        set.put("to", request.getParameter("docPhoneNum")); // 수신번호
        log.info("------------------sendSms수신번호들어옴----------------");
        set.put("from", "01046034026"); // 발신번호, 발신번호를 받아 map에 저장한다.
        set.put("text", "본인인증번호는"+numStr+"입니다. 정확히 입력해 주세요."); // 문자내용, 문자내용을 받아 map에 저장한다.
        set.put("type", "sms"); // 문자 타입

        System.out.println(set);

        JSONObject result = coolsms.send(set); // 보내기&전송결과받기

        if ((boolean)result.get("status") == true) {

            // 메시지 보내기 성공 및 전송결과 출력
            System.out.println("성공");
            System.out.println(result.get("group_id")); // 그룹아이디
            System.out.println(result.get("result_code")); // 결과코드
            System.out.println(result.get("result_message")); // 결과 메시지
            System.out.println(result.get("success_count")); // 메시지아이디
            System.out.println(result.get("error_count")); // 여러개 보낼시 오류난 메시지 수
        } else {

            // 메시지 보내기 실패
            System.out.println("실패");
            System.out.println(result.get("code")); // REST API 에러코드
            System.out.println(result.get("message")); // 에러메시지
        }
        return numStr;
    }

}
