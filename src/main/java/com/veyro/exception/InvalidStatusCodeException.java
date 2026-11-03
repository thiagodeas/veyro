package com.veyro.exception;

public class InvalidStatusCodeException extends RuntimeException {

    public InvalidStatusCodeException() {
        super("O Status Code deve estar entre 100 e 599.");
    }

}
