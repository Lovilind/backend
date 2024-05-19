package com.project.lovlind.conmon.requset.argument.resolver;

import com.project.lovlind.conmon.requset.dto.CurrentUser;
import com.project.lovlind.domain.chat.interceptor.AuthPrincipalInterface;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class CurrentUserResolver implements HandlerMethodArgumentResolver {
  private final AuthPrincipalInterface authPrincipalInterface;

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.getParameterType().equals(CurrentUser.class);
  }

  @Override
  public Object resolveArgument(
      MethodParameter parameter,
      ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest,
      WebDataBinderFactory binderFactory)
      throws Exception {
    HttpServletRequest request = (HttpServletRequest) webRequest;
    String accessToken = request.getHeader("Authentication");
    return authPrincipalInterface.findCurrentUser(accessToken);
  }
}