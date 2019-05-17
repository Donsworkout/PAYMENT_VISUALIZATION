package com.community.rest.service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.LoggerFactory;
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
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DataUploadService.class);

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
		}
	}
	
	public void loadTrade(ExcelReadOption excelReadOption) throws NumberFormatException, EncryptedDocumentException, InvalidFormatException, IOException {
		List<Map<String, String>> excelContent = ExcelRead.read(excelReadOption, 0);
		
		int index = 0;
		List<Trade> tmpTrades = new ArrayList<Trade>();
		
		for (Map<String, String> article : excelContent) {
			if(index == 10000) {
				tradeRepository.saveAll(tmpTrades);
				tmpTrades.clear();
				index = 0;
			}
			
			Trade trade = new Trade();
			Long id = Long.parseLong(article.get("A"));
			
			//if(null != tradeRepository.findById(id).orElse(null)) {
			//	continue;
			//}
			
			trade.setId(Long.parseLong(article.get("A")));
			Date tradedate;
			try {
				tradedate = new SimpleDateFormat("yyyyMMdd").parse(article.get("B"));
				trade.setTradeDate(tradedate);
			} catch (ParseException e) {
				e.printStackTrace();
				logger.info("Trade_ID- :" + id + "tradeDate is not uploaded");
			}
			
			trade.tradeType = article.get("C").split("\\.")[0];
			trade.amount = Integer.parseInt(article.get("D").split("\\.")[0]);
			trade.fee = Integer.parseInt(article.get("E").split("\\.")[0]);
			trade.merchantId = merchantRepository.findById(Long.parseLong(article.get("F"))).orElse(null);
			trade.serviceType = article.get("G");
			trade.tradeAccess = article.get("H");
			
			tmpTrades.add(trade);
			index ++;
		}	
		if(!tmpTrades.isEmpty()) {
			tradeRepository.saveAll(tmpTrades);
			tmpTrades.clear();			
		}
	}

	public void loadMerchant(ExcelReadOption excelReadOption) throws EncryptedDocumentException, InvalidFormatException, IOException {
		List<Map<String, String>> excelContent = ExcelRead.read(excelReadOption, 1);
		
		int index = 0;
		List<Merchant> tmpMerchants = new ArrayList<Merchant>();
		
		for (Map<String, String> article : excelContent) {
			
			if(index == 10000) {
				merchantRepository.saveAll(tmpMerchants);
				tmpMerchants.clear();
				index = 0;
			}
			
			Merchant merchant = new Merchant();
			
			Long id = Long.parseLong(article.get("A"));
			
			if(null != merchantRepository.findById(id).orElse(null)) {
				continue;
			}
			
			merchant.id = Long.parseLong(article.get("A"));
			merchant.merchantName = article.get("B");
			
			Date regdate;
			try {
				regdate = new SimpleDateFormat("yyyyMMdd").parse(article.get("C"));
				merchant.setRegDate(regdate);
			} catch (ParseException e) {
				e.printStackTrace();
				logger.info("Merchant_ID- :" + id + "tradeDate is not uploaded");
			}
			
			merchant.serviceType = article.get("D");
			merchant.statusType = article.get("E");
			merchant.address = article.get("F");
			merchant.addressDetail = article.get("G");
			
			//merchant.xPos = coordsParsingService.getCoordsByAddress(article.get("F")).getX();
			//merchant.yPos = coordsParsingService.getCoordsByAddress(article.get("F")).getY();
			System.out.println(merchant);
			tmpMerchants.add(merchant);
			index ++;
		}	

		if(!tmpMerchants.isEmpty()) {
			merchantRepository.saveAll(tmpMerchants);
			tmpMerchants.clear();			
		}
	}
}