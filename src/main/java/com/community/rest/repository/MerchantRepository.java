package com.community.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.community.rest.domain.Merchant;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {
	@Query(value="SELECT * "
				+ "FROM merchant "
				+ "WHERE x_pos IS NULL OR "
				+ "y_pos IS NULL", nativeQuery=true)
	List<Merchant> findByXPosOrYPosIsNull();
}
