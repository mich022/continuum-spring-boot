package com.hackerrank.stocktrade.service;

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
public class TradeImpl implements TradeService {

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
	public List<Trade> getAll(Long userId) {
		if(userId!=null && userId>0L) {
			
			Trade tradeFilter = new Trade();
			User userFilter = new User();
			userFilter.setId(userId);
			tradeFilter.setUser(userFilter);
			Example<Trade> filter = Example.of(tradeFilter);
			
			return tradeRepository.findAll(filter, Sort.by(Sort.Direction.ASC, "id"));
			
		} else {
			
			return tradeRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
			
		}
	}

}
