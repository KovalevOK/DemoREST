package ru.kovalev.restdemoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.kovalev.restdemoapp.projection.ProductView;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResponseContentDTO {
  private List<ProductView> content;

}
