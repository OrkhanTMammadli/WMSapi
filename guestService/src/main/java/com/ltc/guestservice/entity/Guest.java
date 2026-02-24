package com.ltc.guestservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "Guests")

public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String email;
    @Enumerated(EnumType.STRING)
    private GuestCategory category;
    @Column(nullable = false)
    private Boolean invited;
    @Column(nullable = false)
    private Boolean confirmed;
    @Column(nullable = false)
    private LocalDateTime createdAt;
}