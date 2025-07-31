package com.myapp.desk.resource;

import com.myapp.desk.domain.Instrument;
import com.myapp.desk.service.InstrumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import java.util.List;

@RestController
@RequestMapping("instruments")
public class InstrumentResource {

    private final InstrumentService instrumentService;

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
    public ResponseEntity<Instrument> getInstrumentById(@PathVariable long id) {
        Optional<Instrument> instrument = instrumentService.getInstrumentById(id);
        return instrument.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Instrument> deleteInstrument(@PathVariable long id) {
        Optional<Instrument> deleted = instrumentService.deleteInstrument(id);
        return deleted.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}