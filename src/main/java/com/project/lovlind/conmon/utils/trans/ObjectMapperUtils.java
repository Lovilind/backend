package com.project.lovlind.conmon.utils.trans;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.lovlind.conmon.exception.BusinessLogicException;
import com.project.lovlind.conmon.exception.CommonExceptionCode;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ObjectMapperUtils {
  private final ObjectMapper objectMapper;

  public <T> T toEntity(HttpServletRequest request, Class<T> valueType) {
    try {
      return objectMapper.readValue(request.getInputStream(), valueType);
    } catch (IOException e) {
      throw new BusinessLogicException(CommonExceptionCode.TRANS_ENTITY_ERROR);
    }
  }

  public <T> T toEntity(String content, Class<T> valueType) {
    try {
      return objectMapper.readValue(content, valueType);
    } catch (IOException e) {
      throw new BusinessLogicException(CommonExceptionCode.TRANS_ENTITY_ERROR);
    }
  }

  public String toStringValue(Object value) {
    try {
      return objectMapper.writeValueAsString(value);
    } catch (JsonProcessingException e) {
      throw new BusinessLogicException(CommonExceptionCode.TRANS_JSON_ERROR);
    }
  }
}
