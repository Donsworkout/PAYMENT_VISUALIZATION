package com.community.rest.repository;

import com.community.rest.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepositorty extends JpaRepository<Trade,Long> { }