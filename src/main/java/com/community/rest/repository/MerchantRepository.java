package com.community.rest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.community.rest.domain.Merchant;

@Repository
public interface MerchantRepository extends MongoRepository<Merchant, Long> {
}
