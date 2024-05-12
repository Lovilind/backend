package com.project.lovlind.domain.member.vo;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
  private String zipCode;
  private String addressSimple;
  private String addressDetail;
  private String phone;
}
