package com.ltc.guestservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ltc.guestservice.entity.GuestCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestRequestDTO {
    @Size(min = 3,max=15)
    private String firstName;
    @Size(min = 3,max=15)
    private String lastName;
    @Size(min = 12,max=12, message = "Phone number should be 12 digits like 994505005050")
    private String phoneNumber;
    @Email(message = "Email should be valid")
    private String email;
    @Enumerated(EnumType.STRING)
    private GuestCategory category;
    private Boolean invited;
    private Boolean confirmed;
    private LocalDateTime createdAt;
}
