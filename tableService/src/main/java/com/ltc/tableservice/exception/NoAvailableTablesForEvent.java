package com.ltc.tableservice.exception;

public class NoAvailableTablesForEvent extends RuntimeException {
    public NoAvailableTablesForEvent(String message) {
        super(message);
    }
}
