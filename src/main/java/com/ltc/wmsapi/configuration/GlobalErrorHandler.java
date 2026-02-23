package com.ltc.wmsapi.configuration;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Configuration
@Order(-2)
public class GlobalErrorHandler implements ErrorWebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {

        if ("RATE_LIMIT_EXCEEDED".equals(ex.getMessage())) {

            exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
            exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

            String body = """
                {
                  "status": 429,
                  "error": "Too Many Requests",
                  "message": "Rate limit exceeded.You press the refresh button so quick, please be patient.",
                  "timestamp": "%s"
                }
                """.formatted(LocalDateTime.now().withNano(0));

            byte[] bytes = body.getBytes(StandardCharsets.UTF_8);

            return exchange.getResponse()
                    .writeWith(Mono.just(exchange.getResponse()
                            .bufferFactory()
                            .wrap(bytes)));
        }

        return Mono.error(ex);
    }
}