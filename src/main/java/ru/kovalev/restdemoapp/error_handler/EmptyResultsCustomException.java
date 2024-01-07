package ru.kovalev.restdemoapp.error_handler;

public class EmptyResultsCustomException extends Exception{

  public EmptyResultsCustomException() {
    super("Not results for request");
  }
}
