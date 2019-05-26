package com.community.rest.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.community.rest.config.ExcelConfig;
import com.community.rest.domain.Merchant;
import com.community.rest.repository.MerchantRepository;
import com.community.rest.repository.TradeRepository;
import com.community.rest.service.CoordsParsingService;
import com.community.rest.service.DataBaseUploadService;
import com.community.rest.service.DataUploadService;

@Controller
@RequestMapping("upload")
public class DataUploadController {
	private static final Logger LOGGER = LogManager.getLogger(DataUploadController.class);
	
	@Autowired
	DataUploadService dataUploadService = new DataUploadService();

	@Autowired
	CoordsParsingService coordsParsingService = new CoordsParsingService();
	
	@Autowired
	MerchantRepository merchantRepository;
	
    @Autowired
    DataBaseUploadService dataBaseUploadService = new DataBaseUploadService();

    @Autowired
    TradeRepository tradeRepositorty;

    @Autowired
    MongoTemplate mongoTemplate;
  
    @Autowired
    ExcelConfig excelconfig;
    
	@GetMapping("")
	public String uploadForm() {
		return "upload/form";
	}
	
    @PostMapping("/ajax")
    public String excelUpload(MultipartHttpServletRequest request, String sheetType) {
    	
    	LOGGER.info("Excel Upload Started");

        MultipartFile excelFile =request.getFile("excelFile");
        
        if(excelFile == null || excelFile.isEmpty()){
            throw new RuntimeException("엑셀파일을 선택 해 주세요.");
        }

        File destFile = new File(excelconfig.getSavePath() + excelFile.getOriginalFilename());

        try{
            excelFile.transferTo(destFile);
        } catch(IllegalStateException | IOException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        
        try {
			dataUploadService.excelUpload(destFile, sheetType);
		} catch (Exception e) {
			LOGGER.warn("Check sheetType parameter is valid : {}", sheetType);
		}

        return "redirect:/upload";
    }

    
    @PostMapping("/coords_setting")
	public String coordsSetting() {
    	Query query = new Query();
    	query.addCriteria(Criteria.where("xPos").is(null).orOperator(Criteria.where("yPos").is(null)));
    	List<Merchant> coordsNotSetted = mongoTemplate.find(query,Merchant.class);   	
    	if(!coordsNotSetted.isEmpty()) {
        	coordsParsingService.setMerchantsCoords(coordsNotSetted);    		
    	}
    	
        return "redirect:/upload";
	}
}
