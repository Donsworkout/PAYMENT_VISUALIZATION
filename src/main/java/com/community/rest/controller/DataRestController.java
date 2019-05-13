package com.community.rest.controller;

import com.community.rest.domain.Merchant;
import com.community.rest.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/api")
public class DataRestController {
    @Autowired

    private MerchantRepository merchantRepository;


    @GetMapping(path="/merchant")
    public @ResponseBody Iterable<Merchant> getAllMerchant() {
        // This returns a JSON or XML with the users
        return merchantRepository.findAll();
    }

}