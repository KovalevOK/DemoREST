package ru.kovalev.restdemoapp.resolver.impl;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import ru.kovalev.restdemoapp.resolver.RequestParamHandler;

import java.util.*;

@Component
@Primary
public class NormalizeRequestParamHandlerImpl implements RequestParamHandler {

  private final String pageParameterName;
  private final String sizeParameterName;

  public NormalizeRequestParamHandlerImpl(@Value("${spring.data.web.pageable.page-parameter}") final String pageParameterName,
                                          @Value("${spring.data.web.pageable.size-parameter}") final String sizeParameterName) {
    this.pageParameterName=pageParameterName;
    this.sizeParameterName=sizeParameterName;
  }

  public  Map<String, Set<String>> doProcess(
          NativeWebRequest webRequest)
    {

    if(!webRequest.getParameterMap().isEmpty()) {
      Map<String, Set<String>> normalizeRequestParam = new HashMap<>();
      for (Map.Entry<String, String[]> entry : webRequest.getParameterMap().entrySet()) {
        var modifiedKey = entry.getKey().toLowerCase().trim();
        var modifiedValue = entry.getValue();

        if(modifiedKey.equals("")) {continue;}

        Set<String> modifiedValueSet = new HashSet<>();
        if(normalizeRequestParam.containsKey(modifiedKey)) {
          modifiedValueSet = normalizeRequestParam.get(modifiedKey);
        }

        for (String value : modifiedValue) {
          if(value.equals("")) {continue;}
          modifiedValueSet.add(
                  value.trim().toLowerCase()
          );
        }
        normalizeRequestParam.put(modifiedKey,modifiedValueSet);
      }

      normalizeRequestParam.keySet().removeAll(List.of(pageParameterName,sizeParameterName));

      return normalizeRequestParam;
    }
    return new HashMap<>();
  }
}
