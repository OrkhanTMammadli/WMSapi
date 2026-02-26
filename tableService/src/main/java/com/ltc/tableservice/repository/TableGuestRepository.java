package com.ltc.tableservice.repository;

import com.ltc.tableservice.entity.TableGuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableGuestRepository extends JpaRepository<TableGuest, Long> {
    boolean existsByGuestId(Long guestId);
    boolean existsByTableIdAndGuestId(Long tableId, Long guestId);
    List<TableGuest> findAllByTableId(Long tableId);
    long countByTableId(Long tableId);
    void deleteByGuestId(Long guestId);
}
