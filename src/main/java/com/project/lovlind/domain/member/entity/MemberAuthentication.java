package com.project.lovlind.domain.member.entity;

import com.project.lovlind.domain.member.enums.Provider;
import com.project.lovlind.domain.member.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MemberAuthentication {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false, name = "member_authentication_id")
  private Long id;

  @Enumerated(value = EnumType.STRING)
  private Provider provider;

  @Enumerated(value = EnumType.STRING)
  private UserRole userRole;
}
