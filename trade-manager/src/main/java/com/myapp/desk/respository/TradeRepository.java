package com.myapp.desk.respository;

import com.myapp.desk.domain.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {
}
