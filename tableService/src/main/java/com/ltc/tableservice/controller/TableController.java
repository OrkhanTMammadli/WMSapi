package com.ltc.tableservice.controller;

import com.ltc.tableservice.dto.GuestResponseDTO;
import com.ltc.tableservice.dto.TableRequestDTO;
import com.ltc.tableservice.dto.TableResponseDTO;
import com.ltc.tableservice.service.TableImplementation;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/table")
@Slf4j
@AllArgsConstructor

public class TableController {
    private final TableImplementation tableImplementation;

    @Operation(summary = "Creating a new table")
    @PostMapping("/createTable")
    public ResponseEntity<TableResponseDTO> createTable(@RequestBody @Valid TableRequestDTO tableRequestDTO){
        TableResponseDTO tableResponseDTO = tableImplementation.createWeddingTable(tableRequestDTO);
        return new ResponseEntity<>(tableResponseDTO, HttpStatus.ACCEPTED);
    }
    @Operation(summary = "Showing all tables")
    @GetMapping("/allTables")
    public ResponseEntity<List<TableResponseDTO>> getAllTables(){
        List<TableResponseDTO> allTables = tableImplementation.getAllWeddingTables();
        return new ResponseEntity<>(allTables, HttpStatus.OK);
    }
    @Operation(summary = "Get the table with Id")
    @GetMapping("/getTablebyId/{id}")
    public ResponseEntity<TableResponseDTO> getTableById(@PathVariable Long id){
        TableResponseDTO tableResponseDTO = tableImplementation.getWeddingTableById(id);
        return new ResponseEntity<>(tableResponseDTO, HttpStatus.OK);
    }

    @Operation(summary = "Updateing the table")
    @PutMapping("/updateTable/{id}")
    public ResponseEntity<TableResponseDTO> updateTable(@PathVariable Long id, @RequestBody @Valid TableRequestDTO tableRequestDTO){
            TableResponseDTO  tableResponseDTO = tableImplementation.updateWeddingTable(id, tableRequestDTO);
            return new ResponseEntity<>(tableResponseDTO, HttpStatus.ACCEPTED);
    }
    @Operation
    @DeleteMapping("/deleteTable/{id}")
    public ResponseEntity<String> deleteTable(@PathVariable Long id){
        tableImplementation.deleteWeddingTable(id);
        return new ResponseEntity<>("The table is deleted",HttpStatus.ACCEPTED);
    }

    @GetMapping("/getGuestbyId/{id}")
    public ResponseEntity<GuestResponseDTO> getGuestById(@PathVariable Long id){
        GuestResponseDTO guestResponseDTO = tableImplementation.getGuestById(id);
        return new  ResponseEntity<>(guestResponseDTO, HttpStatus.OK);
    }
}
