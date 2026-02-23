package com.ltc.guestservice.dto;

import com.ltc.guestservice.entity.GuestCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class GuestResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    @Enumerated(EnumType.STRING)
    private GuestCategory category;
    private Boolean invited;
    private Boolean confirmed;
    private LocalDateTime createdAt;
}
