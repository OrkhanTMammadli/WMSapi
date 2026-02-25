package com.ltc.tableservice.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableRequestDTO {
    @Positive
    private String tableNumber;
    @Positive
    private Integer capacity;
    @Positive
    private Integer occupiedSeats;
    private Long eventId;
}
