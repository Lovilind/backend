package com.project.lovlind.conmon.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonExceptionCode implements ExceptionCode {
  TRANS_ENTITY_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Entity 변환 에러"),
  TRANS_JSON_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "JSON 변환 에러");

  private final HttpStatus httpStatus;
  private final String message;
}
