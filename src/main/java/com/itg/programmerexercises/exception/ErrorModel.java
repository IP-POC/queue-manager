package com.itg.programmerexercises.exception;

public class ErrorModel {
    private final String timestamp;
    private final int status;
    private final String error;
    private final String userMessage;

    public ErrorModel(String timestamp, int status, String error, String userMessage) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.userMessage = userMessage;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getUserMessage() {
        return userMessage;
    }
}
