package ru.kovalev.restdemoapp.dto;

import ru.kovalev.restdemoapp.entity.Product;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductMapper {

  public static  ProductWithAttributesDTO toProductWithAttributesDTO(Optional<Product> product){
      ProductWithAttributesDTO productWithAttributesDTO = new ProductWithAttributesDTO();
      productWithAttributesDTO.setId(product.get().getId());
      productWithAttributesDTO.setName(product.get().getName());
      productWithAttributesDTO.setPrice(product.get().getPrice());

      productWithAttributesDTO.setAttributes(product.get().getAttributeValueList().stream().map(v -> AttributeDto.builder().name(v.getAttribute().getName())
              .value(v.getValueEnumerate().getValue())
              .build()).collect(Collectors.toList()));

      return productWithAttributesDTO;
  }
}
