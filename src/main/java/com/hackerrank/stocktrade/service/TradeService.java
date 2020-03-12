package com.hackerrank.stocktrade.service;

import java.util.List;

import com.hackerrank.stocktrade.model.Trade;

public interface TradeService {
	public Trade add(Trade trade);
	public boolean deleteAll();
	public Trade get(Long id);
	public List<Trade> getAll();
}
