package com.project.lovlind.domain.member.enums;

import lombok.Getter;

@Getter
public enum LoginType {

  O01("자체회원")
  , S01("구글")
  , S02("")
  ;

  private final String desc;

  LoginType(String desc) {
    this.desc = desc;
  }

  public boolean isSocialMember() {
    return this != O01;
  }
}
