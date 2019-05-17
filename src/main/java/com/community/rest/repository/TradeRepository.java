package com.community.rest.repository;

import com.community.rest.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface TradeRepository extends JpaRepository<Trade,Long> {
    List<Trade> findByMerchantIdId(Long id);
    
    @Query(value="SELECT MAX(trade_date) FROM trade", nativeQuery=true)
    public String getMaxDate();
    
    @Query(value="SELECT MIN(trade_date) FROM trade", nativeQuery=true)
    public String getMinDate();
}