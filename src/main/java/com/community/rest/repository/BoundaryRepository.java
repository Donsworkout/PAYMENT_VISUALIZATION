package com.community.rest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.community.rest.domain.Boundary;


@Repository
public interface BoundaryRepository extends MongoRepository<Boundary, Long> {}