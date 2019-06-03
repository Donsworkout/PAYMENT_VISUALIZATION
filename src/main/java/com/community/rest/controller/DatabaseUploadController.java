package com.community.rest.controller;

import com.community.rest.repository.TradeRepository;
import com.community.rest.service.DataBaseUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("data")
public class DatabaseUploadController {

    @Autowired
    DataBaseUploadService dataBaseUploadService;

    @Autowired
    TradeRepository tradeRepositorty;


    @GetMapping("")
    public String uploadDatabase() throws Exception {

        dataBaseUploadService.uploadDailyStatic(tradeRepositorty.findAll());
        return "redirect:/upload";
    }
}
