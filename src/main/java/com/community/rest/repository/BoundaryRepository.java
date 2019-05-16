package com.community.rest.repository;

import com.community.rest.domain.DailyStatic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BoundaryRepository extends JpaRepository<DailyStatic, Long> { }