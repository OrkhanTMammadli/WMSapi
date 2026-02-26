package com.ltc.tableservice.repository;

import com.ltc.tableservice.entity.WeddingTable;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WeddingTableRepository extends JpaRepository<WeddingTable, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT t FROM WeddingTable t WHERE t.id = :id")
    Optional<WeddingTable> findWithLockById(Long id);

    @Query("""
        SELECT t FROM WeddingTable t
        WHERE t.eventId = :eventId
        AND t.occupiedSeats < t.capacity
    """)
    List<WeddingTable> findAvailableTablesByEventId(Long eventId);
}
