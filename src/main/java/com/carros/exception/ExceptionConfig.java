package com.carros.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class ExceptionConfig extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            EmptyResultDataAccessException.class
    })
    public ResponseEntity errorNotFound(Exception e){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity errorBadRequest(){
        return ResponseEntity.badRequest().build();
    }

    //Poderia deixar a tratativa para o exception handler aqui, mas preferi deixar na classe isolada por estar relacionado a segurança. Deixar aqui somente a parte de erros mais genericos da app.
    /*@ExceptionHandler({
            AccessDeniedException.class
    })
    public ResponseEntity erroAcessDenies(){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Error("Acesso Negaduuuu"));
    }*/



}
