package com.project.lovlind.conmon.exception;

import org.springframework.http.HttpStatus;

public interface ExceptionCode {
  HttpStatus getHttpStatus();

  String getMessage();
}
