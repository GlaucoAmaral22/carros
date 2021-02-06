package com.carros.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
Toda vez que tivermos o lancamento de um erro e cair aqui, o retorno do status code será 404 not found devido ao @ResponseStatus
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
