package com.hackerrank.stocktrade.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hackerrank.stocktrade.dao.TradeRepository;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;


@Service
public class TradeServiceImpl implements TradeService {

	@Autowired
	TradeRepository tradeRepository;
	
	@Override
	public Trade add(Trade trade) {
		if(!tradeRepository.existsById(trade.getId())) {
			return tradeRepository.save(trade);
		}
		
		return null;
	}

	@Override
	public boolean deleteAll() {
		tradeRepository.deleteAll();
		return true;
	}

	@Override
	public Trade get(Long id) {
		Optional<Trade> model = tradeRepository.findById(id);
		if(model.isPresent()) {
			return model.get();
		}
		return null;
	}

	@Override
	public List<Trade> getAll() {
		return tradeRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}
	
	@Override
	public List<Trade> getAllByUserId(Long userId) {
		Trade tradeFilter = new Trade();
		User userFilter = new User();
		userFilter.setId(userId);
		tradeFilter.setUser(userFilter);
		Example<Trade> filter = Example.of(tradeFilter);
		
		return tradeRepository.findAll(filter, Sort.by(Sort.Direction.ASC, "id"));
	}

	@Override
	public List<Trade> getAllBySymbolAndTypeAndBetweenDates(String symbol, String type, Date startDate,
			Date endDate) {
		List<Trade> models;
		
		Trade tradeFilter = new Trade();
		tradeFilter.setSymbol(symbol);
		Example<Trade> filter = Example.of(tradeFilter);
		
		models = tradeRepository.findAll(filter, Sort.by(Sort.Direction.ASC, "id"));
		
		if(!models.isEmpty()) {
			//existe el dato symbol que es mandatorio			
			return tradeRepository.findAllBySymbolAndTypeAndBetweenDates(symbol,
					type!=null?type:null,
					startDate!=null?new Timestamp(startDate.getTime()):null,
					endDate!=null?new Timestamp(endDate.getTime()):null);
		}
		
		return null;
	}

}
