package com.community.rest.repository;

import com.community.rest.domain.DailyStatic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DailyRepository extends JpaRepository<DailyStatic, Long> {
    List<DailyStatic> findByTradeDate(Date date);
    Optional<DailyStatic> findByMerchantIdAndTradeDate(Long merchantId, Date tradeDate);
}
