package com.example.test.controller;

import com.example.test.services.UserService;
import com.example.test.model.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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

        service.memberSignUp(vo);
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



    @GetMapping("checkId")
    public String checkId(String userId, Model model) throws IOException {

        log.info("-----------------------------------");
        log.info("들어옴");
        log.info(userId);
        log.info(String.valueOf(service.checkId(userId)));
        log.info("-----------------------------------");

//        JSONObject obj = new JSONObject();
        if (service.checkId(userId)) {
            model.addAttribute("result", "사용 가능");
        } else {
            model.addAttribute("result", "사용 불가");
//            obj.put("status", "not-ok");
        }

        return "/user/login :: idCheck_text";
    }


}
