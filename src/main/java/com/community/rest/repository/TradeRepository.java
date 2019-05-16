package com.community.rest.repository;

import com.community.rest.domain.Merchant;
import com.community.rest.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TradeRepository extends JpaRepository<Trade,Long> {
    List<Trade> findByMerchantIdId(Long id);
}