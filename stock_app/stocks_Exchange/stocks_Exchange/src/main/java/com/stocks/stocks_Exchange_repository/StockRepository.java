package com.stocks.stocks_Exchange_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stocks.stocks_Exchange_model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {}