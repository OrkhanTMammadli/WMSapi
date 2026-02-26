package com.ltc.tableservice.exception;

import com.ltc.tableservice.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.awt.geom.RectangularShape;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TableAlreadyExistsException.class)
    public ResponseEntity<String> handleTableAlreadyExistsException (TableAlreadyExistsException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
    @ExceptionHandler(TableNotFoundException.class)
    public ResponseEntity<String> handleTableNotFoundException (TableNotFoundException exception){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);}
    @ExceptionHandler(NoAvailableTablesForEvent.class)
    public ResponseEntity<String> handleNoAvailableTablesForEventException (NoAvailableTablesForEvent exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
    @ExceptionHandler(WeddingTableIsFullException.class)
    public ResponseEntity<String> handleWeddingTableIsFullException (WeddingTableIsFullException exception){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(exception.getMessage());
    }
    @ExceptionHandler(GuestAlreadyAssignedToAnotherTableException.class)
    public ResponseEntity<String> handleGuestAlreadyAssignedToAnotherTableException (GuestAlreadyAssignedToAnotherTableException exception){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(exception.getMessage());
    }
    @ExceptionHandler(GuestNotConfirmedYetException.class)
    public ResponseEntity<ErrorResponseDTO> handleGuestNotConfirmedYetException (GuestNotConfirmedYetException exception){
        return buildResponse("Guest Not Confirmed Yet", HttpStatus.NOT_ACCEPTABLE);
    }
    private ResponseEntity<ErrorResponseDTO> buildResponse(String message, HttpStatus status) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                status.value(),
                message,
                LocalDateTime.now());
        return new ResponseEntity<>(error, status);
    }

}
