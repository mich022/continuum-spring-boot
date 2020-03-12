package com.hackerrank.stocktrade.service;

import java.util.Date;
import java.util.List;

import com.hackerrank.stocktrade.model.Trade;

public interface TradeService {
	public Trade add(Trade trade);
	public boolean deleteAll();
	public Trade get(Long id);
	public List<Trade> getAll();
	public List<Trade> getAllByUserId(Long userId);
	public List<Trade> getAllBySymbolAndTypeAndBetweenDates(String symbol, String type, Date startDate, Date endDate);
}