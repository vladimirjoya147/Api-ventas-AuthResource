package com.amaru.ventas_amaru.dev.Exception;

import com.amaru.ventas_amaru.dev.DTO.Ecxepcion.ExcepcionRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class GlobalExcepcion extends RuntimeException{

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExcepcionRequest> ErrorHttp(EntityNotFoundException ex){
        ExcepcionRequest excepcionRequest = new ExcepcionRequest();
        excepcionRequest.setNombre(HttpStatus.NOT_FOUND.value());
        excepcionRequest.setMensaje(ex.getMessage());
        return new ResponseEntity<>(excepcionRequest,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExcepcionRequest> ilegalAgument (IllegalArgumentException illegal){
        ExcepcionRequest excepcionRequest = new ExcepcionRequest();
        excepcionRequest.setNombre(HttpStatus.BAD_REQUEST.value());
        excepcionRequest.setMensaje(illegal.getMessage());
        return new ResponseEntity<>(excepcionRequest,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExcepcionRequest> handleConflict (DataIntegrityViolationException data){
        ExcepcionRequest excepcionRequest = new ExcepcionRequest();
        excepcionRequest.setNombre(HttpStatus.CONFLICT.value());
        excepcionRequest.setMensaje(data.getMessage());
        return new ResponseEntity<>(excepcionRequest,HttpStatus.CONFLICT);
    }
}
