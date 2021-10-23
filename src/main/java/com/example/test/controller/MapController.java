package com.example.test.controller;

import com.example.test.services.MapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping("/map/*")
@RequiredArgsConstructor
public class MapController {

    private final MapService service;

    @GetMapping("searchDocs")
    public String searchDocs(@RequestParam String docHospitalName, Model model){
        model.addAttribute("docs",service.getDocs(docHospitalName));
        model.addAttribute("hosName",docHospitalName);
        log.info("---------------------");
        log.info(docHospitalName);
        log.info("---------------------");
        return "map/searchDocs";
    }

    @GetMapping("searchMap")
    public String map(Model model){
        log.info("들어옴---------------------");
        log.info(String.valueOf(service.getSearchList("sdfdsf2ewf32rf23f").size()));
        log.info("들어옴---------------------");
        model.addAttribute("searchList", service.getSearchList(""));
        model.addAttribute("allList",service.getList());
        return "map/searchMap";

    }

    @PostMapping("searchMap")
    public void searchMap(@RequestParam String search, Model model){
        model.addAttribute("searchList",service.getSearchList(search));
        model.addAttribute("allList",service.getList());
    }



}
