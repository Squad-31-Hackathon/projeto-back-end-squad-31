package com.squad31.apiorangeportifolio.Exceptions;

import lombok.Getter;

@Getter
public class UserException extends RuntimeException{


    public UserException(String message) {
        super(message);
    }
}
