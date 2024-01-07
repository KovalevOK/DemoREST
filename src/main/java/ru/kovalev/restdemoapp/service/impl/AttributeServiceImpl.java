package ru.kovalev.restdemoapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kovalev.restdemoapp.entity.Attribute;
import ru.kovalev.restdemoapp.repository.AttributeRepository;
import ru.kovalev.restdemoapp.service.AttributeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttributeServiceImpl implements AttributeService {

  private final AttributeRepository attributeRepository;
  @Override
//  @Cacheable("attributes")
  public List<Attribute> findAll() {
    return attributeRepository.findAll();
  }
}
