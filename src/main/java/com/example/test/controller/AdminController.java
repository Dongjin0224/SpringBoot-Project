package com.example.test.controller;

import com.example.test.model.user.vo.DocVO;
import com.example.test.model.user.vo.UserVO;
import com.example.test.services.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Slf4j
@RequestMapping("/admin/*")
@RequiredArgsConstructor
public class AdminController {

    public final AdminService adminService;

    @GetMapping("admin")
    public String admin(){return "admin/adminHome";}

    /* 일반회원 관리 */
    @GetMapping("adminUpdateUser")
    public void getUser(Model model){
        model.addAttribute("user", adminService.readUser());
        model.addAttribute("totalUser", adminService.totalUser());
    }

    @GetMapping("updateUser")
    public RedirectView updateUser(UserVO user, RedirectAttributes rttr){
        log.info("-------------------------------");
        log.info("modify : " + user.toString());
        log.info("-------------------------------");

        adminService.updateUser(user);

        return new RedirectView("adminUpdateUser");
    }

    /* 의사회원 관리 */
    @GetMapping("adminUpdateDoc")
    public void getDoc(Model model){
        model.addAttribute("doc", adminService.readDoc());
        model.addAttribute("totalDoc", adminService.totalDoc());
    }

    @GetMapping("updateDoc")
    public RedirectView updateDoc(DocVO doc, RedirectAttributes rttr){
        log.info("-------------------------------");
        log.info("modify : " + doc.toString());
        log.info("-------------------------------");

        adminService.updateDoc(doc);

        return new RedirectView("adminUpdateDoc");
    }
}
