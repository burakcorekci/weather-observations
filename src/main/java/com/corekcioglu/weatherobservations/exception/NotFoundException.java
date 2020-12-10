package com.corekcioglu.weatherobservations.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String resource, Object id) {
        this(String.format("%s not found; id: %s", resource, id));
    }
}
