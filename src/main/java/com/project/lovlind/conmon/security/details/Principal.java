package com.project.lovlind.conmon.security.details;

import com.project.lovlind.domain.member.entity.Member;
import io.jsonwebtoken.Claims;
import lombok.Getter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

@Getter
public class Principal extends User {
  private final Long id;

  public Principal(Member member) {
    super(
        member.getEmail(),
        member.getPassword(),
        AuthorityUtils.createAuthorityList(
            member.getMemberAuthentication().getUserRole().getRole()));
    this.id = member.getId();
  }

  public Principal(Claims claims) {
    super(
        claims.getSubject(),
        "",
        AuthorityUtils.commaSeparatedStringToAuthorityList(
            claims.get("authorities", String.class)));
    this.id = claims.get("id", Long.class);
  }
}
