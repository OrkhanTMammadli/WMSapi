package com.ltc.tableservice.service;

import com.ltc.tableservice.client.GuestClient;
import com.ltc.tableservice.dto.GuestResponseDTO;
import com.ltc.tableservice.entity.TableGuest;
import com.ltc.tableservice.entity.WeddingTable;
import com.ltc.tableservice.exception.GuestAlreadyAssignedToAnotherTableException;
import com.ltc.tableservice.exception.GuestNotConfirmedYetException;
import com.ltc.tableservice.exception.TableNotFoundException;
import com.ltc.tableservice.exception.WeddingTableIsFullException;
import com.ltc.tableservice.repository.TableGuestRepository;
import com.ltc.tableservice.repository.WeddingTableRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class TableAssignmentService {

    private final WeddingTableRepository tableRepository;
    private final TableGuestRepository tableGuestRepository;
    private final GuestClient guestClient;

    public void assignGuestToTable(Long tableId, Long guestId) {
        GuestResponseDTO guest = guestClient.getGuestById(guestId);

        if (!guest.getConfirmed()) {
            throw new GuestNotConfirmedYetException("Guest has not confirmed attendance.");}

        WeddingTable table = tableRepository.findById(tableId)
                .orElseThrow(() -> new TableNotFoundException("Table not found"));

        if (table.getOccupiedSeats() >= table.getCapacity()) {
            throw new WeddingTableIsFullException("Table is full");
        }

        boolean alreadyAssigned = tableGuestRepository
                .existsByGuestId(guestId);

        if (alreadyAssigned) {
            throw new GuestAlreadyAssignedToAnotherTableException("Guest already assigned to a table");
        }

        TableGuest tableGuest = new TableGuest();
        tableGuest.setTableId(tableId);
        tableGuest.setGuestId(guestId);
        tableGuest.setAssignedAt(LocalDateTime.now());

        tableGuestRepository.save(tableGuest);
        table.setOccupiedSeats(table.getOccupiedSeats() + 1);
        tableRepository.save(table);
    }
}
