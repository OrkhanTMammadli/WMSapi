package com.ltc.eventservice.controller;

import com.ltc.eventservice.dto.EventRequestDTO;
import com.ltc.eventservice.dto.EventResponseDTO;
import com.ltc.eventservice.service.Implementation;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
@AllArgsConstructor
@Slf4j

public class EventController {
    private final Implementation implementation;
    @Operation(summary = "Add a new Event")
    @PostMapping("/addEvent")
    public ResponseEntity<EventResponseDTO> addEvent(@Valid @RequestBody EventRequestDTO eventRequestDTO) {
        EventResponseDTO eventResponseDTO = implementation.addEvent(eventRequestDTO);
        log.info("Event Added: {}", eventResponseDTO);
        return new ResponseEntity<>(eventResponseDTO, HttpStatus.ACCEPTED);
    }
    @Operation(summary = "Get All Events")
    @GetMapping("/allEvents")
    public ResponseEntity<List<EventResponseDTO>> getAllEvents() {
        List<EventResponseDTO> eventResponseDTO = implementation.getAllEvents();
        log.info("All Events: {}", eventResponseDTO);
        return new ResponseEntity<>(eventResponseDTO, HttpStatus.OK);
    }
    @Operation(summary = "Get the Event by Id")
    @GetMapping("/getEventById/{id}")
    public ResponseEntity<EventResponseDTO> getEventById(@PathVariable Long id){
        EventResponseDTO eventResponseDTO = implementation.findEventById(id);
        log.info("Event Found: {}", eventResponseDTO);
        return new ResponseEntity<>(eventResponseDTO, HttpStatus.OK);
    }
    @Operation(summary = "Update the Event by Id")
    @PutMapping("/updateEvent/{id}")
    public ResponseEntity<EventResponseDTO> updateEvent(@PathVariable Long id, @RequestBody @Valid EventRequestDTO eventRequestDTO){
        EventResponseDTO eventResponseDTO = implementation.updateEvent(id, eventRequestDTO);
        log.info("Event Updated: {}", eventResponseDTO);
        return new ResponseEntity<>(eventResponseDTO, HttpStatus.ACCEPTED);
    }
    @Operation(summary = "Delete the Event by Id")
    @DeleteMapping("/deleteEvent/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        implementation.deleteEvent(id);
        log.info("Event Deleted: {}", id);
        return new ResponseEntity<>("The event deleted", HttpStatus.ACCEPTED);
    }
}
