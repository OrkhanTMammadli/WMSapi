package com.ltc.eventservice.service;

import com.ltc.eventservice.dto.EventRequestDTO;
import com.ltc.eventservice.dto.EventResponseDTO;

import java.util.List;

public interface EventMethods {
    EventResponseDTO addEvent(EventRequestDTO eventRequestDTO);
    EventResponseDTO findEventById(Long id);
    EventResponseDTO updateEvent(Long id, EventRequestDTO eventRequestDTO);
    void deleteEvent(Long id);
    List<EventResponseDTO> getAllEvents();
}
