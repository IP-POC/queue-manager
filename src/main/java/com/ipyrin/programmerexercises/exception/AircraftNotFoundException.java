package com.ipyrin.programmerexercises.exception;

public class AircraftNotFoundException extends RuntimeException {
    public AircraftNotFoundException(String message) {
        super(message);
    }
}
