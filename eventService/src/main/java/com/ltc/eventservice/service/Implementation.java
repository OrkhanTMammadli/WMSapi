package com.ltc.eventservice.service;

import com.ltc.eventservice.dto.EventRequestDTO;
import com.ltc.eventservice.dto.EventResponseDTO;
import com.ltc.eventservice.entity.Event;
import com.ltc.eventservice.exception.EventAlreadyExistsException;
import com.ltc.eventservice.exception.EventNotFoundException;
import com.ltc.eventservice.mapper.EventMapper;
import com.ltc.eventservice.repository.EventRepository;
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

public class Implementation implements EventMethods{
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final EmailService emailService;
    private final static String EVENT_CACHE_NAME = "eventCache";

    @Override
    @CachePut(value = EVENT_CACHE_NAME, key = "#result.id")
    public EventResponseDTO addEvent(EventRequestDTO eventRequestDTO) {
        eventRepository.findByName(eventRequestDTO.getName())
                .ifPresent(event -> { throw new EventAlreadyExistsException("Event already exists");});
        Event event = eventMapper.ToEvent(eventRequestDTO);
        eventRepository.save(event);
        log.info("Event Saved: {}", event);
        emailService.sendHtmlEmail("ordukhanm20@outlook.com", "Wedding Management System", "New Wedding Event Created");
        return eventMapper.ToEventResponseDTO(event);
    }

    @Override
    @Cacheable(value = EVENT_CACHE_NAME, key = "#id")
    public EventResponseDTO findEventById(Long id) {
        Event foundEvent = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event not found"));
        return eventMapper.ToEventResponseDTO(foundEvent);
    }

    @Override
    @CachePut(value = EVENT_CACHE_NAME, key = "#id")
    public EventResponseDTO updateEvent(Long id, EventRequestDTO eventRequestDTO) {
        eventRepository.findByName(eventRequestDTO.getName())
                .ifPresent(event -> {throw new EventAlreadyExistsException("This wedding record is already exists");});
        Event foundEvent = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event not found"));
        eventMapper.updateEvent(eventRequestDTO, foundEvent);
        eventRepository.save(foundEvent);
        log.info("Event Updated: {}", foundEvent);
        emailService.sendHtmlEmail("ordukhanm20@outlook.com", "Wedding Management System", "Event Updated");
        return eventMapper.ToEventResponseDTO(foundEvent);
    }

    @Override
    @CacheEvict(value = EVENT_CACHE_NAME, key = "#id")
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
        log.info("Event Deleted: {}", id);
        emailService.sendHtmlEmail("ordukhanm20@outlook.com", "Wedding Management System", "Event has been deleted");
    }

    @Override
    @Cacheable(value = EVENT_CACHE_NAME)
    public List<EventResponseDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(eventMapper::ToEventResponseDTO).toList();
    }
}
