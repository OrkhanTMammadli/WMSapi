package com.ltc.eventservice.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventResponseDTO {
    private Long id;
    private String name;
    private String venue;
    private String address;
    private LocalDateTime eventDate;
    private Integer totalTables;
    private Integer totalGuests;
    private LocalDateTime createdAt;
}
