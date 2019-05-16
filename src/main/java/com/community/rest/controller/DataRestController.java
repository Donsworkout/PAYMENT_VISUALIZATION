package com.community.rest.controller;

import com.community.rest.domain.Trade;
import com.community.rest.repository.DailyStaticRepository;
import com.community.rest.repository.MerchantRepository;
import com.community.rest.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/map") //API URL
public class DataRestController {

    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    TradeRepository tradeRepositorty;

    @Autowired
    DailyStaticRepository dailyStaticRepository;

   @GetMapping({"","/"})
    public String board(Model model) {
        model.addAttribute("merchantList", merchantRepository.findAll());
        //merchantTradeInfoList라고 해당 날자에 해당하는 가게명, 총합, 총 거래수를 넘겨주어야함
//        model.addAttribute("merchantTradeInfoList", dailyStaticRepository.findByDate());
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
    
    @GetMapping("/byDay")
    public String dayBd(Model model) {
    	 //model.addAttribute("merchantList", merchantRepository.findAll());
    	 //model.addAttribute("tList",tradeRepositorty.findAll());
         return "/dayView";
    }
    
}