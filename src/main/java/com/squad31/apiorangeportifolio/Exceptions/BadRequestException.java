package com.squad31.apiorangeportifolio.Exceptions;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException{

     Integer statusCode;
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Integer statusCode) {

        super(message);
        this.statusCode = statusCode;
    }
}
