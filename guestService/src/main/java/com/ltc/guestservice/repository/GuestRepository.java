package com.ltc.guestservice.repository;

import com.ltc.guestservice.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GuestRepository extends JpaRepository<Guest, Long> {
    Optional<Guest> findByPhoneNumber(String phoneNumber);
}
