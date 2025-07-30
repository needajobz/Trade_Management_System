package com.myapp.desk.service;

import com.myapp.desk.domain.Trade

import java.util.List;
import java.util.Optional;

public interface TradeService {

    Trade createTrade(Trade trade);
    Trade updateTrade(Long tradeId, Trade trade);
    List<Trade> getTrades();
    Optional<Trade> getTradeById(Long tradeId);
    Optional<Trade> deleteTrade(Long tradeId);
}
