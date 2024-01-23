package com.squad31.apiorangeportifolio.Controller.Request;

public record CreateUserRequest(

        String name,
        String lastname,
        String email,
        String password

) {

}
