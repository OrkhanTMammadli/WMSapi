package com.ltc.tableservice.service;

import com.ltc.tableservice.dto.GuestResponseDTO;
import com.ltc.tableservice.dto.TableRequestDTO;
import com.ltc.tableservice.dto.TableResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TableMethods {
    TableResponseDTO createWeddingTable(TableRequestDTO tableRequestDTO);
    TableResponseDTO updateWeddingTable(Long id, TableRequestDTO tableRequestDTO);
    void deleteWeddingTable(Long id);
    TableResponseDTO getWeddingTableById(Long id);
    List<TableResponseDTO> getAllWeddingTables();
    GuestResponseDTO getGuestById(Long id);
}
