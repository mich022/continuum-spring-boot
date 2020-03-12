package com.hackerrank.stocktrade.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hackerrank.stocktrade.model.Trade;

public interface TradeRepository extends JpaRepository<Trade, Long> {
	@Query(value = "FROM Trade t WHERE t.symbol = :symbol "
			+ "AND (:type IS NULL OR t.type = :type) "
			+ "AND (:startDate IS NULL OR t.timestamp >= :startDate) "
			+ "AND (:endDate IS NULL OR t.timestamp <= :endDate)")
	public List<Trade> findAllBySymbolAndTypeAndBetweenDates(@Param("symbol") String symbol, @Param("type") String type, @Param("startDate") Timestamp startDate,@Param("endDate") Timestamp endDate);
}
