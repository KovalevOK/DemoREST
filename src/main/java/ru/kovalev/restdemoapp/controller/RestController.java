package ru.kovalev.restdemoapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kovalev.restdemoapp.projection.ProductView;
import ru.kovalev.restdemoapp.dto.ProductWithAttributesDTO;
import ru.kovalev.restdemoapp.resolver.NormalizeRequestParam;
import ru.kovalev.restdemoapp.service.ProductService;

import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/api")
public class RestController {

  private final ProductService productService;

  @GetMapping(value = "/products")
  public ResponseEntity<Page<ProductView>> findAllProductWithFilter(
          @NormalizeRequestParam Map<String, Set<String>> normalizeParamsMap,
          Pageable pageable
  ) {
    return ResponseEntity.ok(productService.findAllProductWithFilter(normalizeParamsMap, pageable));
  }

  @GetMapping(value = "/products/{id}")
  public ResponseEntity<ProductWithAttributesDTO> getProductById(
          @PathVariable(name = "id") Long id
  ) {

    return ResponseEntity.ok(productService.getProductBy(id));
  }

}



