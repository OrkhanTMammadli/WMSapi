package com.ltc.tableservice.service;

import com.ltc.tableservice.client.GuestClient;
import com.ltc.tableservice.dto.GuestResponseDTO;
import com.ltc.tableservice.dto.TableRequestDTO;
import com.ltc.tableservice.dto.TableResponseDTO;
import com.ltc.tableservice.entity.WeddingTable;
import com.ltc.tableservice.exception.TableAlreadyExistsException;
import com.ltc.tableservice.exception.TableNotFoundException;
import com.ltc.tableservice.mapper.TableMapper;
import com.ltc.tableservice.repository.TableRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TableImplementation implements TableMethods {
    private final TableRepository tableRepository;
    private final GuestClient guestClient;
    private final TableMapper tableMapper;
    private static final String TABLE_CACHE_NAME = "tableCache";
    private final EmailService emailService;


    @Override
    @CachePut(value = TABLE_CACHE_NAME,key = "#result.id")
    public TableResponseDTO createWeddingTable(TableRequestDTO tableRequestDTO) {
        tableRepository.findByTableNumber(tableRequestDTO.getTableNumber())
                .ifPresent(table -> {throw new TableAlreadyExistsException("Table already exists");});
        WeddingTable  weddingTable = tableMapper.toWeddingTable(tableRequestDTO);
        tableRepository.save(weddingTable);
        log.info("Wedding Table Created Successfully");
        return  tableMapper.toTableResponseDTO(weddingTable);
    }

    @Override
    public TableResponseDTO updateWeddingTable(Long id, TableRequestDTO tableRequestDTO) {
        tableRepository.findByTableNumber(tableRequestDTO.getTableNumber())
                .ifPresent(table -> {throw new TableAlreadyExistsException("Table already exists");});
        WeddingTable foundWeddingTable = tableRepository.findByTableNumber(tableRequestDTO.getTableNumber())
                .orElseThrow(() -> new TableNotFoundException("Table not found"));
        tableMapper.updateWeddingTable(tableRequestDTO, foundWeddingTable);
        tableRepository.save(foundWeddingTable);
        log.info("Wedding Table Updated Successfully");
        return tableMapper.toTableResponseDTO(foundWeddingTable);
    }

    @Override
    @CacheEvict(value = TABLE_CACHE_NAME, key = "#id")
    public void deleteWeddingTable(Long id) {
        tableRepository.deleteById(id);
        log.info("Wedding Table Deleted Successfully");
    }

    @Override
    @Cacheable(value = "TABLE_CACHE_NAME")
    public TableResponseDTO getWeddingTableById(Long id) {
        WeddingTable foundWeddingTable = tableRepository.findById(id)
                .orElseThrow(()-> new TableNotFoundException("Table not found"));
        log.info("Wedding Table Found Successfully");
        return tableMapper.toTableResponseDTO(foundWeddingTable);
    }

    @Override
    @Cacheable(value = "TABLE_CACHE_NAME")
    public List<TableResponseDTO> getAllWeddingTables() {
       List<WeddingTable> weddingTables = tableRepository.findAll();
       log.info("Wedding Tables Found Successfully");
       return weddingTables.stream().map(tableMapper::toTableResponseDTO).toList();
    }

    @Override
    public GuestResponseDTO getGuestById(Long id) {
        GuestResponseDTO guessResponseDTO = guestClient.getGuestById(id);
        if (guessResponseDTO != null) {
            return guessResponseDTO;
        }
        throw new IllegalArgumentException("Guest not found");
    }
}
