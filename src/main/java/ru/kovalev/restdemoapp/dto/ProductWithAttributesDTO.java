package ru.kovalev.restdemoapp.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ProductWithAttributesDTO {
  private  Long id;
  private String name;
  private  Integer price;
  private List<AttributeDto> attributes;


}
