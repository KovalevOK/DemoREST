package ru.kovalev.restdemoapp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AttributeDto {
  private String name;
  private String value;
}
