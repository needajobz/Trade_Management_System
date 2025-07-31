package com.myapp.desk.repository;

import com.myapp.desk.domain.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrumentRepository  extends JpaRepository<Instrument, Long> {
    // select * from tickets where closedDate =
    // Spring Boot Convention
    //List<Ticket> findBy(String userId);
    // Convention Over Configuration
}
