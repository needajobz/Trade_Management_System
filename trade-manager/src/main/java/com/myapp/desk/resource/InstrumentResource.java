package com.myapp.desk.resource;

import com.myapp.desk.domain.Instrument;
import com.myapp.desk.service.InstrumentService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("instruments")
public class InstrumentResource {

    private InstrumentService instrumentService;

    public InstrumentResource(InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    @PostMapping
    public ResponseEntity<Instrument> createInstrument(@RequestBody Instrument instrument) {
        instrument = instrumentService.createInstrument(instrument);
        return new ResponseEntity<>(instrument, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Instrument>> getAllInstruments() {
        return ResponseEntity.ok(instrumentService.getInstruments());
    }

//    @PutMapping("/{id}/agent/{agentId}")
//    @RolesAllowed("ADMIN")
//    public ResponseEntity<Instrument> assignAgent(@PathVariable("id") Long instrumentId,
//                                                  @PathVariable("agentId") Long agentId) {
//        Instrument updated = instrumentService.assignAgentToInstrument(instrumentId, agentId);
//        return ResponseEntity.ok(updated);
//    }

    @GetMapping("/{id}")
    public Instrument getInstrumentById(@PathVariable long id) {return tradeService.getInstrumentById(id).get();
    }

    @DeleteMapping("/{id}")
    public Instrument deleteInstrument(@PathVariable long id) {
        instrumentService.
    }
}