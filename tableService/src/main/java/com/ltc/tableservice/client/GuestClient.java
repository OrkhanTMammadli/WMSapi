package com.ltc.tableservice.client;


import com.ltc.tableservice.dto.GuestResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "guest-service", url = "http://localhost:8082/api/guest")
public interface GuestClient {
    @GetMapping("/getGuestbyId/{id}")
    GuestResponseDTO getGuestById(@PathVariable Long id);
}