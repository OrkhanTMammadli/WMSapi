package com.ltc.guestservice.service;

import com.ltc.guestservice.dto.GuestRequestDTO;
import com.ltc.guestservice.dto.GuestResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GuestMethods {
    GuestResponseDTO addGuest(GuestRequestDTO guestRequestDTO);
    GuestResponseDTO findGuestById(Long id);
    GuestResponseDTO updateGuest(Long id, GuestRequestDTO guestRequestDTO);
    void deleteGuest(Long id);
    List<GuestResponseDTO> getAllGuests();

}
