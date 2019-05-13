package com.community.rest.controller;

import com.community.rest.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/map") //API URL 경로를 /map으로 정의
public class DataRestController {

    @Autowired
    MerchantRepository merchantRepository;

    @GetMapping({"","/"}) //매핑 경로
    public String board(Model model) {
        model.addAttribute("merchantList", merchantRepository.findAll());
        return "/index";
    }
}