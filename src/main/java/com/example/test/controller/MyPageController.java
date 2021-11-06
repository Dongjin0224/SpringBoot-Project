package com.example.test.controller;

import com.example.test.model.beans.vo.Criteria;
import com.example.test.model.beans.vo.PageDTO;
import com.example.test.model.mainBoard.vo.BoardVO;
import com.example.test.model.user.vo.DocVO;
import com.example.test.model.user.vo.UserVO;
import com.example.test.model.volunteer.vo.ApplicantsVO;
import com.example.test.services.MyPageService;
import com.example.test.services.PayService;
import com.example.test.services.VolunteerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Controller
@Slf4j
@RequestMapping("/myPage/*")
@RequiredArgsConstructor
public class MyPageController {

    public final MyPageService myPageService;
    public final VolunteerService volunteerService;
    public final PayService payService;

    @GetMapping("myPageUser")
    public String myPageUser(Model model, HttpServletRequest request) {
        HttpSession session = (HttpSession)request.getSession();
        UserVO user = (UserVO) session.getAttribute("user");
        Long userNo = user.getUserNo();
        model.addAttribute("user", myPageService.viewUser(userNo));
        model.addAttribute("getResList", myPageService.getResList(userNo));
        model.addAttribute("getQnaList", myPageService.getQnaList(userNo));
        return "myPage/myPageUser";
    }

    @PostMapping("updateUser")
    @ResponseBody
    public String updateUser(@RequestBody UserVO userVO, HttpServletRequest request){

        HttpSession session = (HttpSession)request.getSession();
        Long userNo = (Long) session.getAttribute("userNo");

        userVO.setUserNo(Long.parseLong(String.valueOf(userNo)));

        log.info("-------------------------------");
        log.info("userNo : " + String.valueOf(userVO.getUserNo()));
        log.info("-------------------------------");

        log.info("---------------------------");
        System.out.println("userVO : " + userVO);
        log.info("---------------------------");

        myPageService.updateUser(userVO);

        return "수정완료";
    }


    @GetMapping("myPageDoc")
    public String myPageDoc(Model model, HttpServletRequest request, Criteria criteria){
        HttpSession session = (HttpSession)request.getSession();
        Long docNo = (Long) session.getAttribute("docNo");
        model.addAttribute("doc", myPageService.viewDoc(docNo));
        model.addAttribute("getVolList", myPageService.getVolList(docNo));
        model.addAttribute("getAppList", myPageService.getAppList(docNo));
        model.addAttribute("pay", payService.getPayList(docNo));
        /*model.addAttribute("pageMaker", new PageDTO(volunteerService.getTotal(criteria), 10, criteria));*/
        model.addAttribute("getQnaReply", myPageService.getQnaReply(docNo));
        /*model.addAttribute("pageMaker", new PageDTO(myPageService.getVolTotal(criteria), 5, criteria));*/
        log.info("------------------------------------");
        log.info("docNo" + String.valueOf(docNo));
        log.info("------------------------------------");

        return "myPage/myPageDoc";
    }

    @GetMapping("applicationForm")
    public String applicationForm(@RequestParam("applicantsNo") Long applicantsNo, Model model, HttpServletRequest request){
        HttpSession session = (HttpSession)request.getSession();
        Long docNo = (Long) session.getAttribute("docNo");

        model.addAttribute("getVolContent", myPageService.getVolContent(docNo, applicantsNo));
        return "myPage/applicationForm";
    }

    @PostMapping("updateDoc")
    @ResponseBody
    public String updateDoc(@RequestBody DocVO docVO, HttpServletRequest request){

        HttpSession session = (HttpSession)request.getSession();
        Long docNo = (Long) session.getAttribute("docNo");

        docVO.setDocNo(Long.parseLong(String.valueOf(docNo)));

        log.info("------------------------------------");
        log.info("docNo : " + String.valueOf(docVO.getDocNo()));
        log.info("------------------------------------");

        log.info("---------------------------");
        System.out.println("dovVO : " + docVO.toString());
        log.info("---------------------------");

        myPageService.updateDoc(docVO);

        return "수정완료";
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
    @PostMapping("sendSmsUser")
    @ResponseBody
    public String sendSmsUser(HttpServletRequest request){



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
        set.put("to", request.getParameter("userPhoneNum")); // 수신번호
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
