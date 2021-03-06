package com.community.rest.controller;

import com.community.rest.domain.Trade;
import com.community.rest.repository.DailyStaticRepository;
import com.community.rest.repository.MerchantRepository;
import com.community.rest.repository.TradeRepository;
import com.community.rest.service.CoordsParsingService;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/map") //API URL
public class DataRestController {

    private final MerchantRepository merchantRepository;

    private final TradeRepository tradeRepository;

    private final DailyStaticRepository dailyStaticRepository;

   @GetMapping({"","/"})
    public String board(Model model) throws ParseException {
        model.addAttribute("merchantList", merchantRepository.findAll());
        Date tradedate = new SimpleDateFormat("yyyyMMdd").parse("20190509");
        model.addAttribute("merchantTradeInfoList", dailyStaticRepository.findByTradeDate(tradedate));
        return "/index";
    }

    @GetMapping("/trade")
    public @ResponseBody List<Trade> trade(HttpServletRequest request) {
        String merchantId = request.getParameter("id");
        return tradeRepository.findByMerchantId(Long.parseLong(merchantId));
    }

    @GetMapping("/allTrade")
    public @ResponseBody List<Trade> trades(){
        return tradeRepository.findAll();
    }
    
    @GetMapping("/byDay")
    public String dayBd(Model model) {
    	 model.addAttribute("SD",tradeRepository.findFirstByOrderByTradeDateDesc().tradeDate);
    	 model.addAttribute("ED",tradeRepository.findFirstByOrderByTradeDateAsc().tradeDate);
         return "/dayView";
    }


    
}