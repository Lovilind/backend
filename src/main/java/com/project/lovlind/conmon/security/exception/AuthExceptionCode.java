package com.project.lovlind.conmon.security.exception;

import com.project.lovlind.conmon.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthExceptionCode implements ExceptionCode {
  ACCESS_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, " Access Token 만료"),
  INVALID_SIGNATURE_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "서명이 올바르지 않습니다"),
  UNAUTHENTICATED(HttpStatus.UNAUTHORIZED, "접근 권한이 없습니다."),
  REFRESH_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "저장된 RefreshToken이 존재하지 않습니다"),
  REFRESH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "RefreshToken 기간이 만료 되었습니다");

  private final HttpStatus httpStatus;
  private final String message;
}
