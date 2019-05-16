package com.community.rest.controller;

import java.io.File;
import java.io.IOException;

import com.community.rest.repository.TradeRepository;
import com.community.rest.service.DataBaseUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.community.rest.service.DataUploadService;

@Controller
@RequestMapping("/upload")
public class DataUploadController {
	@Autowired
	DataUploadService dataUploadService = new DataUploadService();

    @Autowired
    DataBaseUploadService dataBaseUploadService = new DataBaseUploadService();

    @Autowired
    TradeRepository tradeRepositorty;

	@GetMapping("")
	public String uploadForm() {
		return "/upload/form";
	}
	
    @PostMapping("/ajax")
    public String excelUpload(MultipartHttpServletRequest request, String sheet_type)  throws Exception{
    	System.out.println("업로드 시작");
    	System.out.println(sheet_type);
        MultipartFile excelFile =request.getFile("excelFile");
        if(excelFile==null || excelFile.isEmpty()){
            throw new RuntimeException("엑셀파일을 선택 해 주세요.");
        }
        File destFile = new File("/Users/doheeKang/Desktop" + excelFile.getOriginalFilename());
        //File destFile = new File("./src/main/resources/static/files/" + excelFile.getOriginalFilename());
        try{
            excelFile.transferTo(destFile);
        }catch(IllegalStateException | IOException e){
            throw new RuntimeException(e.getMessage(),e);
        }
        dataUploadService.excelUpload(destFile, sheet_type);

        return "redirect:/upload";
    }

}