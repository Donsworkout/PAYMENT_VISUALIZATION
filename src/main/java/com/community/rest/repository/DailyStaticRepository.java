package com.community.rest.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.community.rest.domain.DailyStatic;
import com.community.rest.domain.Merchant;

@Repository
public interface DailyStaticRepository extends MongoRepository<DailyStatic, Long> {
    Optional<DailyStatic> findByMerchantIdAndTradeDate(Merchant merchant, Date tradeDate);

    List<DailyStatic> findByTradeDate(Date tradedate);
}
