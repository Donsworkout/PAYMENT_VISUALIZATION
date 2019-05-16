package com.community.rest.service;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.community.rest.domain.DailyStatic;
import com.community.rest.repository.DailyStaticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.community.rest.domain.Merchant;
import com.community.rest.domain.Trade;
import com.community.rest.repository.MerchantRepository;
import com.community.rest.repository.TradeRepository;
import com.community.rest.utilities.ExcelRead;
import com.community.rest.utilities.ExcelReadOption;

@Service
public class DataUploadService {
	@Autowired
	private TradeRepository tradeRepository;
	
	@Autowired
	private MerchantRepository merchantRepository;
	
	@Autowired
	private CoordsParsingService coordsParsingService;

	@Autowired
	private DailyStaticRepository dailyStaticRepository;
	
	public void excelUpload(File destFile, String type) throws Exception {
		
		ExcelReadOption excelReadOption = new ExcelReadOption();
		excelReadOption.setFilePath(destFile.getAbsolutePath());
		excelReadOption.setStartRow(2);
		
		if(type.equals("trade")) {
			//("ID", "TRADEDATE", "TRADETYPE", "AMOUNT", "FEE", "MERCHANTID", "SERVICETYPE", "TRADEACCESS");
			excelReadOption.setOutputColumns("A", "B", "C", "D", "E", "F", "G", "H");
			loadTrade(excelReadOption);
		}else if(type.equals("merchant")) {
			//("ID", "MERCHANTNAME", "REGDATE", "SERVICETYPE", "STATUSTYPE", "ADDRESS", "ADDRESSDETAIL");
			excelReadOption.setOutputColumns("A", "B", "C", "D", "E", "F", "G");
			loadMerchant(excelReadOption);
		}else {
			System.out.println("업로드가 지원되지 않는 타입입니다");
			return;
		}

	}


	public void loadTrade(ExcelReadOption excelReadOption) throws NumberFormatException {
		if(tradeRepository.count() > 1) {
			tradeRepository.deleteAll();			
		}
		List<Map<String, String>> excelContent = ExcelRead.read(excelReadOption, 0);
		for (Map<String, String> article : excelContent) {
			Trade trade = new Trade();
			
			Long id = Long.parseLong(article.get("A"));
			Trade tempTrade = tradeRepository.findById(id).orElse(null);
			
			if(null != tempTrade) {
				continue;
			}
			
			trade.setId(Long.parseLong(article.get("A")));
			Date tradedate;
			try {
				//System.out.println(article.get("B"));
				tradedate = new SimpleDateFormat("yyyyMMdd").parse(article.get("B"));
				trade.setTradeDate(tradedate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			trade.setTradeType(article.get("C").split("\\.")[0]);
			trade.setAmount(Integer.parseInt(article.get("D").split("\\.")[0]));
			trade.setFee(Integer.parseInt(article.get("E").split("\\.")[0]));
			trade.setMerchantId(merchantRepository.findById(Long.parseLong(article.get("F"))).orElse(null));
			trade.setServiceType(article.get("G"));
			trade.setTradeAccess(article.get("H"));
			tradeRepository.save(trade);
		}	
	}

	public void loadMerchant(ExcelReadOption excelReadOption) {
		List<Map<String, String>> excelContent = ExcelRead.read(excelReadOption, 1);
		for (Map<String, String> article : excelContent) {
			Merchant merchant = new Merchant();
			
			Long id = Long.parseLong(article.get("A"));
			Merchant tempMct = merchantRepository.findById(id).orElse(null);
			
			if(null != tempMct) {
				continue;
			}
			
			merchant.setId(Long.parseLong(article.get("A")));
			merchant.setMerchantName(article.get("B"));
			Date regdate;
			try {
				regdate = new SimpleDateFormat("yyyyMMdd").parse(article.get("C"));
				merchant.setRegDate(regdate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			merchant.setServiceType(article.get("D"));
			merchant.setStatusType(article.get("E"));
			merchant.setAddress(article.get("F"));
			merchant.setAddressDetail(article.get("G"));
			
			merchant.setxPos(coordsParsingService.getCoordsByAddress(article.get("F")).getX());
			merchant.setyPos(coordsParsingService.getCoordsByAddress(article.get("F")).getY());
			
			merchantRepository.save(merchant);
		}		
	}
}