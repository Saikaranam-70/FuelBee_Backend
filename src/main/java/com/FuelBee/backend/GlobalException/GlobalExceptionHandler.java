package com.FuelBee.backend.GlobalException;

import com.FuelBee.backend.exception.*;
import com.FuelBee.backend.response.ErrorResponse;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<com.FuelBee.backend.response.ErrorResponse> handleUserAlreadyExistException(UserAlreadyExistException ex){
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OTPInvalidException.class)
    public ResponseEntity<ErrorResponse> otpInvalidException(OTPInvalidException ex){
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundException(UserNotFoundException ex){
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ErrorResponse> addressNotFoundException(AddressNotFoundException ex){
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), HttpStatus.NO_CONTENT.value()),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UserNotVerifiedException.class)
    public ResponseEntity<ErrorResponse> userNotVerifiedException(UserNotVerifiedException ex){
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DealerAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> dealerAlreadyExistException(DealerNotFoundException ex){
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DealerNotFoundException.class)
    public ResponseEntity<ErrorResponse> dealerNotFoundException(DealerNotFoundException ex){
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FuelStationAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> fuelStationAlreadyExistException(FuelStationAlreadyExistException ex){
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FuelStationNotFoundException.class)
    public ResponseEntity<ErrorResponse> fuelStationNotFoundException(FuelStationNotFoundException ex){
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }
}
