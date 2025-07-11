package org.example.exception;

public class MiddlewareException extends Exception{
    public MiddlewareException(String msg, Exception e) {
        super(msg, e);
    }

    public MiddlewareException(String msg) {
        super(msg);
    }
}
