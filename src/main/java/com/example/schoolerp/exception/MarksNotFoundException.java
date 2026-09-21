package com.example.schoolerp.exception;

public class MarksNotFoundException extends RuntimeException {
    public MarksNotFoundException(String message) {
        super(message);
    }
}
