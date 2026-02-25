package com.ltc.tableservice.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class TableResponseDTO {
    private Long id;
    private String tableNumber;
    private Integer capacity;
    private Integer occupiedSeats;
    private Long eventId;
}
