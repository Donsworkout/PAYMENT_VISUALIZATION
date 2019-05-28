package com.community.rest.service;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
	private CoordsParsingService coordsparsingservice;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	private static final Logger LOGGER = LogManager.getLogger(DataUploadService.class);

	
	public void excelUpload(File destFile, String type) throws Exception {
		
		ExcelReadOption excelReadOption = new ExcelReadOption();
		excelReadOption.setFilePath(destFile.getAbsolutePath());
		excelReadOption.setStartRow(2);
		
		if(type.equals("trade")) {
			excelReadOption.setOutputColumns("A", "B", "C", "D", "E", "F", "G", "H");
			loadTrade(excelReadOption);
		}else if(type.equals("merchant")) {
			excelReadOption.setOutputColumns("A", "B", "C", "D", "E", "F", "G");
			loadMerchant(excelReadOption);
		}else {
			LOGGER.warn("업로드가 지원되지 않는 타입입니다");
		}

	}


	public void loadTrade(ExcelReadOption excelReadOption) {
	    Query query = new Query();
	    query.addCriteria(Criteria.where("_id").ne(null));
	    long count = mongoTemplate.count(query, Trade.class);
	    
		LOGGER.info("현재 trade 저장 개수 (start) : " + count);

		List<Map<String, String>> excelContent = ExcelRead.read(excelReadOption, 0, count);
		
		int index = 0;
		List<Trade> tmpTrades = new ArrayList<>();
		
		for (Map<String, String> article : excelContent) {
			if(index == 10000) {
				tradeRepository.saveAll(tmpTrades);
				LOGGER.info("현재 trade 저장 개수 : " + mongoTemplate.count(query, Trade.class));
				tmpTrades.clear();
				index = 0;
			}
			
			Trade trade = new Trade();
			Long id = Long.parseLong(article.get("A"));
			
			if(null != tradeRepository.findById(id).orElse(null)) {
				continue;
			}
			
			trade.id = Long.parseLong(article.get("A"));
			
			Date tradedate;
			try {
				tradedate = new SimpleDateFormat("yyyyMMdd").parse(article.get("B"));
				trade.setTradeDate(tradedate);
			} catch (ParseException e) {
				LOGGER.info("Trade_ID-? :" + id + "'s tradeDate was not uploaded");
			}
			
			trade.tradeType = article.get("C").split("\\.")[0];
			trade.amount = Integer.parseInt(article.get("D").split("\\.")[0]);
			trade.fee = Integer.parseInt(article.get("E").split("\\.")[0]);
			trade.merchantId = Long.parseLong(article.get("F"));
			trade.serviceType = article.get("G");
			trade.tradeAccess = article.get("H");
			
			tmpTrades.add(trade);
			index ++;
		}	
		
		// 10000개 단위로 마지막 나머지 엔티티들 commit 하고 임시 배열 해제
		if(!tmpTrades.isEmpty()) {
			tradeRepository.saveAll(tmpTrades);
			LOGGER.info("현재 trade 저장 개수 : " + tradeRepository.countById());
			tmpTrades.clear();			
		}

	}

	public void loadMerchant(ExcelReadOption excelReadOption) {
	    Query query = new Query();
	    query.addCriteria(Criteria.where("_id").ne(null));
	    long count = mongoTemplate.count(query, Trade.class);
	    
		List<Map<String, String>> excelContent = ExcelRead.read(excelReadOption, 1, count);
		
		int index = 0;
		List<Merchant> tmpMerchants = new ArrayList<>();
		
		for (Map<String, String> article : excelContent) {
			
			if(index == 10000) {
				merchantRepository.saveAll(tmpMerchants);
				coordsparsingservice.setMerchantsCoords(tmpMerchants);
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
				LOGGER.info("Merchant_ID- :" + id + "'s tradeDate is not uploaded");
			}
			
			merchant.serviceType = article.get("D");
			merchant.statusType = article.get("E");
			merchant.address = article.get("F");
			merchant.addressDetail = article.get("G");
			
			tmpMerchants.add(merchant);
			index ++;
		}	

		if(!tmpMerchants.isEmpty()) {
			merchantRepository.saveAll(tmpMerchants);
			LOGGER.info("현재 trade 저장 개수 : " + count);
			coordsparsingservice.setMerchantsCoords(tmpMerchants);
			tmpMerchants.clear();			
		}
		
	}
}