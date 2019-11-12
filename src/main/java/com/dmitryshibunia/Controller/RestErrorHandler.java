package com.dmitryshibunia.Controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class RestErrorHandler {

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleDataAccessException(DataAccessException ex) {
        return "DataAccessException: " + ex.getLocalizedMessage();
    }

    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidFormatException(InvalidFormatException ex){
        return "InvalidFormatException" + ex.getLocalizedMessage();
    }
}
