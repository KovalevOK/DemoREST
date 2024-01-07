package ru.kovalev.restdemoapp.utility;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kovalev.restdemoapp.entity.Attribute;
import ru.kovalev.restdemoapp.repository.AttributeRepository;

import java.util.*;
import java.util.stream.Collectors;
@Component
@RequiredArgsConstructor
public class RequestParamSeparator {
  private final AttributeRepository attributeRepository;
  private  Map<String, Set<String>> normalizeParamsMap;

  private Map<Long, Set<String>> clearingAttribute = new HashMap<>();

  public void setRequestParam(Map<String, Set<String>> normalizeRequestParam) {
    normalizeParamsMap = normalizeRequestParam;
  }
  public void separate() {
    if (!normalizeParamsMap.isEmpty()) {
      var attributeList = attributeRepository.findAll()
              .stream()
              .collect(Collectors.toMap(Attribute::getFilterAlias, Attribute::getId));

      this.clearingAttribute = normalizeParamsMap
              .entrySet()
              .stream()
              .filter(v -> attributeList.containsKey(v.getKey()))
              .collect(Collectors.toMap(v -> attributeList.get(v.getKey()), Map.Entry::getValue));
    }
  }

  public List<Long> getAllId() {
    return clearingAttribute
            .keySet()
            .stream()
            .toList();
  }

  public List<String> getAllValues() {
    return clearingAttribute
            .values()
            .stream()
            .flatMap(Collection::stream)
            .toList()
            ;
  }

  public Integer getSizeAttributeMap() {
    return clearingAttribute.size();
  }

  public Boolean filterIsPresent() {
    return clearingAttribute.isEmpty();
  }


}
