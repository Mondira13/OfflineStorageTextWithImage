package com.java.imagewithtextstoreinroom.exceptions;

public final class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("Unknown resource not found");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
