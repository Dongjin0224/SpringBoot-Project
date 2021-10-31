package com.example.test.controller;

import com.example.test.model.user.vo.DocVO;
import com.example.test.services.UserService;
import com.example.test.model.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

@Slf4j
@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("userLogin")
    public String memberSignUp() {
        return "user/login";
    }

    @PostMapping("SignUp")
    public String memberSignUp(UserVO vo) {
        log.info("---------------들어옴------------------");

        int result =service.checkId(vo);
        if(result == 1) {
            return "user/login";
        }else if(result == 0) {
            service.memberSignUp(vo);
        }
        return "user/login";
    }

    @PostMapping("userLogin")
    public RedirectView userLogin(UserVO vo, HttpServletRequest req, RedirectAttributes rttr) {
        HttpSession session = req.getSession();
        UserVO login = service.userLogin(vo);

        if (login == null) {
            session.setAttribute("user", null);
            /*rttr.addFlashAttribute("msg",false);*/
        } else {
            session.setAttribute("user", login);
        }
        return new RedirectView("/index");
    }


    @PostMapping("checkUserId")
    @ResponseBody
    public int checkId(UserVO vo){
        int result= service.checkId(vo);
        return result;
    }


    @PostMapping("sendUserSms")
    @ResponseBody
    public String sendUserSms(HttpServletRequest request){


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
