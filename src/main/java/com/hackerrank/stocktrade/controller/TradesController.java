package com.hackerrank.stocktrade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.service.TradeService;

@RestController
@RequestMapping(value = "/trades")
public class TradesController {
	
	@Autowired
	TradeService tradeService;
	
	@PostMapping
	public ResponseEntity<?> add(@RequestBody Trade trade){
		Trade model = tradeService.add(trade);
		if(model!=null) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/erase")
	public ResponseEntity<?> delete() {
		tradeService.deleteAll();
		return new ResponseEntity<>(HttpStatus.OK); 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable Long id) {
		Trade model = tradeService.get(id);
		if(model != null) {
			return new ResponseEntity<>(model, HttpStatus.OK); 
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
	
	@GetMapping
	public ResponseEntity<?> getAll() {
		List<Trade> models = tradeService.getAll(null);
		return new ResponseEntity<>(models, HttpStatus.OK);		
	}
	
	@GetMapping("/users/{userID}")
	public ResponseEntity<?> getAll(@PathVariable(name = "userID") Long userId) {
		List<Trade> models = tradeService.getAll(userId);
		if(models!=null && models.size()>0) {
			return new ResponseEntity<>(models, HttpStatus.OK);
		}
		return new ResponseEntity<>(models, HttpStatus.NOT_FOUND);		
	}
}
