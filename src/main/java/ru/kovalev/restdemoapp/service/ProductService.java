package ru.kovalev.restdemoapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.kovalev.restdemoapp.dto.ProductWithAttributesDTO;
import ru.kovalev.restdemoapp.projection.ProductView;

import java.util.Map;
import java.util.Set;

public interface ProductService {
  Page<ProductView> findAllProductWithFilter(Map<String, Set<String>> normalizeParamsMap, Pageable pageable);
  ProductWithAttributesDTO getProductBy(Long id);

}
