package com.project.lovlind.domain.participaint.exception;

import com.project.lovlind.conmon.exception.ExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ParticipantExceptionCode implements ExceptionCode {
  NOT_FOUND_PARTICIPANT(HttpStatus.FOUND, "참가자를 찾을 수 없 습니다.");

  private final HttpStatus httpStatus;
  private final String message;
}
