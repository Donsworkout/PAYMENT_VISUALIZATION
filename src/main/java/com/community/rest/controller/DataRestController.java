package com.community.rest.controller;

import com.community.rest.domain.Trade;
import com.community.rest.repository.MerchantRepository;
import com.community.rest.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/map") //API URL 경로를 /map으로 정의
public class DataRestController {

    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    TradeRepository tradeRepositorty;

    @GetMapping({"","/"})
    public String board(Model model) {
        model.addAttribute("merchantList", merchantRepository.findAll());
        return "/index";
    }

    @GetMapping("/trade")
    public @ResponseBody List<Trade> trade(HttpServletRequest request) {
        String merchantId = request.getParameter("id");
        return tradeRepositorty.findByMerchantIdId(Long.parseLong(merchantId));
    }

    @GetMapping("/allTrade")
    public @ResponseBody List<Trade> trades(){
        return tradeRepositorty.findAll();
    }
}