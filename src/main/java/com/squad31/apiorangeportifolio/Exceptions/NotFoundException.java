package com.squad31.apiorangeportifolio.Exceptions;

public class NotFoundException extends RuntimeException {

    Integer statusCode;
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Integer statusCode) {

        super(message);
        this.statusCode = statusCode;
    }
}
