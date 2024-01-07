package ru.kovalev.restdemoapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kovalev.restdemoapp.dto.ProductWithAttributesDTO;
import ru.kovalev.restdemoapp.projection.ProductView;
import ru.kovalev.restdemoapp.utility.RequestParamSeparator;
import ru.kovalev.restdemoapp.repository.ProductRepository;
import ru.kovalev.restdemoapp.service.ProductService;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import static ru.kovalev.restdemoapp.dto.ProductMapper.toProductWithAttributesDTO;
@Transactional
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
  private final ProductRepository productRepository;
  private final RequestParamSeparator requestParamSeparator;

  @Override
  public Page<ProductView> findAllProductWithFilter(Map<String, Set<String>> normalizeParamsMap, Pageable pageable) {

    if (normalizeParamsMap.isEmpty()) {
      return productRepository.findAllProductWithPagination(pageable);
    } else {

      requestParamSeparator.setRequestParam(normalizeParamsMap);
      requestParamSeparator.separate();

      if (!requestParamSeparator.filterIsPresent()) {
        return productRepository.findAllProductWithFilterAndPagination(
                requestParamSeparator.getAllId(),
                requestParamSeparator.getAllValues(),
                requestParamSeparator.getSizeAttributeMap(),
                pageable
        );
      } else {
        throw new NoSuchElementException("Фильтр ничего не нашел");
      }
    }
  }

  @Override
  public ProductWithAttributesDTO getProductBy(Long id) {
    return toProductWithAttributesDTO(productRepository.getProductBy(id));
  }


}