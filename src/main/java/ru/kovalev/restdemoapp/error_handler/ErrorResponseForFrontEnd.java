package ru.kovalev.restdemoapp.error_handler;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class ErrorResponseForFrontEnd {
  private final String error;
  private final List<String> content = new ArrayList<>();

  public ErrorResponseForFrontEnd(Exception error) {
    this.error = error.getMessage();
  }

}
