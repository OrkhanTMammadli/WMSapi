package com.ltc.eventservice.mapper;

import com.ltc.eventservice.dto.EventRequestDTO;
import com.ltc.eventservice.dto.EventResponseDTO;
import com.ltc.eventservice.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventResponseDTO ToEventResponseDTO(Event event);
    Event ToEvent(EventRequestDTO eventRequestDTO);
    void updateEvent(EventRequestDTO eventRequestDTO, @MappingTarget Event event);
}
