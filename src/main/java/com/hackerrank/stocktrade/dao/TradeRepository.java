package com.hackerrank.stocktrade.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackerrank.stocktrade.model.Trade;

public interface TradeRepository extends JpaRepository<Trade, Long>{

}
