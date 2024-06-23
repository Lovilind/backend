package com.project.lovlind.conmon.security.filter;

import com.project.lovlind.conmon.exception.BusinessLogicException;
import com.project.lovlind.conmon.redis.repository.RedisRepository;
import com.project.lovlind.conmon.security.details.Principal;
import com.project.lovlind.conmon.security.dto.UserInfo;
import com.project.lovlind.conmon.security.dto.request.LoginDto;
import com.project.lovlind.conmon.utils.jwt.JwtProperties;
import com.project.lovlind.conmon.utils.jwt.JwtProvider;
import com.project.lovlind.conmon.utils.response.CookieUtils;
import com.project.lovlind.conmon.utils.trans.ObjectMapperUtils;
import com.project.lovlind.domain.member.exception.MemberExceptionCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final JwtProvider jwtProvider;
  private final CookieUtils cookieUtils;
  private final ObjectMapperUtils objectMapper;
  private final RedisRepository repository;
  private final JwtProperties jwtProperties;

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    try {
      LoginDto requestData = objectMapper.toEntity(request, LoginDto.class);

      UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(requestData.getEmail(), requestData.getPassword());
      return getAuthenticationManager().authenticate(token);

    } catch (Exception e) {
      throw new BusinessLogicException(MemberExceptionCode.USER_NOT_SAME);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
    Principal principal = (Principal) authResult.getPrincipal();

    String accessToken = createAccessToken(principal);
    String refreshToken = createRefreshToken(principal);

    repository.save(refreshToken, objectMapper.toStringValue(createUserInfo(principal)), jwtProperties.getRefreshTokenValidityInSeconds());
    response.setHeader(HttpHeaders.AUTHORIZATION, jwtProperties.getPrefix() + accessToken);
    response.addCookie(cookieUtils.createCookie(refreshToken));
  }

  private String createRefreshToken(Principal principal) {
    return jwtProvider.generateRefreshToken(principal.getUsername());
  }

  private String createAccessToken(Principal principal) {
    return jwtProvider.generateAccessToken(
        principal.getUsername(), principal.getId(), toTrans(principal.getAuthorities()));
  }

  private UserInfo createUserInfo(Principal principal) {
    return new UserInfo(principal.getUsername(), toTrans(principal.getAuthorities()));
  }

  private String toTrans(Collection<GrantedAuthority> list) {
    return StringUtils.collectionToCommaDelimitedString(list);
  }
}
