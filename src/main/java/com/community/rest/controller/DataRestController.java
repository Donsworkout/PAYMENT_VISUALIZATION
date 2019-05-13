package com.community.rest.controller;

import com.community.rest.domain.Merchant;
import com.community.rest.domain.Trade;
import com.community.rest.repository.MerchantRepository;
import com.community.rest.repository.TradeRepositorty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/map") //API URL 경로를 /map으로 정의
public class DataRestController {

    @GetMapping({"","/"}) //매핑 경로
    public String board(Model model) {
        model.addAttribute("data", "hello word");
        return "/index";
    }
}