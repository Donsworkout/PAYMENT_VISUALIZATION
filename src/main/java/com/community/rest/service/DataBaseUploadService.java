package com.community.rest.service;

import com.community.rest.domain.DailyStatic;
import com.community.rest.domain.Trade;
import com.community.rest.repository.DailyStaticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataBaseUploadService {
    @Autowired
    private DailyStaticRepository dailyStaticRepository;

    public void uploadDailyStatic(List<Trade> tradeList) throws Exception {
        for(Trade trade:tradeList) {
            Optional<DailyStatic> dailyStaticOptional = dailyStaticRepository.findByMerchantIdAndTradeDate(trade.getMerchantId().getId(), trade.getTradeDate());
            // 이미 데이터가 있는 경우
            if (dailyStaticOptional.isPresent()) {
                DailyStatic dailyStatic = dailyStaticOptional.get();
                dailyStatic.setAmount(dailyStatic.getAmount() + trade.getAmount());
                dailyStatic.setFrequency(dailyStatic.getFrequency() + 1);
                dailyStaticRepository.save(dailyStatic);
            } else {
                DailyStatic ds = new DailyStatic();
                ds.setTradeDate(trade.getTradeDate());
                ds.setMerchantId(trade.getMerchantId());
                ds.setAmount(trade.getAmount());
                ds.setFrequency((long)1);
                dailyStaticRepository.save(ds);
            }
        }

    }
}

