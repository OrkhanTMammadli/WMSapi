package com.ltc.guestservice.controller;

import com.ltc.guestservice.dto.GuestRequestDTO;
import com.ltc.guestservice.dto.GuestResponseDTO;
import com.ltc.guestservice.service.GMImplementation;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guest")
@AllArgsConstructor
@Slf4j
public class GuestController {
    private final GMImplementation gMImplementation;

    @Operation(summary = "Add a new Guest")
    @PostMapping("/addGuest")
    public ResponseEntity<GuestResponseDTO> addGuest(@RequestBody @Valid GuestRequestDTO guestRequestDTO){
    GuestResponseDTO guestResponseDTO = gMImplementation.addGuest(guestRequestDTO);
    log.info("Guest Added: {}", guestResponseDTO);
    return new ResponseEntity<>(guestResponseDTO,HttpStatus.CREATED);}

    @Operation(summary = "Get All Guests")
    @GetMapping("/allGuests")
    public ResponseEntity<List<GuestResponseDTO>> getAllGuests(){
    List<GuestResponseDTO> allGuests = gMImplementation.getAllGuests();
    log.info("All Guests: {}", allGuests);
    return new ResponseEntity<>(allGuests,HttpStatus.OK);
}
    @Operation(summary = "Get the Guest by Id")
    @GetMapping("/getGuestbyId/{id}")
    public ResponseEntity<GuestResponseDTO> getGuestById(@PathVariable Long id){
        GuestResponseDTO guestResponseDTO = gMImplementation.findGuestById(id);
        log.info("Guest Found: {}", guestResponseDTO);
        return new ResponseEntity<>(guestResponseDTO,HttpStatus.OK);
    }
    @Operation(summary = "Update the Guest by Id")
    @PutMapping("/updateGuest/{id}")
    public ResponseEntity<GuestResponseDTO> updateGuest(@PathVariable Long id, @RequestBody @Valid GuestRequestDTO guestRequestDTO){
        GuestResponseDTO guestResponseDTO = gMImplementation.updateGuest(id, guestRequestDTO);
        log.info("Guest Updated: {}", guestResponseDTO);
        return new ResponseEntity<>(guestResponseDTO,HttpStatus.ACCEPTED);
    }
    @Operation(summary = "Delete the Guest by Id")
    @DeleteMapping("/deleteGuest/{id}")
    public ResponseEntity<String> deleteGuest(@PathVariable Long id) {
        gMImplementation.deleteGuest(id);
        log.info("Guest Deleted: {}", id);
        return new ResponseEntity<>("The guest deleted", HttpStatus.ACCEPTED);
    }
}
