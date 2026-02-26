package com.ltc.tableservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@Table(name = "table_guests", uniqueConstraints = {@UniqueConstraint(columnNames = {"guestId"})})

public class TableGuest {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long tableId;
    private Long guestId;
    private LocalDateTime assignedAt;

    public TableGuest() {}
}