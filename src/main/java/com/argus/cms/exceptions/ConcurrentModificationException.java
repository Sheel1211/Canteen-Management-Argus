package com.argus.cms.exceptions;


public class ConcurrentModificationException extends Exception {
    public ConcurrentModificationException(String message) {
        super(message);
    }
}
