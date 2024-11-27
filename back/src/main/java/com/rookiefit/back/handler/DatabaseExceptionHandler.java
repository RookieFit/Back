package com.rookiefit.back.handler;

import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rookiefit.back.dto.response.ResponseDto;

@RestControllerAdvice
public class DatabaseExceptionHandler {
    @ExceptionHandler({ SQLException.class, RuntimeException.class, DataAccessException.class, IllegalArgumentException.class })
    public ResponseEntity<ResponseDto> databaseExceptionHandler(Exception exception) {
        exception.printStackTrace();
        return ResponseDto.databaseError();
    }
}
