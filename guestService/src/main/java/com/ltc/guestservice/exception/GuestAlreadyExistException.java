package com.ltc.guestservice.exception;

public class GuestAlreadyExistException extends RuntimeException {
    public GuestAlreadyExistException(String message) {
        super(message);
    }
}
