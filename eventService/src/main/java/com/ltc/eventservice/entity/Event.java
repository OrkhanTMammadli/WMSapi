package com.ltc.eventservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Events")

public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String venue;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private LocalDateTime eventDate;
    @Column(nullable = false)
    private Integer totalTables;
    @Column(nullable = false)
    private Integer totalGuests;
    @Column(nullable = false)
    private LocalDateTime createdAt;
}
