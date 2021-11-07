package com.example.test.controller;

import com.example.test.model.user.vo.DocAttachFileVO;
import com.example.test.model.user.vo.DocHosAttachFileVO;
import com.example.test.model.user.vo.DocVO;
import com.example.test.services.AppointmentService;
import com.example.test.services.DocService;
import com.example.test.services.MapService;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/map/*")
@RequiredArgsConstructor
public class MapController {

    private final MapService service;
    public final AppointmentService appointmentService;

    @GetMapping("searchDocs")
    public String searchDocs(@RequestParam String docHospitalName, Model model, HttpServletRequest request){
        List<DocVO> list = service.getDocs(docHospitalName);

        HttpSession session = request.getSession();
        if(session.getAttribute("userNo") == null && session.getAttribute("docNo") == null){
            model.addAttribute("loginCheck", 0);
        }


        model.addAttribute("hosName",docHospitalName);
        log.info("---------------------");
        log.info(docHospitalName);
        log.info("---------------------");

//        List<DocVO> docPic = new ArrayList<>();

//        for (int i=0;i<list.size();i++){
////            docPic.add(service.docPic(list.get(i).getDocNo()));
//            list.get(i).setFileName(service.docPic(list.get(i).getDocNo()).getFileName());
//            list.get(i).setUuid(service.docPic(list.get(i).getDocNo()).getUuid());
//            list.get(i).setUploadPath(service.docPic(list.get(i).getDocNo()).getUploadPath());
//            list.get(i).setImage(service.docPic(list.get(i).getDocNo()).isImage());
//
//        }
        log.info(list.toString());
//        model.addAttribute("file",docPic);
        model.addAttribute("docs",list);
//        model.addAttribute("docPic",docPic);
        return "map/searchDocs";
    }

    @GetMapping("searchMap")
    public String map(Model model){
        log.info("들어옴---------------------");
        log.info(String.valueOf(service.getSearchList("sdfdsf2ewf32rf23f").size()));
        log.info(service.getSearchList("").toString());
        log.info("들어옴---------------------");

        model.addAttribute("searchList", service.getSearchList(""));
        model.addAttribute("allList",service.getList());
        return "map/searchMap";

}
    @PostMapping("searchMap")
    public void searchMap(@RequestParam String search, Model model){
        log.info("----------------------------------------------------");
        log.info(service.getSearchList(search).toString());
        log.info("----------------------------------------------------");
        model.addAttribute("searchList",service.getSearchList(search));
        model.addAttribute("allList",service.getList());
    }



}
