package com.ltc.guestservice.mapper;

import com.ltc.guestservice.dto.GuestRequestDTO;
import com.ltc.guestservice.dto.GuestResponseDTO;
import com.ltc.guestservice.entity.Guest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GuestMapper {
    GuestResponseDTO toGuestResponseDTO (Guest guest);
    Guest toEntity(GuestRequestDTO guestRequestDTO);
    void updateGuest(GuestRequestDTO guestRequestDTO, @MappingTarget Guest guest);
}
