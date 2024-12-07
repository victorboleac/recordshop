package com.northcoders.recordshop.repository;

import com.northcoders.recordshop.model.StockManager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockManagerRepository extends CrudRepository <StockManager, Long> {
}
