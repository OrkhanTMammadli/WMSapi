package com.ltc.tableservice.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class WeddingTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String tableNumber;
    @Column(nullable = false)
    private Integer capacity;
    @Column(nullable = false)
    private Integer occupiedSeats;
    @Column(nullable = false)
    private Long eventId;
}

