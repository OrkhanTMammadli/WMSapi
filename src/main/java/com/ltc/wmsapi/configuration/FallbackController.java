package com.ltc.wmsapi.configuration;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class FallbackController {

    @GetMapping(value = "/fallback/event", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Map<String, Object>> eventFallback() {
        return Mono.just(Map.of(
                "status", 503,
                "error", "Service Unavailable",
                "message", "Event service is currently unavailable. Please try again later.",
                "timestamp", LocalDateTime.now().getNano()
        ));
    }
    @GetMapping(value = "/fallback/guest", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Map<String, Object>> guestFallback() {
        return Mono.just(Map.of(
                "status", 503,
                "error", "Service Unavailable",
                "message", "Guest service is currently unavailable. Please try again later.",
                "timestamp", LocalDateTime.now().getNano()
        ));
    }
    @GetMapping(value = "/fallback/table", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Map<String, Object>> tableFallback() {
        return Mono.just(Map.of(
                "status", 503,
                "error", "Service Unavailable",
                "message", "Table service is currently unavailable. Please try again later.",
                "timestamp", LocalDateTime.now().getNano()
        ));
    }
}