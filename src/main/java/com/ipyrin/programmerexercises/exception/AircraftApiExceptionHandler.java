package com.ipyrin.programmerexercises.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.Optional;

@ControllerAdvice
public class AircraftApiExceptionHandler {
    public static final String AIRCRAFT_NOT_FOUND = "The Aircraft Not Found!";
    public static final String UNEXPECTED_ERROR = "An unexpected error!";
    private static final Logger LOGGER = LoggerFactory.getLogger(AircraftApiExceptionHandler.class);

    @ExceptionHandler(value = {AircraftNotFoundException.class})
    public ResponseEntity<ErrorModel> handleNotFoundException(AircraftNotFoundException ex) {
        LOGGER.error(ex.getMessage(), ex);
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND, AIRCRAFT_NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorModel> anyException(Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, UNEXPECTED_ERROR);
    }

    private ResponseEntity<ErrorModel> buildErrorResponse(Exception ex, HttpStatus httpStatus, String userMessage) {
        String msg = Optional.ofNullable(ex.getMessage()).orElse(ex.getClass().getSimpleName());
        ErrorModel error = new ErrorModel(Instant.now().toString(), httpStatus.value(), msg, userMessage);
        return new ResponseEntity<>(error, httpStatus);
    }
}
