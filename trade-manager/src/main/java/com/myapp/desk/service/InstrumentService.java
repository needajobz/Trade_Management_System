package com.myapp.desk.service;

import com.myapp.desk.domain.Instrument;

import java.util.List;
import java.util.Optional;

public interface InstrumentService {
        Instrument createInstrument(Instrument instrument);
        Instrument updateInstrument(Long instrumentId, Instrument instrument);
        List<Instrument> getInstruments();
        Optional<Instrument> getInstrumentById(Long instrumentId);
        Optional<Instrument> deleteInstrument(Long instrumentId);
}
