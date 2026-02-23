package com.ltc.guestservice.controller;

import com.ltc.guestservice.dto.GuestRequestDTO;
import com.ltc.guestservice.dto.GuestResponseDTO;
import com.ltc.guestservice.mapper.GuestMapper;
import com.ltc.guestservice.service.GMImplementation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guest")
@AllArgsConstructor
public class GuestController {
    private final GMImplementation gMImplementation;
    @Operation(summary = "Add a new Guest")
    @PostMapping("/addGuest")
    ResponseEntity<GuestResponseDTO> addGuest(@RequestBody GuestRequestDTO guestRequestDTO){
    GuestResponseDTO guestResponseDTO = gMImplementation.addGuest(guestRequestDTO);
    return new ResponseEntity<>(guestResponseDTO,HttpStatus.CREATED);}

    @Operation(summary = "Get All Guests")
    @GetMapping("/allGuests")
    ResponseEntity<List<GuestResponseDTO>> getAllGuests(){
    List<GuestResponseDTO> allGuests = gMImplementation.getAllGuests();
    return new ResponseEntity<>(allGuests,HttpStatus.OK);
}
}
