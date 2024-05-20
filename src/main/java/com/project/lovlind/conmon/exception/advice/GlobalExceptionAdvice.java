package com.project.lovlind.conmon.exception.advice;

import com.project.lovlind.conmon.exception.BusinessLogicException;
import com.project.lovlind.conmon.exception.ErrorResponse;
import com.project.lovlind.domain.chat.exception.WebSocketException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

  /*
   * 로직에러
   * */
  @ExceptionHandler
  public ResponseEntity<ErrorResponse> handleBusinessException(BusinessLogicException e) {
    log.error("BusinessLogic Exception Error : {}", e.getMessage());
    final ErrorResponse errorResponse = ErrorResponse.of(e.getExceptionCode());
    return new ResponseEntity<>(errorResponse, e.getExceptionCode().getHttpStatus());
  }

  /*
   * 로직에러
   * */
  @ExceptionHandler
  public ResponseEntity<ErrorResponse> handleWebsocketException(WebSocketException e) {
    log.error("BusinessLogic Exception Error : {}", e.getMessage());
    final ErrorResponse errorResponse = ErrorResponse.of(e.getExceptionCode());
    return new ResponseEntity<>(errorResponse, e.getExceptionCode().getHttpStatus());
  }

  /*
   * Dto 검증오류
   * */
  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    log.error("MethodArgumentNotValidException : {}", e.getMessage());
    return ErrorResponse.of(e.getBindingResult());
  }

  /*
   * Controller 검증 오류
   * */
  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleConstraintViolationException(ConstraintViolationException e) {
    log.error("ConstraintViolationException : {}", e.getMessage());
    return ErrorResponse.of(e.getConstraintViolations());
  }

  /*
   * HTTP 메서드 오류
   * */
  @ExceptionHandler
  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  public ErrorResponse handleHttpRequestMethodNotSupportedException(
      HttpRequestMethodNotSupportedException e) {
    log.error("HttpRequestMethodNotSupportedException : {}", e.getMessage());
    return ErrorResponse.of(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage());
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleException(MethodArgumentTypeMismatchException e) {
    log.error("MethodArgumentTypeMismatchException : ", e);
    log.error("MethodArgumentTypeMismatchException : {}", e.getMessage());
    return ErrorResponse.of(HttpStatus.BAD_REQUEST, e.getMessage());
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse handleException(MissingServletRequestPartException e) {
    log.error("MissingServletRequestPartException : {}", e.getMessage());
    return ErrorResponse.of(HttpStatus.NOT_FOUND, e.getMessage());
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleException(MissingServletRequestParameterException e) {
    log.error("MissingServletRequestParameterException : {}", e.getMessage());
    return ErrorResponse.of(HttpStatus.BAD_REQUEST, e.getMessage());
  }
}
