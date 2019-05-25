package com.community.rest.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.community.rest.domain.Trade;
@Repository
public interface TradeRepository extends MongoRepository<Trade,Long> {
    List<Trade> findByMerchantIdId(Long id);
    
    public Trade findFirstByOrderByTradeDateDesc();
    
    public Trade findFirstByOrderByTradeDateAsc();
}