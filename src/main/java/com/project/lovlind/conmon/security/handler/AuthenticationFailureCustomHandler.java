package com.project.lovlind.conmon.security.handler;

import com.project.lovlind.conmon.utils.response.ErrorResponseUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationFailureCustomHandler implements AuthenticationFailureHandler {

  private final ErrorResponseUtils errorResponseUtils;

  @Override
  public void onAuthenticationFailure(
      HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
      throws IOException {
    log.error("# Authentication failed: {}", exception.getMessage());
    log.error("authentication ", exception);

    errorResponseUtils.sendErrorResponse(response);
  }
}
