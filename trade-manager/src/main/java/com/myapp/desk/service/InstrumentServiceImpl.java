package com.myapp.desk.service;

import com.myapp.desk.domain.Instrument;
import com.myapp.desk.respository.InstrumentRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstrumentServiceImpl implements InstrumentService {

    private final InstrumentRepository instrumentRepository;

    public InstrumentServiceImpl(InstrumentRepository instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }

    @Override
    public Instrument createInstrument(Instrument instrument) {
        return instrumentRepository.save(instrument);
    }

    @Override
    public Instrument updateInstrument(Long instrumentId, Instrument instrument) {
        return instrumentRepository.findById(instrumentId)
                .map(existingInstrument -> {
                    // Update fields as needed (update with actual Instrument fields)
                    existingInstrument.setSymbol(instrument.getSymbol());
                    existingInstrument.setIsin(instrument.getIsin());
                    existingInstrument.setType(instrument.getType());
                    existingInstrument.setId(instrument.getId());
                    // ...add other Instrument fields as needed...
                    return instrumentRepository.save(existingInstrument);
                })
                .orElseThrow(() -> new EntityNotFoundException("Instrument not found with id: " + instrumentId));
    }

    @Override
    public List<Instrument> getInstruments() {
        return instrumentRepository.findAll();
    }

    @Override
    @Cacheable(value = "instruments", key = "#instrumentId")
    public Optional<Instrument> getInstrumentById(Long instrumentId) {
        return instrumentRepository.findById(instrumentId);
    }

    @Override
    @CacheEvict(value = "instruments", key = "#instrumentId")
    public Optional<Instrument> deleteInstrument(Long instrumentId) {
        Optional<Instrument> instrumentOptional = instrumentRepository.findById(instrumentId);
        instrumentOptional.ifPresent(instrumentRepository::delete);
        return instrumentOptional;
    }