 package com.myapp.desk.service;

import com.myapp.desk.domain.Status;
import com.myapp.desk.respository.AgentRepository;
import com.myapp.desk.respository.TicketRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeServiceImpl implements TradeService {

    private TradeRepository tradeRepository;
    public TradeServiceImpl(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;

    }

    @Override
    public Trade createTrade(Trade trade) {

        return tradeRepository.save(trade);
    }

    @Override
    public Trade updateTrade(Long tradeId, Trade trade) {
        return tradeRepository.findById(tradeId)
                .map(existingTrade -> {
                    // Update fields as needed
                    existingTrade.setPrice(trade.getPrice());
                    existingTrade.setQuantity(trade.getQuantity());
                    existingTrade.setInstrumentSymbol(trade.getInstrumentSymbol());
                    existingTrade.setTradeDate(trade.getTradeDate());
                    existingTrade.setSourceSystem(trade.getSourceSystem());
                    existingTrade.setTradeId(trade.getTradeId());
                    return tradeRepository.save(existingTrade);
                })
                .orElseThrow(() -> new EntityNotFoundException("Trade not found with id: " + tradeId));
    }

    @Override
    public List<Trade> getTrades() {
        return tradeRepository.findAll();
    }

    @Override
    @Cacheable(value = "trades",key = "#tradeId")
    public Optional<Trade> getTradeById(Long tradeId) {
        return tradeRepository.findById(tradeId);
    }

    @Override
    @CacheEvict(value = "trades", key = "#tradeId")
    public Optional<Trade> deleteTrade(Long tradeId) {
        Optional<Trade> tradeOptional = tradeRepository.findById(tradeId);
        tradeOptional.ifPresent(tradeRepository::delete);
        return tradeOptional;
    }
}
