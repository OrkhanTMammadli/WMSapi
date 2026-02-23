package com.ltc.guestservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
@EnableCaching
@SpringBootApplication

public class GuestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuestServiceApplication.class, args);
    }

}
