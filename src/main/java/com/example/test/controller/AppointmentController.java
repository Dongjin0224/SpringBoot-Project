package com.example.test.controller;

import com.example.test.model.appointment.vo.ReserveDocVO;
import com.example.test.model.appointment.vo.ReserveUserVO;
import com.example.test.model.appointment.vo.ReserveVO;
import com.example.test.model.beans.vo.Criteria;
import com.example.test.model.user.vo.UserVO;
import com.example.test.services.AppointmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping("/appointment/*")
@RequiredArgsConstructor
public class AppointmentController {

    public final AppointmentService appointmentService;

    @GetMapping("appointment")
    public void read(@RequestParam("docNo") Long docNo, Criteria criteria, Model model, HttpServletRequest request){

        HttpSession session = request.getSession();
        session.setAttribute("docNo",docNo);

        model.addAttribute("appointment", appointmentService.get(docNo));
        model.addAttribute("file", appointmentService.getFile(docNo));
          model.addAttribute("criteria", criteria);
    }

    @PostMapping(value = "reserve",  consumes = "application/json", produces = "text/plain; charset=utf-8")
    @ResponseBody
    public String reserve(ReserveVO reserveVO, HttpServletRequest request, Model model) {
        /* 로그인 한 유저 번호 가져오기 (세션) */
        HttpSession session = (HttpSession) request.getSession();


        if (session.getAttribute("userNo") == null) {
            model.addAttribute("loginCheck", 0);
            return "실패";
        } else {
            model.addAttribute("loginCheck", session.getAttribute("userNo"));
            UserVO userVO = (UserVO) session.getAttribute("user");
            Long userNo = userVO.getUserNo();
            /*  해당 의사 번호 가져오기*/
            Long docNo = (Long) session.getAttribute("docNo");
            System.out.println("의사번호 = " + docNo);

            /*유저 회원번호와 의사 회원번호를 reserveVO에 담기 */
            reserveVO.setUserNo(userNo);
            reserveVO.setDocNo(docNo);
            System.out.println("reservVO = " + reserveVO);

            /* DB에 예약 정보 넘기기 */
            appointmentService.reserve(reserveVO);

            /*세션에 담긴 의사 번호 초기화 */
            session.setAttribute("docNo", null);
            docNo = (Long) session.getAttribute("docNo");
            System.out.println("초기화 된 의사번호 = " + docNo);

            /*크롤링으로 문자 보내기 */
            ReserveUserVO user = appointmentService.getUserPhone(reserveVO.getUserNo());
            ReserveDocVO doc = appointmentService.getDocPhone(reserveVO.getDocNo());
            String userName = user.getUserName();
            String docName = doc.getDocName();
            String userPhoneNum = user.getUserPhoneNum();
            String docPhoneNum = doc.getDocPhoneNum();

            String[] name = {userName, docName};
            String[] phoneNum = {userPhoneNum, docPhoneNum};

            CrawlingController test = new CrawlingController();
            test.open(name, phoneNum);

            return "성공";
        }
    }
}
