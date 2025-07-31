package com.myapp.desk.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import com.myapp.desk.service.TradeService;
import com.myapp.desk.domain.Trade;

import java.util.List;

@RestController
@RequestMapping("trades")
public class TradeResource {

    private TradeService tradeService;

    public TradeResource(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @PostMapping
    public ResponseEntity<Trade> createTrade(@RequestBody Trade trade) {
        trade = tradeService.createTrade(trade);
        return new ResponseEntity<>(trade, HttpStatus.OK);
    }

//    @GetMapping
//    public ResponseEntity<List<Trade>> getAllTickets() {
//        return ResponseEntity.ok(ticketService.getTickets());
//    }
//    @PutMapping("/{id}")
//    public ResponseEntity<Ticket> assignAgent(@PathVariable("id") Long ticketId,
//                                              @PathVariable("agentId")    Long agentId) {
//        Ticket updated = ticketService.assignAgentToTicket(ticketId, agentId);
//        return ResponseEntity.ok(updated);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Trade> updateTrade(@PathVariable("id") Long tradeId, @RequestBody Trade trade) {
        Trade updatedTrade = tradeService.updateTrade(tradeId, trade);
        return ResponseEntity.ok(updatedTrade);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Trade> getTradeById(@PathVariable Long id) {
        Optional<Trade> trade = tradeService.getTradeById(id);
        return trade.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Trade>> getAllTrades() {
        return ResponseEntity.ok(tradeService.getTrades());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrade(@PathVariable("id") Long tradeId) {
        Optional<Trade> deleted = tradeService.deleteTrade(tradeId);
        if (deleted.isPresent()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
