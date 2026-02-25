package com.ltc.tableservice.repository;

import com.ltc.tableservice.entity.WeddingTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TableRepository extends JpaRepository<WeddingTable,Long> {
    Optional<WeddingTable> findByEventId(Long eventId);
    Optional<WeddingTable> findByTableNumber(String tableNumber);
}
