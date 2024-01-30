package com.squad31.apiorangeportifolio.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (HttpStatus.INTERNAL_SERVER_ERROR)
public class ImageProcessingException extends RuntimeException{

    public ImageProcessingException(){
        super("Erro ao processar imagem");
    }

}
