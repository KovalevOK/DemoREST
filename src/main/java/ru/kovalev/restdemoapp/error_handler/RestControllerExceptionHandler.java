package ru.kovalev.restdemoapp.error_handler;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.ConnectException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class RestControllerExceptionHandler{

  @ExceptionHandler(EmptyResultsCustomException.class)
  public ResponseEntity<ErrorResponseForFrontEnd> emptyCollectionCustomException(EmptyResultsCustomException ex) {
    var error = new ErrorResponseForFrontEnd(ex);
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(TypeMismatchException.class)
  protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex) {
    var error = new ErrorResponseForFrontEnd(ex);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ConnectException.class)
  public ResponseEntity<ErrorResponseForFrontEnd> connectException(ConnectException ex) {
    var error = new ErrorResponseForFrontEnd(ex);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  protected ResponseEntity<Object> handleTypeMismatch(IllegalArgumentException ex) {
    var error = new ErrorResponseForFrontEnd(ex);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NoSuchElementException.class)
  protected ResponseEntity<Object> noSuchElementException(NoSuchElementException ex) {
    var error = new ErrorResponseForFrontEnd(ex);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

}
