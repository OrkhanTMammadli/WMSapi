package com.ltc.guestservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ltc.guestservice.entity.GuestCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class GuestRequestDTO {
    @Size(min = 3,max=20)
    private String firstName;
    @Size(min = 3,max=20)
    private String lastName;
    @Size(min = 12,max=12)
    private String phoneNumber;
    @Email
    private String email;
    @Enumerated(EnumType.STRING)
    private GuestCategory category;
    private Boolean invited;
    private Boolean confirmed;
    @Future
    private LocalDateTime createdAt;
}
