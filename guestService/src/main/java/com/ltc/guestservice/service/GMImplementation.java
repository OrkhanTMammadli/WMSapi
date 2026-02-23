package com.ltc.guestservice.service;

import com.ltc.guestservice.dto.GuestRequestDTO;
import com.ltc.guestservice.dto.GuestResponseDTO;
import com.ltc.guestservice.entity.Guest;
import com.ltc.guestservice.mapper.GuestMapper;
import com.ltc.guestservice.repository.GuestRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class GMImplementation implements GuestMethods {
    GuestRepository guestRepository;
    GuestMapper guestMapper;
    EmailService emailService;
    private static final String GUEST_CACHE_NAME = "guestCache";

    @Override
    @CachePut(value = GUEST_CACHE_NAME, key = "#result.id")
    public GuestResponseDTO addGuest(GuestRequestDTO guestRequestDTO) {
        Guest guest = guestMapper.toEntity(guestRequestDTO);
        guestRepository.save(guest);
        log.info("Guest Saved: {}", guest);
        emailService.sendHtmlEmail("ordukhanm20@outlook.com", "Guest Saved", "Welcome to our Wedding List");
        return guestMapper.toGuestResponseDTO(guest);
    }

    @Override
    public GuestResponseDTO findGuestById(Long id) {
        return null;
    }

    @Override
    public GuestResponseDTO updateGuest(Long id, GuestRequestDTO guestRequestDTO) {
        return null;
    }

    @Override
    public void deleteGuest(Long id) {

    }

    @Override
    @Cacheable(value = GUEST_CACHE_NAME)
    public List<GuestResponseDTO> getAllGuests() {
        List<Guest> guests = guestRepository.findAll();
        return guests.stream().map(guestMapper::toGuestResponseDTO).toList();
    }
}
