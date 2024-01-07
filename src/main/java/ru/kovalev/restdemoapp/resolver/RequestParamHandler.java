package ru.kovalev.restdemoapp.resolver;

import org.springframework.web.context.request.NativeWebRequest;

import java.util.Map;
import java.util.Set;

public interface RequestParamHandler {
  Map<String, Set<String>> doProcess(NativeWebRequest webRequest);

}
