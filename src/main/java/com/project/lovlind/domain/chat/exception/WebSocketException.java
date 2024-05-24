package com.project.lovlind.domain.chat.exception;

import com.project.lovlind.conmon.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class WebSocketException extends RuntimeException {
  private final ExceptionCode exceptionCode;

  public WebSocketException(ExceptionCode exceptionCode) {
    super(exceptionCode.getMessage());
    this.exceptionCode = exceptionCode;
  }
}
