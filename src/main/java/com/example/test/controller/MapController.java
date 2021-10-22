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
    public String searchDocs(){
        return "map/searchDocs";
    }

    @PostMapping("searchMap")
    public String map(){
        return "map/searchMap";
    }

    @GetMapping("searchMap")
    public void searchMap(@RequestParam String search, Model model){
        model.addAttribute("searchList",service.getSearchList(search));
    }



}
