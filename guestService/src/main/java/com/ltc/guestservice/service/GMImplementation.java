package com.ltc.guestservice.service;

import com.ltc.guestservice.dto.GuestRequestDTO;
import com.ltc.guestservice.dto.GuestResponseDTO;
import com.ltc.guestservice.entity.Guest;
import com.ltc.guestservice.exception.GuestAlreadyExistException;
import com.ltc.guestservice.exception.GuestNotFoundException;
import com.ltc.guestservice.mapper.GuestMapper;
import com.ltc.guestservice.repository.GuestRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
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
        guestRepository.findByPhoneNumber(guestRequestDTO.getPhoneNumber())
                .ifPresent(guest -> {throw new GuestAlreadyExistException("Guest already exists with this Phone number.");});
        Guest guest = guestMapper.toEntity(guestRequestDTO);
        guestRepository.save(guest);
        log.info("Guest Saved: {}", guest);
        emailService.sendHtmlEmail("ordukhanm20@outlook.com", "Wedding Management System", "Welcome to our Wedding List");
        return guestMapper.toGuestResponseDTO(guest);
    }

    @Override
    public GuestResponseDTO findGuestById(Long id) {
        Guest foundGuest = guestRepository.findById(id).orElseThrow(() -> new GuestNotFoundException("Guest not found"));
        return guestMapper.toGuestResponseDTO(foundGuest);
    }

    @Override
    @CachePut(value = GUEST_CACHE_NAME, key = "#id")
    public GuestResponseDTO updateGuest(Long id, GuestRequestDTO guestRequestDTO) {
        guestRepository.findByPhoneNumber(guestRequestDTO.getPhoneNumber())
                .ifPresent(guest -> {throw new GuestAlreadyExistException("Guest already exists with this Phone number.");});
        Guest foundGuest = guestRepository.findById(id).orElseThrow(() -> new GuestNotFoundException("Guest not found"));
        guestMapper.updateGuest(guestRequestDTO, foundGuest);
        guestRepository.save(foundGuest);
        return guestMapper.toGuestResponseDTO(foundGuest);
    }

    @Override
    @CacheEvict(value = GUEST_CACHE_NAME, key = "#id")
    public void deleteGuest(Long id) {
        guestRepository.deleteById(id);
        log.info("Guest Deleted: {}", id);
    }

    @Override
    @Cacheable(value = GUEST_CACHE_NAME)
    public List<GuestResponseDTO> getAllGuests() {
        List<Guest> guests = guestRepository.findAll();
        return guests.stream().map(guestMapper::toGuestResponseDTO).toList();
    }
}
