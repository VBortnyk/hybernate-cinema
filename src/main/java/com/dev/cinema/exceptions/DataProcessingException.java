package com.dev.cinema.exceptions;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String msg, Exception ex) {
        super(msg, ex);
    }
}
