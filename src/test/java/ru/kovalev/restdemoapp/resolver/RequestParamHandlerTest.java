package ru.kovalev.restdemoapp.resolver;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(properties = {
        "spring.data.web.pageable.size-parameter=size",
        "spring.data.web.pageable.page-parameter=page"})
@ExtendWith(MockitoExtension.class)
class RequestParamHandlerTest {

  @Autowired
  private RequestParamHandler requestParamHandler;

  @Mock
  private NativeWebRequest mockWebRequest;

  @Test
  void emptyURLParameters() {
    when(mockWebRequest.getParameterMap()).thenReturn(new HashMap<>());
    assertTrue(requestParamHandler.doProcess(mockWebRequest).isEmpty());
  }

  @Test
  void URLParametersWithPageAndSize() {

    Map<String, String[]> query = new HashMap<>();
    query.put("page", new String[]{"1"});
    query.put("size", new String[]{"2"});

    when(mockWebRequest.getParameterMap()).thenReturn(query);
    assertTrue(requestParamHandler.doProcess(mockWebRequest).isEmpty());
  }

  @Test
  void URLParametersWithPageAndSizeAndOneParameter() {
    Map<String, String[]> query = new HashMap<>();
    query.put("page", new String[]{"0"});
    query.put("size", new String[]{"0"});
    query.put("co", new String[]{"Russia"});
    when(mockWebRequest.getParameterMap()).thenReturn(query);
    assertEquals(1, requestParamHandler.doProcess(mockWebRequest).entrySet().size());
  }

  @Test
  void URLParametersNormalize() {
    Map<String, String[]> query = new HashMap<>();
    Map<String, Set<String>> result = new HashMap<>();

    query.put("co", new String[]{" китай", " Россия ", "Франция "});
    query.put("c", new String[]{"Красный"});
    query.put("  c", new String[]{""});
    query.put("", new String[]{"Черный"});
    query.put(" c", new String[]{" Оранжевый"});
    query.put(" c ", new String[]{" Желтый "});
    query.put("C", new String[]{" Зеленый "});

    result.put("co", Set.of("китай", "россия", "франция"));
    result.put("c", Set.of("зеленый", "желтый", "красный", "оранжевый"));

    when(mockWebRequest.getParameterMap()).thenReturn(query);
    assertEquals(result, requestParamHandler.doProcess(mockWebRequest));
  }
}