package com.project.lovlind.conmon.security.handler;

import com.project.lovlind.conmon.exception.ErrorResponse;
import com.project.lovlind.conmon.security.exception.AuthExceptionCode;
import com.project.lovlind.conmon.utils.trans.ObjectMapperUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {

  private final ObjectMapperUtils objectMapperUtils;

  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException)
      throws IOException {
    sendError(response, getExceptionCodeByRequest(request));
  }

  private AuthExceptionCode getExceptionCodeByRequest(HttpServletRequest request) {
    if (!(request.getAttribute("exceptionCode") instanceof AuthExceptionCode)) {
      return AuthExceptionCode.UNAUTHENTICATED;
    }
    return (AuthExceptionCode) request.getAttribute("exceptionCode");
  }

  private void sendError(HttpServletResponse response, AuthExceptionCode authExceptionCode)
      throws IOException {
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setCharacterEncoding("utf-8");
    response.setStatus(authExceptionCode.getHttpStatus().value());
    response.getWriter().write(getResponseData(authExceptionCode));
  }

  private String getResponseData(AuthExceptionCode authExceptionCode) {
    return objectMapperUtils.toStringValue(createErrorResponse(authExceptionCode));
  }

  private ErrorResponse createErrorResponse(AuthExceptionCode authExceptionCode) {
    return ErrorResponse.of(authExceptionCode.getHttpStatus(), authExceptionCode.getMessage());
  }
}
