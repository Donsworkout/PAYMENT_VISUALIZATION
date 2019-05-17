package com.community.rest.repository;

import com.community.rest.domain.DailyStatic;
import com.community.rest.domain.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DailyStaticRepository extends JpaRepository<DailyStatic, Long> {
    Optional<DailyStatic> findByMerchantIdAndTradeDate(Merchant merchant, Date tradeDate);

    List<DailyStatic> findByTradeDate(Date tradedate);
}
