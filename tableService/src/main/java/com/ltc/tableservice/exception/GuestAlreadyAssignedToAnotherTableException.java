package com.ltc.tableservice.exception;

public class GuestAlreadyAssignedToAnotherTableException extends RuntimeException {
    public GuestAlreadyAssignedToAnotherTableException(String message) {
        super(message);
    }
}
