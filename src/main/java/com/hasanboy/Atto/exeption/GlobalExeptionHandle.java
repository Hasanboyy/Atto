package com.hasanboy.Atto.exeption;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExeptionHandle {
    @ExceptionHandler
    public ResponseEntity<?> exeption(AttoExeption e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
