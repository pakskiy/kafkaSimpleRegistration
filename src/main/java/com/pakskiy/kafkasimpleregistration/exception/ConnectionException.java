package com.pakskiy.kafkasimpleregistration.exception;

public class ConnectionException extends RuntimeException {
    public ConnectionException(String message) {
        super(message);
    }
}