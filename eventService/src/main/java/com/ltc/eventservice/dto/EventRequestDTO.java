package com.ltc.eventservice.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequestDTO {
    @Size(min = 1, max = 50)
    private String name;
    private String venue;
    private String address;
    @Future
    private LocalDateTime eventDate;
    private Integer totalTables;
    private Integer totalGuests;
    private LocalDateTime createdAt;
}
